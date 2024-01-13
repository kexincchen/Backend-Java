//package com.example.springboot.Config;
//
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.OidcScopes;
//import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
//import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.util.UUID;
//
//@Configuration(proxyBeanMethods = false)
//public class AuthorizationServerConfig {
//
//    //授权端点过滤器链
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
//                new OAuth2AuthorizationServerConfigurer<>();
//        RequestMatcher endpointsMatcher = authorizationServerConfigurer
//                .getEndpointsMatcher();
//
//        http
//                //没有认证会自动跳转到/login页面
//                .exceptionHandling((exceptions) -> exceptions
//                        .authenticationEntryPoint(
//                                new LoginUrlAuthenticationEntryPoint("/login"))
//                )
//
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers(endpointsMatcher).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
//                .apply(authorizationServerConfigurer);
//        return http.build();
//    }
//
//
//    //用于身份验证的过滤器链
//    @Bean
//    @Order(2)
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    //配置主体用户
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }
//
//    //注册客户端
//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                //客户端id
//                .clientId("testClientId")
//                //客户端秘钥，授权服务器需要加密存储
//                .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("testClientSecret"))
//                //授权方法
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                //支持的授权类型
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                //回调地址，支持多个，本地测试不能使用localhost
//                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/customize")
//                .scope(OidcScopes.OPENID)
//                //授权scope
//                .scope("message.read")
//                .scope("userinfo")
//                .scope("message.write")
//                //是否需要授权页面，开启跳转到授权页面，需要手动确认
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//        return new InMemoryRegisteredClientRepository(registeredClient);
//    }
//
//    //token加密
//    @Bean
//    public JWKSource<SecurityContext> jwkSource() {
//        KeyPair keyPair = generateRsaKey();
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        RSAKey rsaKey = new RSAKey.Builder(publicKey)
//                .privateKey(privateKey)
//                .keyID(UUID.randomUUID().toString())
//                .build();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return new ImmutableJWKSet<>(jwkSet);
//    }
//
//    private static KeyPair generateRsaKey() {
//        KeyPair keyPair;
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//            keyPairGenerator.initialize(2048);
//            keyPair = keyPairGenerator.generateKeyPair();
//        }
//        catch (Exception ex) {
//            throw new IllegalStateException(ex);
//        }
//        return keyPair;
//    }
//
//    //配置协议端点，比如/oauth2/authorize、/oauth2/token等
//    @Bean
//    public ProviderSettings providerSettings() {
//        return ProviderSettings.builder().build();
//    }
//
//}
