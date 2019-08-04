package bootstrap.controller;

import org.springframework.stereotype.Component;

@Component
public class FeignError implements FeignControllerInterface {
    @Override
    public String getPort() {
        return "provider1维护中";
    }
}
