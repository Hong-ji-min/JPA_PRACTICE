package com.sh.service;

import com.sh.dto.CommentRequestDto;
import com.sh.dto.PostRequestDto;
import com.sh.dto.PostResponseDto;
import com.sh.entity.Comment;
import com.sh.entity.Post;
import com.sh.repository.CommentRepository;
import com.sh.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // ✨readonly -> get할 때 쓰기 위함
@RequiredArgsConstructor
public class PostService {
    private final PostJpaRepository repository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long save(PostRequestDto postRequestDto) {
        Post save =  repository.save(postRequestDto.toEntity());
        return save.getId();
    }

    public PostResponseDto findOne(Long id) {
        Post result = findById(id);

        //response에 entity를 쓰면 안됨 -> entity를 그대로 반환하면 민감한 정보를 그대로 반환이 될 수도 있는 문제가 생길 수 있음 => ✨responseDto로 변환하는 것 = 보여줄 데이터를 한정해주는 것!
        // comments -> responseDto
        return PostResponseDto.fromPost(result);
    }

    @Transactional // update도 해줘야 함 -> 데이터를 바꾸는 것이기 때문
    public Long update(Long id, PostRequestDto updateDto) {
        /**
         * 조회를 한 다음에 있으면 업데이트 해야 함
         */
        Post result = findById(id);


        // update하는 방법 2개가 있음

        // ✨ 방법 1 ) 변경 감지 ❤️추천❤️
        // 영속성 컨텍스트 엔티티가 존재하는데
        // 비교를 해서 이전에 저장되어 있는 데이터랑 이 메소드가 끝나고 나서의 데이터를 비교함
        // 비교를 햇는데 달라졌다? 그러면 이제 update 쿼리 날려버려~
        // 📌변경 감지의 단점

        result.updateTitle(updateDto.getTitle()); // 여기서 title이 updateTitle로 바꼈다는 것을 jpa가 감지함 = 변.경.감.지
        return id;

        // ✨ 방법 2 ) save Method 비추💀
//        repository.save(entity);
    }

    @Transactional // ✨✨✨ service단에는 꼭 transacional 붙히기!!! ✨✨✨
    public void delete(Long id) {
        Post result = findById(id);
        repository.delete(result);
//        repository.deleteById(id); 위에랑 똑같음!
    }

    public List<PostResponseDto> findAll(Pageable pageable) {
        Page<Post> posts = repository.findAll(pageable);

        // map : 리스트를 순회하면서 넘긴 function method를 실행함
        // 결국 리스트가 0-5까지 있다고 가정
        // 0 function을 실행해서 변환된 결과값을 받음 -> 이걸 5까지 실해함
        // 5까지 다 하고 나서 어떤 collection에 담을 건지? -> 나는 list에 담을 거다 = toList
        return posts.getContent().stream().map(PostResponseDto::fromPost).toList();
    }

    public Post findById(Long id) {
        Optional<Post> savedEntity = repository.findById(id);

        // Exception 없으면 없다고 알려줘야 함, null을 반환하는게 아니라
        return savedEntity.orElseThrow(() -> new NoSuchElementException("not exist post = " + id));
    }

    @Transactional
    public Long saveComment(Long postId, CommentRequestDto commentRequestDto) {
        Post post = findById(postId);
        Comment entity = commentRequestDto.toEntity();

        entity.writeToPost(post);
        Comment save = commentRepository.save(entity);

        return save.getId();
    }

    // ✅ 리팩토링 한 코드 노션에 있음! 참고하기!
}
