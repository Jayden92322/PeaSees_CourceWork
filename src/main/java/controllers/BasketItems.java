package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("BasketItems/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class BasketItems{
    @GET
    @Path("list")
    public String BasketsList() {
        System.out.println("Invoked Baskets.BasketsList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT BasketID, PartID, Quantity FROM Baskets");
            ResultSet results = ps.executeQuery();
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("BasketID", results.getInt(1));
                row.put("UserID", results.getInt(2));
                row.put("PartID", results.getInt(3));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }
    @POST
    @Path("add")
    public String BasketAdd(@FormDataParam("Title") String Title) {
        System.out.println("Invoked Baskets.BasketsAdd()");
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Baskets (Title) VALUES (?)");
            ps.setString(1, Title);
            ps.execute();
            return "{\"OK\": \"Added Basket.\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to create new item, please see server console for more info.\"}";
        }

    }
}