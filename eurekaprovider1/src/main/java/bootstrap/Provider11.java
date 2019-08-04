package bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 可以简易部署2个服务。只需改一下端口号就行了
 */
@SpringBootApplication
public class Provider11 {
    public static void main(String[] args) {
        SpringApplication.run(Provider11.class,args);
    }
}
