package com.formaciondbi.springboot.app.eureka.springbootservicioeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringbootServicioEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioEurekaServerApplication.class, args);
	}

}

//ToDo		A PARTIR DE JDK8 HAY QUE AÑADIR LA SIGUIENTE
//			DEPENDENCIA PARA EUREKA SERVER
//
//<dependency>
//<groupId>org.glassfish.jaxb</groupId>
//<artifactId>jaxb-runtime</artifactId>
//</dependency>
