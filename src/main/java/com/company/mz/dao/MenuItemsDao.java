package com.company.mz.dao;

import com.company.mz.entity.Guest;
import com.company.mz.entity.MenuItems;
import com.company.mz.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuItemsDao implements CrudDao<MenuItems, MenuItems, Integer> {
    @Override
    public void create(MenuItems menuItems) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.CREATE.query)) {
            statement.setString(1, menuItems.getDishName());
            statement.setInt(2, menuItems.getPrice());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public MenuItems read(MenuItems menuItemCome) {
        ResultSet resultSet = null;
        MenuItems menuItem = new MenuItems();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.READ.query)) {
            statement.setString(1, menuItemCome.getDishName());
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setDishName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getInt("price"));
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
        return menuItem;
    }

    @Override
    public void update(MenuItems menuItems, Integer newPrice) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.UPDATE.query)) {
            statement.setInt(1, newPrice);
            statement.setString(2, menuItems.getDishName());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(MenuItems menuItems) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.DELETE.query)) {
            statement.setString(1, menuItems.getDishName());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    enum MenuItemQueries{
        CREATE("INSERT INTO menu_items (name, price) VALUES (?, ?);"),
        READ("SELECT * FROM menu_items WHERE name = (?);"),
        UPDATE("UPDATE menu_items SET price = (?) where name = (?);"),
        DELETE("DELETE FROM menu_items WHERE name = (?);");

        String query;

        MenuItemQueries(String query) {
            this.query = query;
        }
    }
}
