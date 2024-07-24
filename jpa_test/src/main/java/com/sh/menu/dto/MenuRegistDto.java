package com.sh.menu.dto;

import com.sh.menu.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRegistDto {
    private String menuName;
    private int menuPrice;
    private long categoryCode;
    private OrderableStatus orderableStatus;

    public Menu toMenuDto() {
        return new Menu(null, this.menuName, this.menuPrice, this.categoryCode, this.orderableStatus);
    }
}
