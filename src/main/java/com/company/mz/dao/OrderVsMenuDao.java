package com.company.mz.dao;

import com.company.mz.entity.MenuItems;
import com.company.mz.entity.OrderVsMenuItems;
import com.company.mz.entity.Orders;
import com.company.mz.util.DBConnection;

//import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderVsMenuDao implements RelationsCrud {
    @Override
    public void create(Orders order, MenuItems menuItem, int quantity) {
        OrderVsMenuItems checkExist = read(order, menuItem);
        if(checkExist.getQuantity() != 0){
            update(order, menuItem, quantity);
        }else {
            try(Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(OrdersVsMenuQueries.CREATE.query)) {
                statement.setInt(1, order.getId());
                statement.setInt(2, menuItem.getId());
                statement.setInt(3, quantity);
                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public OrderVsMenuItems read(Orders order, MenuItems menuItem) {
        ResultSet resultSet = null;
        OrderVsMenuItems orderVsMenuItem = new OrderVsMenuItems();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(OrdersVsMenuQueries.READ.query)) {
            statement.setInt(1, order.getId());
            statement.setInt(2, menuItem.getId());
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                orderVsMenuItem.setOrderId(resultSet.getInt("order_id"));
                orderVsMenuItem.setMenuItemId(resultSet.getInt("menu_item_id"));
                orderVsMenuItem.setQuantity(resultSet.getInt("dish_count"));
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
        return orderVsMenuItem;
    }

    @Override
    public void update(Orders order, MenuItems menuItem, int quantity) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(OrdersVsMenuQueries.UPDATE.query)) {
            statement.setInt(1,quantity);
            statement.setInt(2, order.getId());
            statement.setInt(3, menuItem.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Orders order, MenuItems menuItem) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(OrdersVsMenuQueries.DELETE.query)) {
            statement.setInt(1, order.getId());
            statement.setInt(2, menuItem.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    enum OrdersVsMenuQueries{
        CREATE("INSERT INTO order_vs_menu_items (order_id, menu_item_id, dish_count) VALUES (?, ?, ?);"),
        READ("SELECT * FROM order_vs_menu_items WHERE order_id = (?) AND menu_item_id =(?);"),
        UPDATE("UPDATE order_vs_menu_items SET dish_count = (?) WHERE order_id = (?) AND menu_item_id = (?);"),
        DELETE("DELETE FROM order_vs_menu_items WHERE order_id = (?) AND menu_item_id = (?);"),
        CHECK_ORDER_EXIST("");

        String query;

        OrdersVsMenuQueries(String query) {
            this.query = query;
        }
    }
}
