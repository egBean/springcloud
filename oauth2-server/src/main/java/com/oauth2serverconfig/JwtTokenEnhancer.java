package com.oauth2serverconfig;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Object principal = authentication.getPrincipal();
        if(principal instanceof User){
            User user = (User) principal;
            String name = user.getUsername();
            System.out.println(name);
            final Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("username", name);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        }
        return accessToken;
    }

    public static void main(String[] args) {
        System.out.println("hello".replace("e","o"));
    }

}

