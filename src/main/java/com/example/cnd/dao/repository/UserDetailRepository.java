package com.example.cnd.dao.repository;

import com.example.cnd.dao.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * File: com.example.cnd.dao.repository
 * Description:  <div style="font-weight: bold; color: #0073e6;"> UserDetailRepository </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    @Query(value = "Select ud.id From UserDetail ud JOIN User u ON u.id = ud.user.id " +
            "Where ud.userName = :userName or u.email = :userName")
    Optional<Long> existsByUserNameOrEmail(@Param("userName") String userName);
}
