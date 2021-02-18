package com.company.mz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class MenuItems {
    private int id;
    private String dishName;
    private int price;

}
