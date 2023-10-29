package com.example.cnd.dao.repository;

import com.example.cnd.dao.entity.Token;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(" select t from Token t inner join User u on t.user.id = u.id " +
            "where u.id = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(@Param("id") Long id);

    Optional<Token> findByToken(@Param("token") String token);
}
