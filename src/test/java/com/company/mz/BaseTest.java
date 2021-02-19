package com.company.mz;

import com.company.mz.entity.Guest;
import com.company.mz.entity.MenuItems;
import com.company.mz.entity.Orders;
import com.company.mz.service.GuestService;
import com.company.mz.service.MenuService;
import com.company.mz.service.OrderService;
import com.github.javafaker.Faker;

public class BaseTest {
    protected GuestService guestService;
    protected MenuService menuService;
    protected OrderService orderService;

    protected Guest testGuest;
    protected Guest readGuest;
    protected MenuItems testMenuItem;
    protected Orders testOrder;

    protected Faker faker = new Faker();

}
