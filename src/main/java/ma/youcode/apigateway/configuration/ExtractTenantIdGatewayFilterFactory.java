package ma.youcode.apigateway.configuration;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class ExtractTenantIdGatewayFilterFactory extends AbstractGatewayFilterFactory<ExtractTenantIdGatewayFilterFactory.Config> {

    public ExtractTenantIdGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String host = exchange.getRequest().getHeaders().getFirst("Host");
            String subdomain = host.split("\\.")[0];
            exchange.getRequest().mutate().header("X-Tenant-Id", subdomain);
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}