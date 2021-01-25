package com.company.mz.entity;

import lombok.Data;

@Data
public class OrderVsMenuItems {
    private int orderId;
    private int menuItemId;
    private int quantity;

}
