package com.yfyy.security.jwt;


import com.yfyy.config.ApplicationProperties;
import com.yfyy.domain.JwtUser;
import com.yfyy.domain.User;
import com.yfyy.domain.UserAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/12 17:04
 */
@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    public static long EIGHT_HOURS_SECONDS = 28800L;

    @Autowired
    private ApplicationProperties applicationProperties;

    public Claims getClaimsFromToken(String token, String pk) throws Exception {

        return Jwts.parser().setSigningKey(getPublicKey(pk))
                .parseClaimsJws(token).getBody();
    }

    /**
     * 根据字符串key获取
     * @param key
     * @return
     */
    private PublicKey getPublicKey(String key) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 根据token和公钥获取用户认证信息
     * @param token
     * @param pk
     * @return
     */
    Authentication getAuthentication(String token, String pk) throws Exception {
        Claims claims = getClaimsFromToken(token, pk);

        Collection<? extends GrantedAuthority> authorities = new HashSet<>();
        if (null != claims.get("auth")) {
            authorities =
                    Arrays.stream(claims.get("auth").toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
        }

        JwtUser user = new JwtUser();
        if (claims.get("userId") == null){
            throw new RuntimeException("Jwt userId 不能为空");
        }
        user.setUserId((String) claims.get("userId"));


        if (claims.get("organization") == null){
            throw new RuntimeException("Jwt organization 不能为空");
        }
        user.setOrganization((String) claims.get("organization"));

        if (claims.get("userName") == null){
            throw new RuntimeException("Jwt userName 不能为空");
        }
        user.setFirstName((String) claims.get("userName"));

        if (claims.get("role") != null) {
            user.setRole((String) claims.get("role"));
        }

        log.info("jwt解析内容：userId:"+claims.get("userId")+"  sysTenantId:"+claims.get("sysTenantId")+"  tenantId:"+claims.get("tenantId")+"   userName:"+claims.get("userName")+"  auth:"+claims.get("auth")+"  role:"+claims.get("role"));

        return securityContextAuthentication(user, authorities);
    }

    Authentication getDebugAuthentication() {
        User user = new User();
        user.setId("aaaaaa-bbbbbbbb-ccccccc-ddddddddddd");
        user.setUserId("superAdmin");
        user.setFirstName("超级测试员");
        user.setMobile("00000000");
        user.setEmail("xxxxx@xx.com");
        user.setOrganization("lei-org");
        Collection<? extends GrantedAuthority> authorities = new HashSet<>();
        return securityContextAuthentication(user, authorities);
    }

    /**
     * 封装用户认证信息
     * @param user
     * @param authorities
     * @return
     */
    private Authentication securityContextAuthentication(User user, Collection<? extends GrantedAuthority> authorities) {
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setName(user.getFirstName());
        // 是否认证
        userAuthentication.setAuthenticated(true);
        userAuthentication.setAuthorities(authorities);
        // 主要的用户信息
        userAuthentication.setPrincipal(user);
        return userAuthentication;
    }

    public boolean validateKeyExpire(Instant createTime) {
        return createTime.getEpochSecond() + applicationProperties.getJwt().getExpireTime() >= Instant.now().getEpochSecond() + EIGHT_HOURS_SECONDS;
    }

    public boolean isDebugMode(String token, String organization) {
        return applicationProperties.getJwt().isDebug()
                && applicationProperties.getJwt().getDebugToken().equals(token)
                && applicationProperties.getJwt().getDebugOrganization().equals(organization);
    }
}
