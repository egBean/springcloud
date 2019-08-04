package bootstrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${server.port}")
    private String port;
    @Value("${foo}")
    private String foo;

    @GetMapping("config")
    public String getConfig(){
        return port+"--"+foo;
    }
}
