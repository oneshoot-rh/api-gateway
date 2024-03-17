// package ma.youcode.apigateway.resource;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
// import reactor.core.publisher.Mono;



// @RestController
// @RequestMapping("/security/test")
// public class SecurityTestController {
    
//     @GetMapping
//     public Mono<String> test(BearerTokenAuthentication authentication) {
//         return Mono.just(authentication == null ? "No authentication" : "Authenticated");
//     }

// }
