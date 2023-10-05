package ma.youcode.apigateway.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
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
