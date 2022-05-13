package com.yfyy.service;

import com.yfyy.domain.JWTKey;
import com.yfyy.domain.JwtKeyVO;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/12 17:29
 */
public interface JWTKeyService {
    JwtKeyVO getJwtKeyBySysIdOrCreate(String organization) throws NoSuchAlgorithmException;

    JWTKey getJwtKeyByOrganization(String organization);

    boolean validateTokenExpire(Instant createTime);
}
