package bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

    @Autowired
    private RestTemplate template;

    @GetMapping("/port")
    public String getPort(){
        return template.getForObject("http://provider1/port",String.class);
    }
}
