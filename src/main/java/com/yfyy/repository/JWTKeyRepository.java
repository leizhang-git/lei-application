package com.yfyy.repository;

import com.yfyy.domain.JWTKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/13 15:01
 */
@Repository
public interface JWTKeyRepository extends JpaRepository<JWTKey, String> {

    List<JWTKey> findAllByOrganizationAndPrivateKey(String organization, String privateKey);

    List<JWTKey> findAllByOrganization(String organization);

}
