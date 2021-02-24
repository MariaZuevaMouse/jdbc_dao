package com.company.mz.entity.statisticentity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVsCost {
    int orderId;
    int totalPrice;
    String guestName;
}
