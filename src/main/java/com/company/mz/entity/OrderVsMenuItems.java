package com.company.mz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class OrderVsMenuItems {
    private int orderId;
    private int menuItemId;
    private int quantity;

}
