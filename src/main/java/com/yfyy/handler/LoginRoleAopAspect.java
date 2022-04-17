package com.yfyy.handler;

import com.yfyy.anno.CheckLogin;
import com.yfyy.domain.User;
import com.yfyy.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: zhang lei
 * @Date: 2022/4/9 22:06
 */
@Component
@Aspect
public class LoginRoleAopAspect {

    @Autowired
    private UserService userService;

    @Pointcut("@annotation(com.yfyy.anno.CheckLogin)")
    public void checkLoginPointcut() {

    }

    @Before("checkLoginPointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        //获取执行方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //通过反射获取@CheckLogin注解
        CheckLogin annotation = method.getAnnotation(CheckLogin.class);
        if(annotation != null) {
            //获取用户信息
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            HttpServletResponse response = requestAttributes.getResponse();
            //session获取
            User user = (User) request.getSession().getAttribute("");
            if(user != null) {
                User newUser = userService.findUserByUserId(user.getUserId());
                if(newUser != null && !newUser.getRoles().contains(annotation.value())) {
                    throw new RuntimeException("当前用户没有此操作权限！");
                }
            }
        }
    }
}
