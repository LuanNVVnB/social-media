package com.example.cnd.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user_detail")
public class UserDetail {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;
    private String lastname;
    @Column(name = "full_name")
    private String fullName;
    private String bio;

    @Column(name = "profile_image")
    private String profileImage; // URL hình ảnh hồ sơ
    @Column(name = "birth_date")
    private Date birthDate;
    private String location;
    @Column(name = "registration_date")
    private Date registrationDate; // Ngày đăng ký tài khoản
}
