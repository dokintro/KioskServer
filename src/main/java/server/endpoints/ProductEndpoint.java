package server.endpoints;

import com.google.gson.Gson;
import server.controllers.ProductController;
import server.models.Product;
import server.models.User;
import server.providers.ProductTable;
import server.providers.UserTable;
import server.resources.Log;
import server.utility.Crypter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/kiosk")
public class ProductEndpoint {

    private ProductController productController = new ProductController();
    private ProductTable productTable = new ProductTable();
    private UserTable userTable = new UserTable();
    private Gson gson = new Gson();

    @GET
    public Response getActiveProducts() {
        String json = gson.toJson(productController.getAllActiveProducts());
        Log.writeLog(getClass().getName(), this, "All active products fetched", 0);
        return Response
                .status(200)
                .type("application/json")
                .entity(json)
                .build();
    }

    @POST
    public Response buyProduct(@HeaderParam("Authorization") String userRFID, String productData) throws SQLException {
        Product receivedProduct = gson.fromJson(productData, Product.class);
        Product foundProduct = productTable.getProductByName(receivedProduct);
        User foundUser = userTable.getUserByRFID(userRFID);
        foundProduct.setAmountBought(receivedProduct.getAmountBought());
        if (productController.buyProduct(foundProduct, userRFID, foundUser)) {
            Log.writeLog(getClass().getName(), this, "A product has been bought", 0);
            return Response
                    .status(200)
                    .type("plain/text")
                    .entity("product bought")
                    .build();
        } else {
            Log.writeLog(getClass().getName(), this, "Couldn't buy product", 2);

            return Response
                    .status(401)
                    .type("plain/text")
                    .entity("Couldn't buy product")
                    .build();
        }
    }

    @GET
    @Path("/admin")
    public Response getAllProducts() {
        String json = gson.toJson(productController.getAllProducts());
        Log.writeLog(getClass().getName(), this, "All products fetched", 0);
        return Response
                .status(200)
                .type("application/json")
                .entity(Crypter.encrypt(json))
                .build();
    }

    @PUT
    @Path("/admin/{idProduct}/update-product")
    public Response updateProduct(@PathParam("idProduct") int idProduct, String data) throws SQLException {
        data = gson.fromJson(data, String.class);

        Product product = gson.fromJson(data, Product.class);
        product.setIdProduct(idProduct);

        if (productController.updateProduct(product)) {
            String json = gson.toJson(product);
            Log.writeLog(getClass().getName(), this, "Product was updated", 0);

            return Response
                    .status(200)
                    .type("application/json")
                    .entity(Crypter.encrypt(json))
                    .build();
        } else {
            Log.writeLog(getClass().getName(), this, "Product not found", 2);
            return Response
                    .status(404)
                    .type("plain/text")
                    .entity("The product wasn't found")
                    .build();
        }
    }

    @POST
    @Path("/admin/create")
    public Response createProduct(String productData) throws SQLException {
        productData = gson.fromJson(productData, String.class);

        Product product = gson.fromJson(productData, Product.class);
        if (productController.createProduct(product)) {
            Log.writeLog(getClass().getName(), this, "Product created", 0);
            String json = gson.toJson(product);
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(Crypter.encrypt(json))
                    .build();
        } else {
            Log.writeLog(getClass().getName(), this, "Not able to create product", 2);
            return Response
                    .status(403)
                    .type("plain/text")
                    .entity("Failed! Product couldn't be created")
                    .build();
        }
    }

    @PUT
    @Path("/admin/{idProduct}/delete-product")
    public Response deleteProduct(@PathParam("idProduct") int idProduct, String data) throws Exception {

        data = gson.fromJson(data, String.class);

        Product product = gson.fromJson(data, Product.class);
        product.setIdProduct(Integer.parseInt(String.valueOf(idProduct)));

        if (productController.deleteProduct(product)) {
            String json = gson.toJson(product);

            Log.writeLog(getClass().getName(), this, "Product deleted", 0);
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(Crypter.encrypt(json))
                    .build();
        } else {
            Log.writeLog(getClass().getName(), this, "Product not deleted", 2);
            return Response
                    .status(400)
                    .type("plain/text")
                    .entity("Couldn't delete the product")
                    .build();
        }
    }
}

