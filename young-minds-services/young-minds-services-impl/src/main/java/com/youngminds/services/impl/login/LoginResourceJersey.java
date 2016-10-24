package com.youngminds.services.impl.login;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.youngminds.services.api.LoginResource;
import com.youngminds.services.impl.camel.LoginRoutes;
import com.youngminds.services.modal.User;

@Component
public class LoginResourceJersey implements LoginResource{

	private static final Logger LOGGER=Logger.getLogger(LoginResourceJersey.class);
	@Autowired
	private ProducerTemplate template;
	public  LoginResourceJersey() {
		LOGGER.info("*******  login resource jersey constructor");
	}
	@Override
	public Response userLogin(User user) {
		try{
			String data = template.requestBody(LoginRoutes.VALIDATE_USER_ROUTE,user, String.class);
			if(StringUtils.isEmpty(data)){
				return Response.status(Status.UNAUTHORIZED).build();
			}
			return Response.ok(data).build();
		}catch (CamelExecutionException cee){
			//we can handle exception here
			return Response.noContent().build();
		}
		
	}
	@Override
	public Response registerUser(User user) {
		try{
			String data = template.requestBody(LoginRoutes.REGISTER_USER_ROUTE,user, String.class);
			return Response.ok(data).build();
		}catch (CamelExecutionException cee){
			//we can handle exception here
			return Response.status(500).entity(cee).build();
		}
	}

}
