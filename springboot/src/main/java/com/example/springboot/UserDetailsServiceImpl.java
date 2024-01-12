package com.example.springboot;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 示例：根据用户名从数据库或其他地方加载用户信息
        // 这里仅为示例，应根据实际情况实现

        // 假设我们从数据库获取到用户类型
        String userType = "user"; // 或 "admin"

        if (userType.equals("admin")) {
            return User.withUsername(username)
                    .password("password") // 应从数据库加载
                    .roles("USER", "ADMIN") // 赋予USER和ADMIN角色
                    .build();
        } else {
            return User.withUsername(username)
                    .password("password") // 应从数据库加载
                    .roles("USER") // 只赋予USER角色
                    .build();
        }
    }
}

