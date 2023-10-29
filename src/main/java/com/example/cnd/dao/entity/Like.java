package com.example.cnd.dao.entity;

import com.example.cnd.common.base.EntityBase;
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
@Table(name = "_like")
public class Like extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người dùng thực hiện lượt thích

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post; // Bài viết mà lượt thích liên quan đến
    @Column(name = "like_date")
    private Date likeDate; // Ngày và thời gian lượt thích được thực hiện
}
