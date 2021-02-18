package com.company.mz.dao.implementation;

import com.company.mz.dao.interfaces.MenuItemsDao;
import com.company.mz.entity.Guest;
import com.company.mz.entity.MenuItems;
import com.company.mz.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsDaoSQLiteImpl implements MenuItemsDao {
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
    public MenuItems read(String dishName) {
        ResultSet resultSet = null;
        MenuItems menuItem = new MenuItems();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.READ.query)) {
            statement.setString(1, dishName);
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
    public void update(MenuItems menuItems) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.UPDATE.query)) {
            statement.setInt(1, menuItems.getPrice());
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

    @Override
    public List<MenuItems> getAllMenuItems() {
        ResultSet resultSet =null;
        MenuItems menuItem;
        List<MenuItems> menuItemsList = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.GET_ALL.query)){
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                menuItem = new MenuItems();
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setDishName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getInt("price"));
                menuItemsList.add(menuItem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return menuItemsList;
    }

    @Override
    public List<MenuItems> getAllMenuItemsOrderByNameAsc() {
        ResultSet resultSet =null;
        MenuItems menuItem;
        List<MenuItems> menuItemsList = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.GET_ALL_ORDER_BY_NAME_ASC.query)){
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                menuItem = new MenuItems();
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setDishName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getInt("price"));
                menuItemsList.add(menuItem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return menuItemsList;
    }

    @Override
    public List<MenuItems> getAllMenuItemsOrderByNameDesc() {
        ResultSet resultSet =null;
        MenuItems menuItem;
        List<MenuItems> menuItemsList = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.GET_ALL_ORDER_BY_NAME_DESC.query)){
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                menuItem = new MenuItems();
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setDishName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getInt("price"));
                menuItemsList.add(menuItem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return menuItemsList;
    }

    @Override
    public MenuItems getById(int id) {
        ResultSet resultSet = null;
        MenuItems menuItem = new MenuItems();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.READ.query)) {
            statement.setInt(1, id);
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
    public MenuItems getPopularMenuItem() {
        ResultSet resultSet = null;
        MenuItems menuItem =new MenuItems();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.GET_MOST_POPULAR.query)) {
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                menuItem = getById(resultSet.getInt("menu_item_id"));
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
    public MenuItems getUnPopularMenuItem() {
        ResultSet resultSet = null;
        MenuItems menuItem =new MenuItems();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.GET_UNPOPULAR.query)) {
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                menuItem = getById(resultSet.getInt("menu_item_id"));
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
    public MenuItems getMostExpensiveMenuItem() {
        ResultSet resultSet = null;
        MenuItems menuItem = new MenuItems();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.GET_MOST_EXPENSIVE.query)) {
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
    public MenuItems getCheapestMenuItem() {
        ResultSet resultSet = null;
        MenuItems menuItem = new MenuItems();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(MenuItemQueries.GET_CHEAPEST.query)) {
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

    enum MenuItemQueries{
        CREATE("INSERT INTO menu_items (name, price) VALUES (?, ?);"),
        READ("SELECT * FROM menu_items WHERE name = (?);"),
        UPDATE("UPDATE menu_items SET price = (?) where name = (?);"),
        DELETE("DELETE FROM menu_items WHERE name = (?);"),
        GET_ALL("SELECT * FROM menu_items ;"),
        GET_ALL_ORDER_BY_NAME_ASC("SELECT * FROM menu_items ORDER  BY name;"),
        GET_ALL_ORDER_BY_NAME_DESC("SELECT * FROM menu_items ORDER  BY name DESC;"),
        GET_MOST_POPULAR("SELECT menu_item_id, SUM(dish_count) FROM order_vs_menu_items GROUP BY menu_item_id ORDER BY SUM(dish_count)  DESC limit 1 ;"),
        GET_UNPOPULAR("SELECT menu_item_id, SUM(dish_count) FROM order_vs_menu_items GROUP BY menu_item_id ORDER BY SUM(dish_count) limit 1;"),
        GET_MOST_EXPENSIVE("SELECT * FROM menu_items WHERE price =(SELECT MAX(price) FROM menu_items);"),
        GET_CHEAPEST("SELECT * FROM menu_items WHERE price =(SELECT MIN(price) FROM menu_items);");

        String query;

        MenuItemQueries(String query) {
            this.query = query;
        }
    }
}
