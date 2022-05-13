package com.yfyy.service.impl;

import com.yfyy.config.ApplicationProperties;
import com.yfyy.domain.JWTKey;
import com.yfyy.domain.JwtKeyVO;
import com.yfyy.repository.JWTKeyRepository;
import com.yfyy.service.JWTKeyService;
import com.yfyy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/13 13:59
 */
@Service
public class JwtKeyServiceImpl implements JWTKeyService {

    @Autowired
    private JWTKeyRepository jwtKeyRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public static long EIGHT_HOURS_SECONDS = 28800L;

    @Override
    public JwtKeyVO getJwtKeyBySysIdOrCreate(String organization) throws NoSuchAlgorithmException {
        JwtKeyVO jwtKeyVO = new JwtKeyVO();
        JWTKey jwtKey = null;
        List<JWTKey> jwtKeys = jwtKeyRepository.findAllByOrganization(organization);
        if (null == jwtKeys || jwtKeys.size() == 0){
            jwtKey = createKey(organization);
            jwtKey = jwtKeyRepository.save(jwtKey);
        }else{
            jwtKey = jwtKeys.get(0);
            if (!validateTokenExpire(jwtKey.getCreateTime())){
                //过期
                jwtKey = updateKey(jwtKey);
            }
        }
        jwtKeyVO.setKey(jwtKey.getPrivateKey());
        jwtKeyVO.setExpire(jwtKey.getCreateTime().plusSeconds(applicationProperties.getJwt().getExpireTime()).getEpochSecond() - EIGHT_HOURS_SECONDS);
        return jwtKeyVO;
    }

    @Override
    public JWTKey getJwtKeyByOrganization(String organization) {
        return null;
    }

    @Override
    public boolean validateTokenExpire(Instant createTime) {
        return false;
    }

    private JWTKey updateKey(JWTKey jwtKey) throws NoSuchAlgorithmException {
        JWTKey key = createKey(jwtKey.getOrganization());
        key.setId(jwtKey.getId());
        key.setCreateTime(Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)));
        return jwtKeyRepository.save(key);
    }

    private JWTKey createKey(String organization) throws NoSuchAlgorithmException {
        JWTKey jwtKey = new JWTKey();
        jwtKey.setOrganization(organization);
        JwtUtil.RSAKeyPair keyPair = JwtUtil.getKeyPair();
        jwtKey.setPrivateKey(keyPair.getPrivateKey());
        jwtKey.setPublicKey(keyPair.getPublicKey());
        return jwtKey;
    }
}
