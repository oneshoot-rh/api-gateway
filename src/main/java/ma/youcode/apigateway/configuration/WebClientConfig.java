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

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ExtractTenantIdGatewayFilterFactory filterFactory;

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }
//     id: tenantService
//     uri: lb://one-shoot-main
//     predicates:
//       - Path=/one-shoot-main/**
//       - Host={tenantId}.oneshoot.local, {tenantId}.locahost
//     filters:
//       - RewritePath=/one-shoot-main/(?<segment>.*), /$\{segment}
//       - AddRequestHeader=X-Tenant-Id, {tenantId}
//   - id: genaiservice
//     uri: lb://one-shoot-genai
//     predicates:
//       - Path=/genai/**
//       - Host={tenantId}.oneshoot.local, {tenantId}.locahost
//     filters:
//       - RewritePath=/genai/(?<segment>.*), /$\{segment}
//   - id: onboardingService
//     uri: lb://EOnboardService
//     predicates:
//       - Path=/onboarding/**
//       - Host={tenantId}.oneshoot.local, {tenantId}.locahost
//     filters:
//       - RewritePath=/onboarding/?(?<segment>.*), /$\{segment}

    @Bean
    RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route("tenantService",routeSpec -> routeSpec
                        .path("/tenantService/**")
                        .filters(f -> f
                                .filter(filterFactory.apply(new ExtractTenantIdGatewayFilterFactory.Config()))
                                .filter(new StripPrefixGatewayFilterFactory().apply(c -> c.setParts(1))))
                        .uri("lb://tenantService"))
                .route("genai",routeSpec -> routeSpec
                        .path("/genai/**")
                        .filters(f -> f
                                .filter(filterFactory.apply(new ExtractTenantIdGatewayFilterFactory.Config()))
                                .filter(new StripPrefixGatewayFilterFactory().apply(c -> c.setParts(1))))
                        .uri("lb://one-shoot-genai"))
                .route("onboarding",routeSpec -> routeSpec
                        .path("/onboarding/**")
                        .filters(f -> f
                                .filter(filterFactory.apply(new ExtractTenantIdGatewayFilterFactory.Config()))
                                .filter(new StripPrefixGatewayFilterFactory().apply(c -> c.setParts(1))))
                        .uri("lb://EOnboardService"))
                .build();

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
