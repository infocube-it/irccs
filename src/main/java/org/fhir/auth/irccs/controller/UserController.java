package org.fhir.auth.irccs.controller;

import io.quarkus.security.Authenticated;
import io.quarkus.security.PermissionsAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.fhir.auth.irccs.entity.OrganizationRequest;
import org.fhir.auth.irccs.entity.User;

import java.util.HashMap;
import java.util.List;


@Path("/fhir/auth/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UserController {

    @GET
    @Authenticated
    Response getAllUsers(@QueryParam("email") @DefaultValue("") String email);
    @Path("/signup")
    @POST
    @PermitAll
    String signUp(String user);

    @Path("/create")
    @POST
    @Authenticated
    Response createUser(User user);

    @Path("/me")
    @GET
    @PermitAll
    String me(SecurityContext cx);

    @Path("/organizations")
    @GET
    @PermitAll
    List<OrganizationRequest> organizations(@QueryParam("_count") @DefaultValue("3000") Integer count, @QueryParam("_offset") @DefaultValue("0") Integer offset, @QueryParam("name") @DefaultValue("") String name);

    @Path("/token")
    @Consumes("application/x-www-form-urlencoded")
    @POST
    @PermitAll
    Response tokenExchange(String payload);
    @Path("/logout")
    @Consumes("application/x-www-form-urlencoded")
    @POST
    @PermitAll
    Response logout(String payload);
    @Path("/enable")
    @POST
    @Authenticated
    Response enableUser(@QueryParam("email") @DefaultValue("") String email);
    @PUT
    @Authenticated
    Response updateUser(User user);

    @DELETE
    @Authenticated
    Response deleteUser(String id);

    @Path("/forgotPassword")
    @Consumes("application/json")
    @POST
    @PermitAll
    Response forgotPassword(HashMap<String,String> payload);

    @Path("/updatePassword")
    @Consumes("application/json")
    @PUT
    @PermitAll
    Response updatePassword(HashMap<String,String>  payload);
}

