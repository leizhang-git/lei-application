package com.yfyy.repository;


import com.yfyy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * userInfo
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据Id获取用户
     * @param userId
     * @return
     */
    User findByUserId(String userId);

    /**
     * 根据组织获取用户
     * @param organization
     * @return
     */
    User findByOrganization(String organization);

    @Query(value = "select d.userId from User d")
    List<String> findAllUserIds();

}
