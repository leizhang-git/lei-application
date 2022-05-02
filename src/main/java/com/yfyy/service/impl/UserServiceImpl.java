package com.yfyy.service.impl;

import com.yfyy.domain.User;
import com.yfyy.domain.dto.UserDTO;
import com.yfyy.repository.UserRepository;
import com.yfyy.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    public List<User> findUserListByName(String name) {
        List<User> userNameList = userRepository.findAllByUserName(name);
        if(CollectionUtils.isEmpty(userNameList)) {
            return Collections.emptyList();
        }
        return userNameList;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteByUserId(String userId) {
        try {
            userRepository.deleteByUserId(userId);
        }catch (Exception e) {
            log.error("delete user error.", e);
            throw new RuntimeException("userId" + userId + "不存在");
        }
        return Boolean.TRUE;
    }

    @Override
    public void convertUserDTO(User user, UserDTO userDTO) {
        userDTO = UserDTO.builder().id(user.getId())
                .userId(user.getUserId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .headSculpture(user.getHeadSculpture())
                .mobile(user.getMobile())
                .organization(user.getOrganization())
                .password(user.getPassword())
                .roles(user.getRoles()).build();
    }

    @Override
    public User saveUser(User user) {
        User userInfo = userRepository.save(user);
        return userInfo;
    }
}
