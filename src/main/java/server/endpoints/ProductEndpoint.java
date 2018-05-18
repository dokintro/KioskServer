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
    @Path("/admin/allProducts")
    public Response getAllProducts() {
        String json = gson.toJson(productController.getAllProducts());
        Log.writeLog(getClass().getName(), this, "All products fetched", 0);
        return Response
                .status(200)
                .type("application/json")
                .entity(json)
                .build();
    }

    @GET
    @Path("/admin/everythingBought")
    public Response getEverythingBought() {
        String json = gson.toJson(productController.getEverythingBought());
        Log.writeLog(getClass().getName(), this, "Everything bought fetched", 0);
        return Response
                .status(200)
                .type("application/json")
                .entity(json)
                .build();
    }

    @PUT
    @Path("/admin/{idProduct}/update-product")
    public Response updateProduct(@PathParam("idProduct") int idProduct, String data) throws SQLException {
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
    @Path("/admin/createProduct")
    public Response createProduct(String productData) throws SQLException {
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

    @DELETE
    @Path("/admin/{idProduct}/delete-product")
    public Response deleteProduct(@PathParam("idProduct") int idProduct, String data) throws Exception {
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

    @PUT
    @Path("/admin/{idProduct}/refill")
    public Response refillProduct(@PathParam("idProduct") int idProduct, String data) throws Exception {
        Product product = gson.fromJson(data, Product.class);
        product.setIdProduct(Integer.parseInt(String.valueOf(idProduct)));
        if (productController.refillProduct(product)) {
            String json = gson.toJson(product);
            Log.writeLog(getClass().getName(), this, "Product refilled", 0);
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(Crypter.encrypt(json))
                    .build();
        } else {
            Log.writeLog(getClass().getName(), this, "Product not refilled", 2);
            return Response
                    .status(400)
                    .type("plain/text")
                    .entity("Couldn't refill the product")
                    .build();
        }
    }

    @DELETE
    @Path("/admin/delete-arrangement-data")
    public Response deleteArrangementData(String data) throws Exception {
        if (productController.deleteArrangementData()) {
            Log.writeLog(getClass().getName(), this, "Purchase history and user data deleted", 0);

            return Response
                    .status(200)
                    .type("application/json")
                    .entity("{'String':'Purchase history and user data deleted'}")
                    .build();
        } else {
            Log.writeLog(getClass().getName(), this, "Purchase history and user data not deleted", 2);
            return Response
                    .status(400)
                    .type("plain/text")
                    .entity("Couldn't delete prchase history and user data")
                    .build();
        }
    }
}

