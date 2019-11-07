package com.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request)
            throws Exception {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest)model.get("authorizationRequest");
        Set<String> scopes = authorizationRequest.getScope();
        Set<String> nScopes = new HashSet<>();
        for(String scope : scopes){
            nScopes.add("scope."+scope);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("scopeList",nScopes);
        mv.setViewName("grant");
        return mv;
    }

} 