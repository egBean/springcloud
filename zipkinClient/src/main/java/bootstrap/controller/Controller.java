package bootstrap.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${server.port}")
    private String port;


    @GetMapping("config")
    public String getConfig(){
        return "zipkinClient:"+port;
    }
}
