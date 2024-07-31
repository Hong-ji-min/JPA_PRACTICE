package com.sh.repository;

import com.sh.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// @Repository -> ✨안붙혀도 됨
public interface PostJpaRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);
    // [return type] [findBy<field>And<field> (String field1, String field2)]
    // Optional,Post> findByTitleAndContent(String title, String content)

}
