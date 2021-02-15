package com.company.mz;

import com.company.mz.dao.*;
import com.company.mz.entity.Guest;
import com.company.mz.entity.MenuItems;
import com.company.mz.entity.OrderVsMenuItems;
import com.company.mz.entity.Orders;
import com.github.javafaker.Faker;

import java.awt.*;
import java.time.LocalDateTime;

public class Main {
    private static Faker faker = new Faker();
    static CrudDao guestTable = new GuestDao();
    static CrudDao menuTable = new MenuItemsDao();
    static CrudDao orderTable = new OrdersDao();
    static RelationsCrud orderVsMenuTable = new OrderVsMenuDao();
    static String testGuestName;
    static Guest testGuest;


    public static void main(String[] args) {
        //        CRUD Guest
        CRUDGuest();

        //CRUD menu
        CRUDMenu();

        //CRUD Orders
        CRUDOrders();

        //Crud Order Vs Menu
        CRUDOrderVsMenu();


    }

    private static void CRUDOrderVsMenu() {
        Guest guest = new Guest().withName(faker.name().fullName());
        guestTable.create(guest);

        Orders order = new Orders();
        orderTable.create(order);
        Guest guestRead = (Guest) guestTable.read("Miss Danny Ernser");
        Orders readOrder = (Orders) orderTable.read(guestRead);
        OrderVsMenuItems orderVsMenuItems = new OrderVsMenuItems();
        MenuItems menuItems  = (MenuItems)menuTable.read("French Toast");
        MenuItems menuItems2  = (MenuItems)menuTable.read("Ebiten maki");
//        orderVsMenuItems.setMenuItemId(menuItems.getId());
//        orderVsMenuItems.setOrderId(readOrder.getId());
//        orderVsMenuItems.setQuantity(3);
        orderVsMenuTable.create(order, menuItems, 6);
        OrderVsMenuItems readOrderItem = orderVsMenuTable.read(readOrder, menuItems);
        System.out.println(readOrderItem);
//        orderVsMenuTable.update(readOrder, menuItems,5);
        orderVsMenuItems = orderVsMenuTable.read(readOrder, menuItems2);
        System.out.println(orderVsMenuItems);
        orderVsMenuTable.delete(readOrder, menuItems);
    }

    private static void CRUDOrders() {
        Orders order = new Orders();
//        testGuest = new Guest();
//        testGuestName = faker.name().fullName();
//        testGuest.setName(testGuestName);
//        guestTable.create(testGuest);
        Guest readGuest =(Guest) guestTable.read("Miss Danny Ernser");
        System.out.println(readGuest);
//        orderTable.create(order);
        Orders orders = (Orders) orderTable.read(readGuest);
//        System.out.println(orders);
//        Guest guestUpdated  =(Guest) guestTable.read("Luciano Treutel");
//        System.out.println(guestUpdated);
//        orderTable.update(orders,guestUpdated );
        orderTable.delete(orders);
    }

    private static void CRUDMenu() {
        MenuItems dish1 = new MenuItems();
        String dishName1 = faker.food().dish();
        int priceFirst = 1000; //(int)(Math.random()*1000)
        int priceUpdated = 1500;
        dish1.setDishName(dishName1);
        dish1.setPrice(priceFirst);
        menuTable.create(dish1);
        dish1 = (MenuItems) menuTable.read(dish1.getDishName());
//        dish1 = (MenuItems) menuTable.read("Pasta Carbonara");
        System.out.println(dish1);
        menuTable.update( dish1, priceUpdated);
        menuTable.delete(dish1);
    }

    private static void CRUDGuest() {
        Guest guest ;
        String nameGuest = faker.name().firstName();
        String newNameGuest = faker.name().fullName();
        guest  = new Guest().withName(nameGuest);


        guestTable.create(guest);
        Guest readGuest = (Guest) guestTable.read(nameGuest);
        System.out.println(readGuest);
        guestTable.update(readGuest, newNameGuest);
        readGuest = (Guest) guestTable.read(newNameGuest);

        System.out.println(readGuest);
        guestTable.delete(readGuest);
    }

}
