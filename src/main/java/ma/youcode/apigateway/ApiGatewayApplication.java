package ma.youcode.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
// import org.springframework.security.config.web.server.ServerHttpSecurity;
// import org.springframework.security.web.server.SecurityWebFilterChain;
// import org.springframework.security.config.Customizer;

@SpringBootApplication
//@EnableWebFluxSecurity
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
    // @Bean
    // SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    //     return http
    //             .authorizeExchange(exchange -> exchange.matchers(EndpointRequest.toAnyEndpoint()).permitAll()
    //                     .anyExchange().authenticated())
    //             .oauth2Login(Customizer.withDefaults())
    //             .build();
    // }
}
