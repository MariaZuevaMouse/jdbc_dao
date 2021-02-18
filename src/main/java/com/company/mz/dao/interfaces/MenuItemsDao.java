package com.company.mz.dao.interfaces;

import com.company.mz.entity.MenuItems;

import java.util.List;

public interface MenuItemsDao extends  CrudDao<MenuItems, String> {
    List<MenuItems> getAllMenuItems();
    List<MenuItems> getAllMenuItemsOrderByNameAsc();
    List<MenuItems> getAllMenuItemsOrderByNameDesc();
    MenuItems getById(int id);
    MenuItems getPopularMenuItem();
    MenuItems getUnPopularMenuItem();
    MenuItems getMostExpensiveMenuItem();
    MenuItems getCheapestMenuItem();
}
