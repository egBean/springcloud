package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;


@RestController
@SpringBootApplication
@MapperScan("com.mapper")
//客户端模式，无refresh_token http://client:secret@localhost:8080/oauth/token?grant_type=client_credentials
//简化模式 http://localhost:8080/oauth/authorize?response_type=token&client_id=client
//账号密码模式 http://client:secret@localhost:8080/oauth/token?username=admin&password=12345&grant_type=password
//授权码模式 http://localhost:8080/oauth/authorize?client_id=client&response_type=code 获取授权码
//通过授权码获取token http://client:secret@localhost:8080/oauth/token post x-www-form-urlencoded grant_type:authorization_code code:'code'
//刷新token http://client:secret@localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFwcCJdLCJhdGkiOiJiNDM1ZWY1NC0xYTZkLTQ4YWEtOTk2ZS1jMmFiMTc2MmZhMzIiLCJleHAiOjE1NzM0NjI5NzQsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiMzQ2NWM5MWEtYjczZi00NWI2LTlhNzUtNDg2YmIxYjcxMjExIiwiY2xpZW50X2lkIjoiY2xpZW50IiwidXNlcm5hbWUiOiJhZG1pbiJ9.hXqG8a7405R6I92z9CKhJ9edi-4SWwHoWYLAUxhjLFf414inrY_WgXKB8sw6ktEeFBMi500vBuXeB9RA80C5LXoLwWyq1E4ZQkLBs1n-N2hPbk5JKHG9Eql8iZLsjUyEwAqEkaVYYeyCGTZq3QE6cqPjosfrf9roG6tRGnbIyUTDZI6_hsdG_Cc9b3rcNjcBTl53G0p3htgQm2Ip9Sj9vrK6dUwlK6e2k-eWB3X4XonLZMMojYzKH3DT8BVIECbZnSB6JenDVafZ7HNNig_Ggevcn6SNp21iBoaNEYRk1hY4mQPu5SDeaEB6D0pjHMgxhMs0fdtbxVGFOJsZr2np6w
public class Oauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

}
