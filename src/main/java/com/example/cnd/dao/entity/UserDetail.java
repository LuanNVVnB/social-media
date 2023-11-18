package com.example.cnd.dao.entity;

import com.example.cnd.common.base.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * File: com.example.cnd.dao.entity
 * Description:  <div style="font-weight: bold; color: #0073e6;"> UserDetail </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_user_detail")
public class UserDetail extends EntityBase {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String firstname;
    private String lastname;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    private Integer gender;
    private String bio;

    @Column(name = "profile_image")
    private String profileImage; // URL avatar of profile
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String address;

}
