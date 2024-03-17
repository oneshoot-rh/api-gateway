package ma.youcode.apigateway.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

//@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ExtractTenantIdGatewayFilterFactory filterFactory;

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }


    @Bean
    RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route("one-shoot-main",routeSpec -> routeSpec
                        .path("/one-shoot-main/**")
                        .filters(f -> f
                                .filter(filterFactory.apply(new ExtractTenantIdGatewayFilterFactory.Config()))
                                .filter(new StripPrefixGatewayFilterFactory().apply(c -> c.setParts(1))))
                        .uri("lb://one-shoot-main")
                ).build();

    }
    // @Bean
    // public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
    // return factory -> factory.configureDefault(
    // id -> new Resilience4JConfigBuilder(id)
    // .timeLimiterConfig(TimeLimiterConfig.custom()
    // .timeoutDuration(Duration.ofSeconds(4)).build())
    // .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
    // .build());
    // }

}
