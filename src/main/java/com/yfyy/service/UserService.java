package com.yfyy.service;

import com.yfyy.domain.User;

import java.util.List;

/**
 * @Author: zhang lei
 * @Date: 2022/4/9 18:00
 */
public interface UserService {

    User findUserByUserId(String userId);

    List<User> getUserWithPage(Integer ps, Integer pn);
}
