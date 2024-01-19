package com.example.springboot.Config;

import com.example.springboot.Service.UserService;
import com.example.springboot.ServiceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity// 开启网络安全注解
public class SecurityConfig {
//    @Autowired
//    CustomOAuth2UserService userService;

    // 将自定义JwtAuthenticationFilter注入
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private UserDetailsServiceImpl userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        //静态资源依然全部可以访问
//                        .requestMatchers("/").permitAll()
                        //只有具有以下角色的用户才能访问路径"/"
//                        .requestMatchers("/admin/**").hasAnyRole("admin")
                        //其他所有路径必须角色为admin才能访问
//                        .anyRequest().hasRole("admin")
                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()
                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(Customizer.withDefaults())
//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .oidcUserService(this.oidcUserService())
//                    )
//                )
//                .oauth2ResourceServer((oauth2) -> oauth2
//                        .jwt(Customizer.withDefaults())
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//
//                .formLogin(form -> form
//                        .loginProcessingUrl("/login")
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .loginPage("/login.html")
//                        .defaultSuccessUrl("/user/hello") // If the login is successful, user will be redirected to this URL.
//                        .permitAll() // We re permitting all for login page
//                )

        ;
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();

        return (userRequest) -> {
            // Delegate to the default implementation for loading a user
            OidcUser oidcUser = delegate.loadUser(userRequest);
            OAuth2AccessToken accessToken = userRequest.getAccessToken();
            System.out.println("AccessToken: " + accessToken);
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            // TODO
            // 1) Fetch the authority information from the protected resource using accessToken
            // 2) Map the authority information to one or more GrantedAuthority's and add it to mappedAuthorities
            // 3) Create a copy of oidcUser but use the mappedAuthorities instead
            oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

            return oidcUser;
        };
    }

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        // 创建一个用户认证提供者
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        // 设置用户相信信息，可以从数据库中读取、或者缓存、或者配置文件
//        authProvider.setUserDetailsService(userDetailsService());
//        // 设置加密机制，若想要尝试对用户进行身份验证，我们需要知道使用的是什么编码
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> userService.loadUserByUsername(username);
//    }


//    @Bean PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return JwtDecoders.fromIssuerLocation("https://my-auth-server.com");
//    }


//    @Autowired
//    UserDetailsServiceImpl userService;



//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
////                        .requestMatchers("/hello").hasAuthority("user")
////                        .requestMatchers("/resources/**").permitAll()
////                                .requestMatchers("admin/**").hasRole("admin")
////                                .requestMatchers("user/**").hasRole("user")
//                                .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
//
////                .formLogin(form -> form
//////                        .loginProcessingUrl("/login")
//////                        .usernameParameter("name")
//////                        .passwordParameter("passwd")
//////                        .loginPage("/login.html")
////                        .defaultSuccessUrl("/hello") // If the login is successful, user will be redirected to this URL.
////                        .permitAll() // We re permitting all for login page
////                )
////                .csrf(AbstractHttpConfigurer::disable)
////                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
////                .httpBasic(withDefaults())
////                .oauth2ResourceServer(oauth2 -> oauth2
////                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
////                )
////                .addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)
//        ;
//
//        return http.build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user")
//                .password("{noop}user")
//                .roles("USER").build());
//        manager.createUser(User.withUsername("admin")
//                .password("{noop}admin")
//                .roles("ADMIN").build());
//        return manager;
//    }


//    @Bean
//    RoleHierarchy roleHierarchy() {
//        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
//        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
//        return hierarchy;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService());
//        provider.setPasswordEncoder(passwordEncoder());
//        return new ProviderManager(provider);
//    }




}
