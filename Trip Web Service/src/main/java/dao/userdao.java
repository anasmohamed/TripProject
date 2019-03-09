/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.User;

/**
 *
 * @author abdullah
 */
@Path("/hello")
public class userdao {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("test")
    public User getUser() {
        User u = new User();
        u.setE_mail("anaskamal");
        u.setUserName("ahmed");
        return u;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    @Path("post")
    public User testUser(User user) {
        User u = new User();
        u.setE_mail(user.getE_mail());
        u.setUserName(user.getUserName());
        return u;
    }

}
