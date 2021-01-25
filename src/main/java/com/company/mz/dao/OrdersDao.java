package com.company.mz.dao;

import com.company.mz.entity.Guest;
import com.company.mz.entity.Orders;
import com.company.mz.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrdersDao implements CrudDao<Orders, Guest, Guest> {
    @Override
    public void create(Orders orders) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(OrderQueries.CREATE.query)) {
//            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(2, findLastGuest());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Orders read(Guest guest) {
        ResultSet resultSet = null;
        Orders order = new Orders();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(OrderQueries.READ.query)) {
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
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(OrderQueries.FIND_LAST_GUEST_ID.query)) {
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
    public void update(Orders orders, Guest guest) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(OrderQueries.UPDATE.query)) {
            statement.setInt(1, guest.getId());
            statement.setInt(2, orders.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Orders orders) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(OrderQueries.DELETE.query)) {
            statement.setInt(1,orders.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    enum OrderQueries{
        CREATE("INSERT INTO orders (order_date, guest_id) VALUES (?, ?);"),
        READ("SELECT * FROM orders WHERE guest_id =(?) AND order_date= (SELECT MAX(order_date) FROM orders WHERE guest_id=(?));"),
        UPDATE("UPDATE orders SET guest_id = (?) WHERE id = (?);"),
        DELETE(" DELETE FROM orders WHERE id = (?);"),
        FIND_LAST_GUEST_ID("SELECT * FROM guest WHERE id = (SELECT MAX(id) FROM GUEST);"),
        FIND_LAST_GUEST_ORDER("");

//        SELECT * FROM orders WHERE guest_id =2 AND order_date= (SELECT MAX(order_date) FROM orders WHERE guest_id=2);
//        SELECT * FROM orders WHERE guest_id =(?) AND order_date= (SELECT MAX(order_date) FROM orders WHERE guest_id=(?));

        String query;

        OrderQueries(String query) {
            this.query = query;
        }
    }
}
