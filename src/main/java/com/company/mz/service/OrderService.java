package com.company.mz.service;

import com.company.mz.dao.implementation.OrdersDaoSQLiteImpl;
import com.company.mz.entity.Guest;
import com.company.mz.entity.Orders;
import com.company.mz.util.DatabaseType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class OrderService {
    private OrdersDaoSQLiteImpl ordersDaoSQLite;

    public OrderService(DatabaseType databaseType) {
        ordersDaoSQLite = new OrdersDaoSQLiteImpl(databaseType);
    }

    public void create(Orders orders) {
        ordersDaoSQLite.create(orders);
        System.out.println("new order was created");
        System.out.println(ordersDaoSQLite.getLastOrder());
    }

    public Orders read(Guest guest) {
        Orders read = ordersDaoSQLite.read(guest);
        System.out.println("Last Order by guest: " + guest + " was " + read);
        return read;
    }

    public void update(Orders orders) {
        ordersDaoSQLite.update(orders);
        System.out.println("Order was updated: " + orders );
    }

    public void delete(Orders orders) {
        ordersDaoSQLite.delete(orders);
        System.out.println("Order was removed: " + orders);
    }

    public List<Orders> getAllOrders() {
        List<Orders> allOrders = ordersDaoSQLite.getAllOrders();
        System.out.println("\n all order list: ");
        allOrders.forEach(System.out::println);
        return allOrders;
    }

    public List<Orders> getAllOrderInDate(LocalDate localDate) {
        List<Orders> allOrderInDate = ordersDaoSQLite.getAllOrderInDate(localDate);
        System.out.println("all orders in date: " + localDate);
        allOrderInDate.forEach(System.out::println);
        return null;
    }

    public Map<Orders,Integer> getTheMostExpensive() {
        Map<Orders, Integer> mostExpensive = ordersDaoSQLite.getTheMostExpensive();
        for(Map.Entry<Orders, Integer> entry : mostExpensive.entrySet()) {
            System.out.println("The most expensive order was" + entry.getKey() + ". Total order price: "+ entry.getValue());
        }
        return mostExpensive;
    }

    public Map<Orders,Integer> getTheCheapest() {
        Map<Orders, Integer> cheapest = ordersDaoSQLite.getTheCheapest();
        for(Map.Entry<Orders, Integer> entry : cheapest.entrySet()) {
            System.out.println("The most expensive order was" + entry.getKey() + ". Total order price: "+ entry.getValue());
        }
        return cheapest;
    }

}
