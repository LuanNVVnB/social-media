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
@Table(name = "m_comment")
public class Comment extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người đăng bình luận

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post; // Bài viết mà bình luận liên quan đến

    private String content; // Nội dung bình luận
    @Column(name = "comment_date")
    private Date commentDate; // Ngày và thời gian bình luận
}
