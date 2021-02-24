package com.company.mz.service;

import com.company.mz.dao.implementation.MenuItemsDaoSQLiteImpl;
import com.company.mz.entity.MenuItems;
import com.company.mz.util.DatabaseType;

import java.util.List;

public class MenuService {
    MenuItemsDaoSQLiteImpl menuDao;

    public MenuService(DatabaseType databaseType) {
        menuDao = new MenuItemsDaoSQLiteImpl(databaseType);
    }

    public void create(MenuItems menuItems) {
        menuDao.create(menuItems);
        MenuItems readItem = menuDao.read(menuItems.getDishName());
        System.out.println("Menu item was created: " + readItem);
    }

    public MenuItems read(String s) {
        MenuItems menuItem = menuDao.read(s);
        System.out.println("menu item read: " + menuItem);
        return menuItem;
    }

    public void update(MenuItems menuItems) {
        menuDao.update(menuItems);
        System.out.println("Menu item was updated: " + menuDao.read(menuItems.getDishName()));
    }

    public void delete(MenuItems menuItems) {
        menuDao.delete(menuItems);
        System.out.println("Menu item: \"" + menuItems + "\" was removed");
    }

    public List<MenuItems> getAllMenuItems() {
        List<MenuItems> allMenuItems = menuDao.getAllMenuItems();
        allMenuItems.forEach(System.out::println);
        return allMenuItems;
    }

    public List<MenuItems> getAllMenuItemsOrderByNameAsc() {
        List<MenuItems> allMenuItems = menuDao.getAllMenuItemsOrderByNameAsc();
        allMenuItems.forEach(System.out::println);
        return allMenuItems;
    }

    public List<MenuItems> getAllMenuItemsOrderByNameDesc() {
        List<MenuItems> allMenuItems = menuDao.getAllMenuItemsOrderByNameDesc();
        allMenuItems.forEach(System.out::println);
        return allMenuItems;
    }

    public MenuItems getById(int id) {
        MenuItems menuItem = menuDao.getById(id);
        System.out.println("Menu item received by ID: " +menuItem);
        return menuItem;
    }

    public MenuItems getPopularMenuItem(){
        MenuItems menuItem = menuDao.getPopularMenuItem();
        System.out.println("The most popular menu item: " +menuItem);
        return menuItem;
    }

    public MenuItems getUnPopularMenuItem() {
        MenuItems menuItem = menuDao.getPopularMenuItem();
        System.out.println("The unpopular menu item: " +menuItem);
        return menuItem;
    }

    public MenuItems getMostExpensiveMenuItem() {
        MenuItems menuItem = menuDao.getMostExpensiveMenuItem();
        System.out.println("The most expensive menu item: " +menuItem);
        return menuItem;
    }

    public MenuItems getCheapestMenuItem() {
        MenuItems menuItem = menuDao.getCheapestMenuItem();
        System.out.println("The cheapest menu item: " +menuItem);
        return menuItem;
    }
}
