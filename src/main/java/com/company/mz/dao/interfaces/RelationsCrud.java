package com.company.mz.dao.interfaces;

import com.company.mz.entity.MenuItems;
import com.company.mz.entity.OrderVsMenuItems;
import com.company.mz.entity.Orders;



public interface RelationsCrud {
    void create(Orders order, MenuItems menuItem, int quantity);
    OrderVsMenuItems read(Orders order, MenuItems menuItem);
    void update(Orders order, MenuItems menuItem, int quantity);
    void delete(Orders order, MenuItems menuItem);

}
