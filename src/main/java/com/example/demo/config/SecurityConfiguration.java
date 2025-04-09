package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.UserService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomUserDetailsService(userService);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(
        PasswordEncoder passwordEncoder,
        UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        // authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    } 

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler(){
        return new CustomSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSucessHandler();
    }

    //cau hinh moi phien dang nhap keo dai 30 ngay
    //sau 30 ngay thi se bi xoa di.
    //cau hinh Remember Me
    //Su dung remember me + session tu dong gia han thoi gian het han vao trong database
    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices =
        new SpringSessionRememberMeServices();
        // optionally customize
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    } 

    //hàm này bắt có ở video 117.
    //1 là để custom form login, 2 là để 
     @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //day la cu phap cua spring security version 6, lamda(cham cac ham viet trong dau ngoac)
        http
                .authorizeHttpRequests(authorize -> authorize
                        //DispatcherType.FORWARD: có thể truy cập được đến các trang view của hệ thống
                        //DispatcherType.INCLUDE: cho phép truy cập kèm thêm thông tin của hệ thống như
                        //vào trang chủ thì load lên các sản phẩm trong csdl.
                        .dispatcherTypeMatchers(DispatcherType.FORWARD,
                        DispatcherType.INCLUDE) .permitAll()
                        //cac duong link url cho phep nguoi dung truy cap,
                        // neu khong khai bao se khong the su dung

                        .requestMatchers("/forgot-password","/reset-password","/register","/homepage","/login",
                         "/client/**", "/css/**", "/js/**",
                        "/images/**").permitAll()
                        //tat ca cac request deu phai xac thuc.

                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated())
                
                .sessionManagement((sessionManagement) -> sessionManagement
                        // luon tao session moi
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        //het han session thi tu dong logout
                        .invalidSessionUrl("/logout?expired")
                        //Muon cho phep tai 1 thoi diem co bn thiet bi duoc login cung tai khoan
                        .maximumSessions(1)
                        //false: nguoi thu 2 login se da nguoi thu nhat ra
                        //true: nguoi thu 2 login vao thoi khoan se phair cho nguoi t1 logout.
                        .maxSessionsPreventsLogin(false))
                
                //moi lan logout thi xoa cai cookies di. Va bao hieu cho server session het han
                .logout(logout->logout.deleteCookies("JSESSIONID").invalidateHttpSession(true)) 
                       
                .rememberMe(remember -> remember
                        .rememberMeServices(rememberMeServices()))
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .successHandler(customSuccessHandler())
                        .permitAll())
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-deny")
                        )
                        
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        // .logoutSuccessUrl("/homepage")
                        .logoutSuccessHandler(logoutSuccessHandler())
                        .permitAll()   
                )
                ; 
                
        return http.build();        
    } 


}
