package com.example.cnd.dao.entity;

import com.example.cnd.common.base.EntityBase;
import com.example.cnd.common.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user_detail")
public class UserDetail extends EntityBase {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String firstname;
    private String lastname;
    @Column(name = "full_name")
    private String fullName;

    private GenderEnum gender;
    private String bio;

    @Column(name = "profile_image")
    private String profileImage; // URL hình ảnh hồ sơ
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String address;

}
