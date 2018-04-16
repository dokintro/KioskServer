package server.endpoints;

import com.google.gson.Gson;
import server.models.User;
import server.providers.UserTable;
import server.resources.Log;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/login")
public class LoginEndpoint {

    private Gson gson = new Gson();
    private UserTable userTable = new UserTable();

    @POST
    public Response login(String jsonLogin) {
        User foundUser;
        User needAuthUser = gson.fromJson(jsonLogin, User.class);
        try {
            foundUser = userTable.getUserByRFID(needAuthUser.getRFIDUser());
        } catch (Exception notFound) {
            Log.writeLog(getClass().getName(), this, "RFID not found/not existing", 2);
            return Response
                    .status(401)
                    .type("plain/text")
                    .entity("RFID does not exist")
                    .build();
        }
        String returnUser = gson.toJson(foundUser);
        Log.writeLog(getClass().getName(), this, "Logged in", 0);
        return Response
                .status(200)
                .type("application/json")
                .entity(returnUser)
                .build();
    }

    @GET
    public Response getUserName(@HeaderParam("Authorization") String RFID) {
        User foundUser;
        try {
            foundUser = userTable.getUserByRFID(RFID);
        } catch (Exception notFound) {
            return Response
                    .status(403)
                    .type("plain/text")
                    .entity("User wasn't found")
                    .build();
        }
        String json = gson.toJson(foundUser);
        return Response
                .status(200)
                .type("application/json")
                .entity(json)
                .build();
    }
}
