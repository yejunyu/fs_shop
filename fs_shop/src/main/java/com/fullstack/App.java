package com.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 *
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer {
    
	public static void main( String[] args ){
        SpringApplication.run(App.class, args);  
        System.out.println( "-----> start success..." );
    }

}
