package com.sh.dto;


import com.sh.entity.Comment;
import com.sh.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Pool;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
// 노출시킬 데이터를 한정짓기 위해서 responsedto를 작성함
public class PostResponseDto {
    private String title;
    private String content;
    private List<Comment> commentList;


    public static PostResponseDto fromPost(Post post) {
        return new PostResponseDto(post.getTitle(), post.getContent(), post.getComments());
    }
}
