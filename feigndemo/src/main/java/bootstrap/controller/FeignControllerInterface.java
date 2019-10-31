package bootstrap.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "provider1",/*fallback = FeignError.class*/fallbackFactory = FeignErrorFactory.class)
public interface FeignControllerInterface {

    @GetMapping("/port")
    public String getPort();
}
