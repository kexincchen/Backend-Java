//package com.example.springboot.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class OAuth2LoginConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(withDefaults());
//        return http.build();
//    }
//
//    //多方登录共存的方式
//    @Bean
//    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
//        http.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(CustomUserService())));
//        http.oauth2Client();
//        return http.build();
//    }
//
//    private OAuth2UserService<OAuth2UserRequest, OAuth2User> CustomUserService() {
//        //自定义的OAuth2客户端id
//        final String CUSTOM = "customize";
//        final CompositeOAuth2UserService compositeOAuth2UserService = new CompositeOAuth2UserService();
//        //这里可以把所有自定义的实现都初始化进去
//        compositeOAuth2UserService.getUserServiceMap().put(CUSTOM, new CustomOAuth2UserService());
//        return compositeOAuth2UserService;
//    }
//}
//
