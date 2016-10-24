package com.youngminds.services.impl.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youngminds.dao.UserDao;
import com.youngminds.services.modal.User;

@Component
public class LoginRoutes extends RouteBuilder{

	private static final Logger LOGGER=Logger.getLogger(LoginRoutes.class);
	
	public static final String VALIDATE_USER_ROUTE = "direct:validateUserRoute";
	public static final String REGISTER_USER_ROUTE = "direct:registerUserRoute";
	
	@Autowired
	private UserDao userDao;
	
	public  LoginRoutes() {
		LOGGER.debug("Login Route invoked  ************** ");
	}
	@Override
	public void configure() throws Exception {
		
		from(VALIDATE_USER_ROUTE)
			.routeId(VALIDATE_USER_ROUTE)
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					Boolean isUserExists = userDao.validateUser(exchange.getIn().getBody(User.class));
					String data = null;
					if(isUserExists){
						data = "The given mobile no and passward is valid";
					}
					exchange.getIn().setBody(data);
				}
			})
		.end();
		
		from(REGISTER_USER_ROUTE)
			.routeId(REGISTER_USER_ROUTE)
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					LOGGER.debug("login route executed **********************");
					String data="{\"message\":\" 'registered user details "+exchange.getIn().getBody(User.class);
					userDao.inserUser(exchange.getIn().getBody(User.class));
					exchange.getIn().setBody(data);
				}
			})
		.end();
	}

}
