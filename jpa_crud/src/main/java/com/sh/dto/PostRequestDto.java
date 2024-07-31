package com.sh.dto;

import com.sh.entity.Comment;
import com.sh.entity.Post;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;


    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
