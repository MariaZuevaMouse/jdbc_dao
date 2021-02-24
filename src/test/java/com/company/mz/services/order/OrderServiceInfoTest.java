package com.company.mz.services.order;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import com.company.mz.entity.Orders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceInfoTest extends BaseTest {

    LocalDateTime actualDate;
    LocalDateTime updatedDate;

    Guest[] guestsAll = new Guest[]{testGuest, testGuest2, testGuest3};
    Orders[] ordersAll = new Orders[]{testOrder, testOrder2, testOrder3};

    @BeforeMethod
    public void setUp() {
        for (int i = 0; i < 3; i++) {
            guestsAll[i] = new Guest().withName(faker.name().fullName());
            guestService.create(guestsAll[i]);
            guestsAll[i] = guestService.read(guestsAll[i].getName());
            ordersAll[i] = new Orders();
            orderService.create(ordersAll[i]);
            ordersAll[i] = orderService.read(guestsAll[i]);
        }
        actualDate = ordersAll[0].getDate();
        updatedDate = LocalDateTime.of(actualDate.getYear(),
                actualDate.getMonth(), actualDate.getDayOfMonth()+3,
                actualDate.getHour(), actualDate.getMinute(), actualDate.getSecond());
        System.out.println(actualDate)
        ;
        System.out.println(updatedDate);
        ordersAll[0].setDate(updatedDate);
        orderService.updateOrderDate(ordersAll[0]);
    }

    @Test
    public void testGetAllOrders() {
        List<Orders> allOrders = orderService.getAllOrders();

        Assert.assertEquals(allOrders.size(), 3);
    }

    @Test
    public void testGetAllOrdersInDate() {
        List<Orders> allOrderInDate = orderService.getAllOrderInDate(actualDate.toLocalDate());

        Assert.assertEquals(allOrderInDate.size(), 2);

        allOrderInDate = orderService.getAllOrderInDate(updatedDate.toLocalDate());

        Assert.assertEquals(allOrderInDate.size(), 1);
    }

    @AfterMethod
    public void tearDown() {
        for (int i = 0; i < 3; i++) {
            orderService.delete(ordersAll[i]);
            guestService.delete(guestsAll[i]);
        }
    }

}
