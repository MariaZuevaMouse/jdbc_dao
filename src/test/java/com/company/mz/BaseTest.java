package com.company.mz;

import com.company.mz.dao.implementation.OrderVsMenuDaoSQLiteImpl;
import com.company.mz.entity.Guest;
import com.company.mz.entity.MenuItems;
import com.company.mz.entity.Orders;
import com.company.mz.service.GuestService;
import com.company.mz.service.MenuService;
import com.company.mz.service.OrderService;
import com.company.mz.util.DatabaseType;
import com.github.javafaker.Faker;
import org.testng.annotations.*;

public class BaseTest {
    protected GuestService guestService;
    protected MenuService menuService;
    protected OrderService orderService;
    protected OrderVsMenuDaoSQLiteImpl orderVsMenu;

    protected Guest testGuest;
    protected Guest testGuest2;
    protected Guest testGuest3;
    protected Guest readGuest;
    protected MenuItems testMenuItem;
    protected MenuItems testMenuItem2;
    protected MenuItems testMenuItem3;
    protected MenuItems readMenuItem;
    protected Orders testOrder;
    protected Orders testOrder2;
    protected Orders testOrder3;
    protected Orders readOrder;

    protected Faker faker = new Faker();

    @BeforeClass
    public void beforeClass() {
        guestService = new GuestService(DatabaseType.TEST);
        menuService = new MenuService(DatabaseType.TEST);
        orderService = new OrderService(DatabaseType.TEST);
        orderVsMenu = new OrderVsMenuDaoSQLiteImpl(DatabaseType.TEST);
    }

    protected void removeTestMenu() {
        menuService.delete(testMenuItem);
        menuService.delete(testMenuItem2);
        menuService.delete(testMenuItem3);
    }

    protected void createTestMenu() {
        testMenuItem = new MenuItems().withDishName(faker.food().dish()).withPrice(1+(int)(Math.random()*10));
        menuService.create(testMenuItem);
        testMenuItem = menuService.read(testMenuItem.getDishName());

        testMenuItem2 = new MenuItems().withDishName(faker.food().vegetable()).withPrice(1+(int)(Math.random()*10));
        menuService.create(testMenuItem2);
        testMenuItem2 = menuService.read(testMenuItem2.getDishName());

        testMenuItem3 = new MenuItems().withDishName(faker.food().sushi()).withPrice(1+(int)(Math.random()*10));
        menuService.create(testMenuItem3);
        testMenuItem3 = menuService.read(testMenuItem3.getDishName());
    }

}

//    @BeforeGroups("order vs menu")
//    public void setUpOrderTest() {
//        createTestMenu();
//
//        Orders[] threeOrders = new Orders[]{testOrder, testOrder2, testOrder3};
//        Guest[] threeGuest = new Guest[]{testGuest, testGuest2, testGuest3};
//
//        for (int i = 1; i < 4; i++) {
//            threeGuest[i-1] = new Guest().withName(faker.name().fullName());
//            guestService.create(threeGuest[i-1]);
//            threeGuest[i-1] = guestService.read(threeGuest[i-1].getName());
//
//            threeOrders[i-1] = new Orders();
//            orderService.create(threeOrders[i-1]);
//            threeOrders[i-1] = orderService.read(threeGuest[i-1]);
//
//            createOrderVsMenuData(threeOrders[i-1], i);
//        }
//
////        testGuest = new Guest().withName(faker.name().fullName());
////        guestService.create(testGuest);
////        testGuest = guestService.read(testGuest.getName());
////
////        testOrder = new Orders();
////        orderService.create(testOrder);
////        testOrder = orderService.read(testGuest);
////
////        orderVsMenu.create(testOrder, testMenuItem, 1);
////        orderVsMenu.create(testOrder, testMenuItem2, 1);
////        orderVsMenu.create(testOrder, testMenuItem3, 1);
//
//
//    }

//    protected void createOrderVsMenuData( Orders inOrder, int qty) {
////        guest = new Guest().withName(faker.name().fullName());
////        guestService.create(guest);
////        guest = guestService.read(guest.getName());
////
////        inOrder = new Orders();
////        orderService.create(inOrder);
////        inOrder = orderService.read(guest);
//
//        orderVsMenu.create(inOrder, testMenuItem, qty);
//        orderVsMenu.create(inOrder, testMenuItem2, qty);
//        orderVsMenu.create(inOrder, testMenuItem3, qty);
//    }

//    @AfterGroups("order vs menu")
//    public void tearDownOrderTest() {
//        removeTestMenu();
//    }