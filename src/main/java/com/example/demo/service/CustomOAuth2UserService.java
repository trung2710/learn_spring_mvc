// xu li thong tin tra ve thi xac thuc google
package com.example.demo.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserService userService;
    

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        
        // Lấy thông tin từ tài khoản Google
        Map<String, Object> attributes = oauth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name=(String)attributes.get("name");
        Role newRole=this.userService.getRoleByName("USER");
        String pw=passwordEncoder.encode("");
        // Ở đây bạn có thể kiểm tra email trong database và cấp quyền phù hợp
        // Ví dụ: nếu email là admin@example.com thì cấp quyền ADMIN
        com.example.demo.domain.User  user =this.userService.getUserByEmail1(email);
        if(user==null){
            //neu chua dang nhap bao gio thi tao 1 user moi
            User newUser=new User();
            newUser.setEmail(email);
            newUser.setFullName(name != null ? name : "Unknown");
            newUser.setRole(newRole);
            newUser.setPassword(pw);
            user=newUser;
            userService.saveUser(newUser);
            // throw new UsernameNotFoundException("User not found");

        }
        String role=user.getRole().getName();
        // Tạo đối tượng OAuth2User với quyền đã xác định
        return new DefaultOAuth2User(
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)),
            attributes,
            "email" // Thuộc tính name principal
        );
    }
}
