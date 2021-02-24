package com.company.mz.services.order;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import com.company.mz.entity.MenuItems;
import com.company.mz.entity.Orders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class OrderServiceCreateTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        testGuest = new Guest().withName(faker.name().fullName());
        guestService.create(testGuest);
        testGuest = guestService.read(testGuest.getName());
        testOrder = new Orders();
    }

    @Test (groups = "order group")
    public void testCreateOrder() {
        orderService.create(testOrder);
        testOrder = orderService.read(testGuest);

        Assert.assertTrue(testOrder.getDate().toLocalDate().equals(LocalDate.now()));
        Assert.assertEquals(testOrder.getGuestId(), testGuest.getId());
    }

    @AfterMethod
    public void tearDown() {

        orderService.delete(testOrder);
        guestService.delete(testGuest);
    }

//    private void removeTestMenu() {
//        menuService.delete(testMenuItem);
//        menuService.delete(testMenuItem2);
//        menuService.delete(testMenuItem3);
//    }
//
//    private void createTestMenu() {
//        testMenuItem = new MenuItems().withDishName(faker.food().dish()).withPrice(1+(int)(Math.random()*10));
//        menuService.create(testMenuItem);
//        testMenuItem = menuService.read(testMenuItem.getDishName());
//
//        testMenuItem2 = new MenuItems().withDishName(faker.food().dish()).withPrice(1+(int)(Math.random()*10));
//        menuService.create(testMenuItem);
//        testMenuItem2 = menuService.read(testMenuItem.getDishName());
//
//        testMenuItem3 = new MenuItems().withDishName(faker.food().dish()).withPrice(1+(int)(Math.random()*10));
//        menuService.create(testMenuItem);
//        testMenuItem3 = menuService.read(testMenuItem.getDishName());
//    }
}
