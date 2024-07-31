package com.sh.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReponseDto {
    private String comment;

    @Builder
    public CommentReponseDto(String comment) {
        this.comment = comment;
    }
}
