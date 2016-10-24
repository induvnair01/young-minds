package com.youngminds.services.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.youngminds.services.modal.User;

@Path("/user")
public interface LoginResource {

	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	Response userLogin(User userId);
	
	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces("application/json")
	Response registerUser(User userId);
}
