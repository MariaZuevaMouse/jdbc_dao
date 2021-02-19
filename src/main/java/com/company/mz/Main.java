package com.company.mz;

import com.company.mz.dao.implementation.GuestDaoSQLiteImpl;
import com.company.mz.dao.implementation.MenuItemsDaoSQLiteImpl;
import com.company.mz.dao.implementation.OrderVsMenuDaoSQLiteImpl;
import com.company.mz.dao.implementation.OrdersDaoSQLiteImpl;
import com.company.mz.dao.interfaces.CrudDao;
import com.company.mz.dao.interfaces.RelationsCrud;
import com.company.mz.entity.Guest;
import com.company.mz.entity.MenuItems;
import com.company.mz.entity.OrderVsMenuItems;
import com.company.mz.entity.Orders;
import com.company.mz.service.GuestService;
import com.company.mz.service.OrderService;
import com.company.mz.util.DatabaseType;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Main {
    private static Faker faker = new Faker();
    static CrudDao guestTable = new GuestDaoSQLiteImpl(DatabaseType.REAL);
    static CrudDao menuTable = new MenuItemsDaoSQLiteImpl(DatabaseType.REAL);
    static CrudDao orderTable = new OrdersDaoSQLiteImpl(DatabaseType.REAL);
    static RelationsCrud orderVsMenuTable = new OrderVsMenuDaoSQLiteImpl(DatabaseType.REAL);
    static String testGuestName;
    static Guest testGuest;


    public static void main(String[] args) {
        //        CRUD Guest
//        CRUDGuest();

        //CRUD menu
//        CRUDMenu();

        //CRUD Orders
//        CRUDOrders();

        //Crud Order Vs Menu
//        CRUDOrderVsMenu();


//        createTestMenu();
//        for (int i = 0; i < 3; i++) {
//            testDataBaseData();
//        }
//        GuestService guestService = new GuestService();
//        guestService.getAllGuestList();
//        guestService.getAllGuestListAscOrder();
//        guestService.getAllGuestListDescOrder();


        LocalDate dateOne = LocalDate.of(2021,02,16);
        OrderService orderService = new OrderService(DatabaseType.REAL);
        orderService.getAllOrders();
        orderService.getAllOrderInDate(dateOne);
        LocalTime time = LocalTime.of(00, 00);
        LocalDateTime dateTime = LocalDateTime.of(dateOne,time);


    }

    private static void testDataBaseData() {
        testGuest = new Guest().withName(faker.name().fullName());
        guestTable.create(testGuest);
        testGuest = (Guest) guestTable.read(testGuest.getName());
        Orders order = new Orders();
        orderTable.create(order);
        order = (Orders) orderTable.read(testGuest);
//        MenuItems menuItem1 = (MenuItems) menuTable.read("Cheeseburger");
//        MenuItems menuItem2 = (MenuItems) menuTable.read("Sushi");
//        MenuItems menuItem3 = (MenuItems) menuTable.read("Bruschette with Tomato");
        MenuItems menuItem4 = (MenuItems) menuTable.read("pizza");
        MenuItems menuItem5 = (MenuItems) menuTable.read("Chicken Milanese");
        MenuItems menuItem6 = (MenuItems) menuTable.read("Poutine");
        MenuItems menuItem7 = (MenuItems) menuTable.read("Meatballs with Sauce");
//        orderVsMenuTable.create(order, menuItem1, (int) Math.random()*10);
//        orderVsMenuTable.create(order, menuItem2, (int) Math.random()*10);
//        orderVsMenuTable.create(order, menuItem3, (int) Math.random()*10);
        orderVsMenuTable.create(order, menuItem4, 1+(int) Math.random()*10);
        orderVsMenuTable.create(order, menuItem5, 1+(int) Math.random()*10);
        orderVsMenuTable.create(order, menuItem6, 1+(int) Math.random()*10);
        orderVsMenuTable.create(order, menuItem7, 1+(int) Math.random()*10);
    }

    private static void createTestMenu() {
        for (int i = 0; i < 6; i++) {
            MenuItems menuItem = new MenuItems().withDishName(faker.food().dish()).withPrice((int)(Math.random()*1000));
            menuTable.create(menuItem);
        }
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
        menuTable.update( dish1);
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
        guestTable.update(readGuest);
        readGuest = (Guest) guestTable.read(newNameGuest);

        System.out.println(readGuest);
        guestTable.delete(readGuest);
    }

}
