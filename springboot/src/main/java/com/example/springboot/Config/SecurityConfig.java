package com.example.springboot.Config;

//import com.example.springboot.CustomOAuth2UserService;
import com.example.springboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
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
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(form -> form
//                        .loginProcessingUrl("/login")
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .loginPage("/login.html")
//                        .defaultSuccessUrl("/user/hello") // If the login is successful, user will be redirected to this URL.
//                        .permitAll() // We re permitting all for login page
//                )

//                .oauth2ResourceServer((oauth2) -> oauth2
//                        .jwt(Customizer.withDefaults())
//                )
        ;
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


//    @Bean
//    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
//        //自定义用户信息获取实现
//        http.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(new CustomOAuth2UserService())));
//        http.oauth2Client(oauth2 -> oauth2.);
//        return http.build();
//    }



//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return JwtDecoders.fromIssuerLocation("https://my-auth-server.com");
//    }
//    @Autowired
//    UserDetailsServiceImpl userService;

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring() //配置忽略掉的 URL 地址，一般对于静态文件，我们可以采用此操作
//                .requestMatchers("/resources/**");
//    }


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
