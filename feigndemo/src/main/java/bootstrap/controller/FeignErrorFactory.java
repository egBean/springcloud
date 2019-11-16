package bootstrap.controller;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorFactory implements FallbackFactory<FeignControllerInterface> {

    @Override
    public FeignControllerInterface create(Throwable throwable) {
        return () -> {
            String result = throwable.getMessage();
            return result;
        };
    }
}
