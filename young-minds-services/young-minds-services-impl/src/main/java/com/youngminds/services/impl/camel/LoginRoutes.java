package com.youngminds.services.impl.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoginRoutes extends RouteBuilder{

	private static final Logger LOGGER=Logger.getLogger(LoginRoutes.class);
	public  LoginRoutes() {
		LOGGER.debug("Login Route invoked  ************** ");
	}
	@Override
	public void configure() throws Exception {
		
		//move route name into a constant
		from("direct:testLoginRoute")
			.routeId("id")
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					LOGGER.debug("login route executed **********************");
					String data="{\"message\":\" 'Hi "+exchange.getIn().getBody()+"' \nThis is a test data \"}";
					exchange.getIn().setBody(data);
				}
			})
		.end();
	}

}
