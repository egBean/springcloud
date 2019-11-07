package com.oauth2serverconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.util.Arrays;


/**
 * tokenstore是存储token的地方，jwttokenstore即不存储token，但是需要将token 加密，通过JwtAccessTokenConverter
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    //注入数据源，会自动寻找oauth2的相关表
    @Autowired
    private DataSource dataSource;
    // 注入认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;

    //使用tokenstore存储相关信息。
    @Bean
    public TokenStore tokenStore(){
        //return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(accessTokenConverter()); //使用jwttokenstore在获取授权码时候无法选择scope，可能是个bug。。
    }

    //通过数据源相关表拿到clientDetails相关信息
    @Bean
    public ClientDetailsService jdbcClientDetails(){
        return new JdbcClientDetailsService(dataSource);
    }

    //指定token 增强，可以自己增加相关信息。如果需要获取access_token的接口同时返回refresh_token，
    // 需要在数据库表oauth_client_details的authorized_grant_types字段增加refresh_token字段
    @Bean
    public TokenEnhancer jwtTokenEnhance(){
        return new JwtTokenEnhancer();
    }

    //配置clientdetails信息
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetails());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //oauth2 提供了一些端点,CheckTokenEndpoint,TokenKeyEndpoint.但是这些端点默认是denyAll，无法访问。
        //tokenKeyAccess是给client获取秘钥用的，最好是只在非对称加密时使用，否则会泄露对称加密秘钥。
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    //
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //指定用哪些tokenEnhancer.
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhance(), accessTokenConverter()));
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)//要开启密码模式，必须配置
               // .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);
    }
    //token加密方式
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
//        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyStoreKeyFactory keyStoreKeyFactory =
//                new KeyStoreKeyFactory(new ClassPathResource("key.keystore"), "storepass".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("alias"));
//        return converter;
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("test-secret");
        return converter;
    }


}
