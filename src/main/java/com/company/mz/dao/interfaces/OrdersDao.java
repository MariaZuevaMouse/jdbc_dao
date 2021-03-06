package com.company.mz.dao.interfaces;

import com.company.mz.entity.Guest;
import com.company.mz.entity.Orders;
import com.company.mz.entity.statisticentity.OrderVsCost;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrdersDao extends CrudDao<Orders, Guest> {
    void updateOrderDate(Orders order);
    List<Orders> getAllOrders();
    List<Orders> getAllOrderInDate(LocalDate localDate);
    List<OrderVsCost> getAllOrderWithTotalCost();
    Orders getOrderById(int id);
    Orders getLastOrder();
    Map<Orders,Integer> getTheMostExpensive();
    Map<Orders,Integer>getTheCheapest();
}
