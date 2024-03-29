package bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Example {

    public static void main(String[] args) {
        SpringApplication.run(Example.class,args);
    }

    /*@Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/p1/**")
                        .filters(f -> {
                            f.addRequestHeader("Hello", "World");
                            f.stripPrefix(1);
                            return f;
                        })
                        .uri("lb://provider1"))
                .build();
    }*/

}
