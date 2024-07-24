package com.sh.menu.repository;

import com.sh.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Menu save(Menu menu);
}
