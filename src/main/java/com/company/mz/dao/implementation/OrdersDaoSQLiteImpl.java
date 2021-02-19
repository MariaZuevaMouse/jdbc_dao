package com.company.mz.dao.implementation;

import com.company.mz.dao.interfaces.OrdersDao;
import com.company.mz.entity.Guest;
import com.company.mz.entity.Orders;
import com.company.mz.util.DBConnection;
import com.company.mz.util.DatabaseType;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersDaoSQLiteImpl extends BaseSQLiteImplClass implements OrdersDao {

    public OrdersDaoSQLiteImpl(DatabaseType type) {
        super(type);
    }

    @Override
    public void create(Orders orders) {
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.CREATE.query)) {
//            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(2, findLastGuest());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Orders getLastOrder(){
        ResultSet resultSet = null;
        Orders order = new Orders();
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.GET_LAST_ORDER.query)) {
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getDate("order_date"));
                order.setGuestId(resultSet.getInt("guest_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return order;
    }

    @Override
    public Orders read(Guest guest) {
        ResultSet resultSet = null;
        Orders order = new Orders();
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.READ.query)) {
            statement.setInt(1, guest.getId());
            statement.setInt(2, guest.getId());
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getDate("order_date"));
                order.setGuestId(resultSet.getInt("guest_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return order;
    }

    public int findLastGuest() {
        Guest guest = new Guest();
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.FIND_LAST_GUEST_ID.query)) {
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                guest.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return guest.getId();
    }

    @Override
    public void update(Orders orders) {
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.UPDATE.query)) {
            statement.setInt(1, orders.getGuestId());
            statement.setInt(2, orders.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Orders orders) {
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.DELETE.query)) {
            statement.setInt(1,orders.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> ordersList = new ArrayList<>();
        ResultSet resultSet = null;
        Orders order ;
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.GET_ALL_ORDERS.query)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                order = new Orders();
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getDate("order_date"));
                order.setGuestId(resultSet.getInt("guest_id"));
                ordersList.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ordersList;
    }

    @Override
    public List<Orders> getAllOrderInDate(LocalDate localDate) {
        List<Orders> ordersList = new ArrayList<>();
        ResultSet resultSet = null;
        Orders order = new Orders();
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.GET_ALL_ORDERS_IN_DATE.query)) {
            LocalTime timeStart = LocalTime.of(00, 00, 00);
            LocalTime timeEnd = LocalTime.of(23, 59, 59);
            System.out.println(Timestamp.valueOf(LocalDateTime.of(localDate, timeStart)));
            statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(localDate, timeStart)));
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(localDate, timeEnd)));
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getDate("order_date"));
                order.setGuestId(resultSet.getInt("guest_id"));
                ordersList.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ordersList;
    }

    @Override
    public Orders getOrderById(int id) {
        ResultSet resultSet = null;
        Orders order = new Orders();
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.GET_ORDER_BY_ID.query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                order.setId(resultSet.getInt("id"));
                order.setDate(resultSet.getDate("order_date"));
                order.setGuestId(resultSet.getInt("guest_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return order;
    }

    @Override
    public Map<Orders,Integer> getTheMostExpensive() {
        ResultSet resultSet = null;
        Orders order = new Orders();
        Map<Orders, Integer> orderVsPrice = new HashMap<>();
        int totalPrice = 0;
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.GET_MOST_EXPENSIVE_ORDER.query)) {
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                order = getOrderById(resultSet.getInt("id"));
                totalPrice = resultSet.getInt("total_price");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        orderVsPrice.put(order, totalPrice);
        return orderVsPrice;
    }

    @Override
    public Map<Orders,Integer> getTheCheapest() {
        ResultSet resultSet = null;
        Orders order = new Orders();
        Map<Orders, Integer> orderVsPrice = new HashMap<>();
        int totalPrice = 0;
        try(PreparedStatement statement = connection.prepareStatement(OrderQueries.GET_CHEAPEST_ORDER.query)) {
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                order = getOrderById(resultSet.getInt("id"));
                totalPrice = resultSet.getInt("total_price");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        orderVsPrice.put(order, totalPrice);
        return orderVsPrice;
    }

    enum OrderQueries{
        CREATE("INSERT INTO orders (order_date, guest_id) VALUES (?, ?);"),
        READ("SELECT * FROM orders WHERE guest_id =(?) AND order_date= (SELECT MAX(order_date) FROM orders WHERE guest_id=(?));"),
        UPDATE("UPDATE orders SET guest_id = (?) WHERE id = (?);"),
        DELETE(" DELETE FROM orders WHERE id = (?);"),
        FIND_LAST_GUEST_ID("SELECT * FROM guest WHERE id = (SELECT MAX(id) FROM GUEST);"),
        GET_ALL_ORDERS("SELECT * FROM orders;"),
        GET_ORDER_BY_ID("SELECT * FROM orders WHERE id=?"),
        GET_LAST_ORDER("SELECT * FROM orders ORDER BY id DESC LIMIT 1;"),
        GET_ALL_ORDERS_IN_DATE("SELECT * FROM orders WHERE order_date BETWEEN ? AND ?;"),
        GET_CHEAPEST_ORDER("SELECT order_id, SUM(dish_count*price) as total_price , name FROM join_all_tabels GROUP BY order_id ORDER BY total_price LIMIT 1;"),
        GET_MOST_EXPENSIVE_ORDER("SELECT order_id, SUM(dish_count*price) as total_price , name FROM join_all_tabels GROUP BY order_id ORDER BY total_price DESC LIMIT 1;");

        String query;

        OrderQueries(String query) {
            this.query = query;
        }
    }
}
