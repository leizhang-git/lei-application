package com.yfyy.service;

import com.yfyy.domain.User;
import com.yfyy.domain.dto.UserDTO;

import java.util.List;

/**
 * @Author: zhang lei
 * @Date: 2022/4/9 18:00
 */
public interface UserService {

    User findUserByUserId(String userId);

    List<User> findUserListByName(String name);

    List<User> getUserWithPage(Integer ps, Integer pn);

    Boolean deleteByUserId(String userId);

    void convertUserDTO(User user, UserDTO userDTO);

    User saveUser(User user);
}
