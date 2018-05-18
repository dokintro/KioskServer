package server.endpoints;

import com.google.gson.Gson;
import server.controllers.FraggelController;
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

@Path("")
public class FraggelEndpoint {

    private FraggelController fraggelController = new FraggelController();
    private Gson gson = new Gson();

    @GET
    @Path("/admin/allUsers")
    public Response getAllUsers() {
        String json = gson.toJson(fraggelController.getAllUsers());
        Log.writeLog(getClass().getName(), this, "All users fetched", 0);
        return Response
                .status(200)
                .type("application/json")
                .entity(json)
                .build();
    }

    @POST
    @Path("/admin/addUser")
    public Response addUser(String userData) throws SQLException {
        User user = gson.fromJson(userData, User.class);
        if (fraggelController.createUser(user)) {
            Log.writeLog(getClass().getName(), this, "User created", 0);
            String json = gson.toJson(user);
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(Crypter.encrypt(json))
                    .build();
        } else {
            Log.writeLog(getClass().getName(), this, "Not able to create User", 2);
            return Response
                    .status(403)
                    .type("plain/text")
                    .entity("Failed! User couldn't be created")
                    .build();
        }
    }

    @PUT
    @Path("/admin/{idUser}/update-user")
    public Response updateUser(@PathParam("idUser") int idUser, String data) throws SQLException {
        User user = gson.fromJson(data, User.class);
        user.setIdUser(idUser);

        if (fraggelController.updateUser(user)) {
            String json = gson.toJson(user);
            Log.writeLog(getClass().getName(), this, "User was updated", 0);
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(Crypter.encrypt(json))
                    .build();
        } else {
            Log.writeLog(getClass().getName(), this, "User not found", 2);
            return Response
                    .status(404)
                    .type("plain/text")
                    .entity("The user wasn't found")
                    .build();
        }
    }

    @DELETE
    @Path("/admin/{idUser}/delete-user")
    public Response deleteProduct(@PathParam("idUser") int idUser, String data) throws Exception {
        User user = gson.fromJson(data, User.class);
        user.setIdUser(Integer.parseInt(String.valueOf(idUser)));

        if (fraggelController.deleteUser(user)) {
            String json = gson.toJson(user);
            Log.writeLog(getClass().getName(), this, "User deleted", 0);
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(Crypter.encrypt(json))
                    .build();
        } else {
            Log.writeLog(getClass().getName(), this, "Yser not deleted", 2);
            return Response
                    .status(400)
                    .type("plain/text")
                    .entity("Couldn't delete the user")
                    .build();
        }
    }
}

