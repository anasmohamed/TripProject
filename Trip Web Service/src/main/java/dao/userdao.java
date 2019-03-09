/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import pojo.User;

/**
 *
 * @author abdullah
 */
@Path("/hello")
public class userdao {
//for test
    @GET
    public String sayHello(@QueryParam("name") String name) {
        String message = "hello ya " + name;
        return message;
    }

//for test
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("post")
    public User testUser(User user) {
        User u = new User();
        u.setEmail(user.getEmail());
        u.setUserName(user.getUserName());
        return u;
    }

}
