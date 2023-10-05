package ma.youcode.apigateway.resource;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import ma.youcode.apigateway.dto.ProcessRequestDto;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class ProcessRequestResource {

    private final WebClient.Builder webClientBuilder;

    public ProcessRequestResource(WebClient.Builder wBuilder) {
        this.webClientBuilder = wBuilder;
    }

    @PostMapping("/process")
    public ResponseEntity<Flux<String>> processRequest(@RequestBody ProcessRequestDto request) {
        log.info("calling one shoot service to process {}", request);
        return ResponseEntity.ok(
                webClientBuilder.build()
                        .post().uri("http://one-shoot-service/api/v1/process-request")
                        .bodyValue(request)
                        .retrieve().bodyToFlux(String.class));

    }
}
