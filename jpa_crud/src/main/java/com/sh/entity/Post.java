package com.sh.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Lazy;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter // Setter는 쓰면 안됨 -> OOP에 맞지 않는 성격
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ✨소문자 long을 쓰지 않는 이유 : db에는 null이 저장 되야 함 => null이 들어가야 함! -> 결국에는 클래스를 쓰기 위해 Long을 씀
    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    // Post : comment -> 1 : N
    @OneToMany(
            mappedBy = "post", // 양방향 관계를 할 때 들어가게 됨 -> Comment에 써준 post를 똑같이 써줘야 함
            fetch = FetchType.LAZY
                    )
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // setter를 대신하는 메소드
    public void updateTitle(String title) {
        this.title = title;
    }

    public void saveComment(Comment comment) {
        this.comments.add(comment);
    }
}
