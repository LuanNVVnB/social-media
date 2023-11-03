package com.example.cnd.dao.entity;

import com.example.cnd.common.base.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * File: com.example.cnd.dao.entity
 * Description:  <div style="font-weight: bold; color: #0073e6;"> Setting </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_setting")
public class Setting extends EntityBase {
    @Id
    private String content;

    @Column(name = "salt", nullable = false, unique = true)
    public String salt;

}
