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

@Path("/fraggel")
public class FraggelEndpoint {

    private FraggelController fraggelController = new FraggelController();
    private UserTable userTable = new UserTable();
    private Gson gson = new Gson();

    @GET
    @Path("/admin/allUsers")
    public Response getAllUsers() {

        String json = gson.toJson(fraggelController.getAllUsers());
        Log.writeLog(getClass().getName(), this, "All users fetched", 0);
        return Response
                .status(200)
                .type("application/json")
                .entity(Crypter.encrypt(json))
                .build();
    }
}

