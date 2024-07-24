package com.sh.menu.entity;

import com.sh.menu.dto.OrderableStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Menu")
@Table(name = "tbl_menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_code")
    private Long menuCode;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_price")
    private int menuPrice;
    @Column(name = "category_code") // 다른 애그리것을 참조하는 것 -> pk를 들고 와서 참조하기
    private Long categoryCode;
    @Column(name = "orderable_status")
    @Enumerated(EnumType.STRING)
    private OrderableStatus orderableStatus;

    public void changeMenuPrice(int newMenuPrice) {
        this.menuPrice = newMenuPrice;
    }

    public void changeMenuName(String newMenuName) {
        this.menuName = newMenuName;
    }
}
