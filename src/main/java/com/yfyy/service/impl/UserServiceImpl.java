package com.yfyy.service.impl;

import com.yfyy.domain.User;
import com.yfyy.repository.UserRepository;
import com.yfyy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhang lei
 * @Date: 2022/4/9 18:01
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            throw new RuntimeException("用户不存在！");
        }
        return user;
    }

    @Override
    public List<User> getUserWithPage(Integer ps, Integer pn) {
        log.info("ps数量 is {}, pn当前页数 is {}", ps, pn);
        if(pn <= 0) {
            pn = 1;
        }
        PageRequest pageRequest = PageRequest.of(pn - 1, ps);
        return userRepository.findAll(pageRequest).getContent();
    }

}
