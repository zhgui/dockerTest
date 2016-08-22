package com.shark;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(locations = {
		"classpath:spring-common.xml","classpath:spring-config.xml"})
public class AbstractContextControllerTests {

	@Autowired
	protected WebApplicationContext wac;

}
