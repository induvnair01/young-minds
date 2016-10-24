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
		LOGGER.info("****** entered into userLogin()");
		try{
			User data = template.requestBody(LoginRoutes.VALIDATE_USER_ROUTE,user, User.class);
			if(StringUtils.isEmpty(data)){
				LOGGER.debug("The given user details::"+user+" are invalid");
				return Response.status(Status.UNAUTHORIZED).build();
			}
			LOGGER.debug("The given user details::"+user+" are valid");
			return Response.ok(data).build();
		}catch (CamelExecutionException cee){
			LOGGER.error("*******  userLogin::Exception::"+cee);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	@Override
	public Response registerUser(User user) {
		try{
			String data = template.requestBody(LoginRoutes.REGISTER_USER_ROUTE,user, String.class);
			LOGGER.debug("*******  registerUser::"+data);
			return Response.ok(user).build();
		}catch (CamelExecutionException cee){
			LOGGER.error("*******  registerUser::Exception::"+cee);
			return Response.status(500).entity(cee).build();
		}
	}

}
