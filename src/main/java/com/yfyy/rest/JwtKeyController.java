package com.yfyy.rest;

import com.yfyy.domain.JWTKey;
import com.yfyy.service.impl.JwtKeyServiceImpl;
import com.yfyy.util.JwtUtil;
import com.yfyy.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/13 15:09
 */
@RestController
@RequestMapping("/jwt")
public class JwtKeyController {

    private final Logger log = LoggerFactory.getLogger(JwtKeyController.class);

    private JwtKeyServiceImpl jwtKeyService;

    public JwtKeyController(JwtKeyServiceImpl jwtKeyService) {
        this.jwtKeyService = jwtKeyService;
    }

    @GetMapping("/token/{organization}")
    public ResultVO<?> getToken(@PathVariable("organization") String organization) {
        try {
            JWTKey jwtKey = jwtKeyService.getJwtKeyByOrganization(organization);
            Map<String, Object> map = new HashMap<>();
            map.put("userId", "defaultId");
            map.put("organization", "default");
            String token = JwtUtil.genToken(jwtKey.getPrivateKey(), map);
            return ResultVO.getSuccess(token);
        } catch (Exception e) {
            log.info("获取key失败", e);
            throw new RuntimeException("获取token失败！");
        }
    }

}
