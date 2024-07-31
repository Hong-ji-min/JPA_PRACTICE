package com.sh.controller;

import com.sh.dto.CommentRequestDto;
import com.sh.dto.PostRequestDto;
import com.sh.dto.PostResponseDto;
import com.sh.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts") // restapi에서 게시물이 다수기 때문에 복수형으로 씀
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    //findAll == pagination을 공부해야함
    @GetMapping("")
    public List<PostResponseDto> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {

        return postService.findAll(pageable);
    }

    //findOne
    @GetMapping("/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findOne(id);
    }

    // create = save
    // form, json으로도 보낼 수도 있음
    @PostMapping("")
    // entity requestBody로 받으면 안됨
    public Long save(@RequestBody PostRequestDto postRequestDto) { // json 데이터를 받으려면 @RequestBody를 써야함
        return postService.save(postRequestDto);
    }

    //update
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        // @PathVariable 쓰는 이유 : id 값을 받아오기 위함 -> 결국에 db에 몇번 id의 etity를 바꿀 건지를 정하기 위함
        return postService.update(id, postRequestDto);
    }

    //delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    // ===============================================================================================
    // Comment

    // post/{id}/comments
    @PostMapping("/{id}/comments")
    public Long saveComment(@PathVariable(value = "id") Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        return postService.saveComment(postId, commentRequestDto);
    }

}
