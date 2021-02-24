package com.company.mz.services.order;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import com.company.mz.entity.Orders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class OrderServiceReadTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        testGuest = new Guest().withName(faker.name().fullName());
        guestService.create(testGuest);
        testGuest = guestService.read(testGuest.getName());
        testOrder = new Orders();
        orderService.create(testOrder);
        testOrder = orderService.read(testGuest);
    }

    @Test
    public void testReadByGuestOrder() {
        readOrder = orderService.read(testGuest);

        Assert.assertTrue(readOrder.getId()>0);
        Assert.assertTrue(readOrder.getDate().toLocalDate().equals(LocalDate.now()));
        Assert.assertEquals(readOrder.getGuestId(), testGuest.getId());
    }

    @Test
    public void testReadOrderById() {
        readOrder = orderService.getOrderById(testOrder.getId());

        Assert.assertTrue(readOrder.getId()>0);
        Assert.assertTrue(readOrder.getDate().toLocalDate().equals(LocalDate.now()));
        Assert.assertEquals(readOrder.getGuestId(), testGuest.getId());
    }

    @Test
    public void testGetLastOrder() {
        readOrder = orderService.getLastOrder();

        Assert.assertTrue(readOrder.getId()>0);
        Assert.assertTrue(readOrder.getDate().toLocalDate().equals(LocalDate.now()));
        Assert.assertEquals(readOrder.getGuestId(), testGuest.getId());
    }

    @AfterMethod
    public void tearDown() {
        orderService.delete(testOrder);
        guestService.delete(testGuest);
    }
}
