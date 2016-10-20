package com.youngminds.services.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.youngminds.services.modal.User;

@Path("/user")
public interface LoginResource {

	@GET
	@Path("/login/{userId}")
	@Produces("application/json")
	Response userLogin(@PathParam("userId") String userId);
	
	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces("application/json")
	Response registerUser(User userId);
}
