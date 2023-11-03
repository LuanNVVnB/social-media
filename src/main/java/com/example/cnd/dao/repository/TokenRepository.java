package com.example.cnd.dao.repository;

import com.example.cnd.dao.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * File: com.example.cnd.dao.repository
 * Description:  <div style="font-weight: bold; color: #0073e6;"> TokenRepository </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(" select t from Token t inner join User u on t.user.id = u.id " +
            "where u.id = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(@Param("id") Long id);

    Optional<Token> findByToken(@Param("token") String token);
}
