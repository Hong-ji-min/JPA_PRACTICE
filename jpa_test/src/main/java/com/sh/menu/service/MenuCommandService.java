package com.sh.menu.service;


import com.sh.menu.dto.MenuRegistDto;
import com.sh.menu.entity.Menu;
import com.sh.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MenuCommandService {
    private final MenuRepository menuRepository;

    public Menu save(MenuRegistDto menuRegistDto) {
        com.sh.menu.entity.Menu menu = menuRegistDto.toMenuDto();
        return menuRepository.save(menu);
    }
}
