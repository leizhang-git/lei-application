package com.yfyy.rest;

import com.yfyy.anno.CheckLogin;
import com.yfyy.service.impl.UserServiceImpl;
import com.yfyy.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhang lei
 * @Date: 2022/4/9 18:06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/find/{userId}")
    public ResultVO<?> findByUserId(@PathVariable String userId) {
        return ResultVO.getSuccess(userService.findUserByUserId(userId));
    }

    @GetMapping("/findAllUser")
    public ResultVO<?> findAllUser(@RequestParam(value = "ps", defaultValue = "100") Integer ps,
                                   @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        return ResultVO.getSuccess(userService.getUserWithPage(ps, pn));
    }

    @CheckLogin
    @GetMapping("/delete/{userId}")
    public ResultVO<?> deleteByUserId(@PathVariable String userId) {
        return ResultVO.getSuccess(userService.deleteByUserId(userId));
    }
}
