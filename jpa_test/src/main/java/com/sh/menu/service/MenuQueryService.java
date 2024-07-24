package com.sh.menu.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final로 만들면 값이 무조건 있어야 하기 때문에 얘를 써줌
@Transactional(readOnly = true) // 하위 메소드에서는 읽기만 가능, 쓰기 작업은 불가능!! -> 읽기 전용으로 connection이 열림
public class MenuQueryService {
    // 생성자 의존 주입 처리

}
