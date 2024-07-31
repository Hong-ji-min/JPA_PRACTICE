package com.sh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 이걸 활성화 시키면 CreateDate를 쓸 수 있음
public class JpaCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaCrudApplication.class, args);
    }

}
