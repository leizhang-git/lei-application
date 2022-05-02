package com.yfyy.service;

import com.yfyy.domain.User;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhang lei
 * @Date:2022/4/28 16:17
 */
@Service
public class AnyUserDetailService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(AnyUserDetailService.class);

    @Autowired
    private final UserService userService;

    public AnyUserDetailService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userService.findUserListByName(username);
        if(CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("用户不存在！");
        }

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(users.get(0).getRoles());
        return new org.springframework.security.core.userdetails.User(users.get(0).getFirstName(), users.get(0).getPassword(), simpleGrantedAuthorities);
    }

    /**
     * 权限字符串转换 =》 user,admin   ->   SimpleGrantedAuthority("user") + SimpleGrantedAuthority("admin")
     * @param roleStr 权限角色
     * @return
     */
    public List<SimpleGrantedAuthority> createAuthorities(String roleStr) {
        String[] roles = roleStr.split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
    }
}
