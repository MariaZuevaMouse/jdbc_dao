package com.company.mz.services.order;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import com.company.mz.entity.Orders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderServiceDeleteTest extends BaseTest {

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
    public void testDeleteOrder() {
        orderService.delete(testOrder);
        testOrder = orderService.read(testGuest);

        Assert.assertEquals(testOrder.getId(), 0);
        Assert.assertEquals(testOrder.getGuestId(), 0);
        Assert.assertNull(testOrder.getDate());
    }

    @AfterMethod
    public void tearDown() {
        guestService.delete(testGuest);
    }
}
