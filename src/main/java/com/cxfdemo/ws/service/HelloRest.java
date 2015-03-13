package com.cxfdemo.ws.service;

import com.cxfdemo.ws.service.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 功能：
 *
 * Created by ZhouJW on 2015/1/23 11:48.
 */
public interface HelloRest {

    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers();

    @GET
    @Path("/getUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id")String id);

    @GET
    @Path("/getUser2/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public User getUser2(@PathParam("id")String id);

    @POST
    @Path("/saveUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean saveUser(User u);

    @PUT
    @Path("/updateUser/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean updateUser(@PathParam("id")String id, User user);

    @DELETE
    @Path("/updateUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteUser(@PathParam("id")String id);
}
