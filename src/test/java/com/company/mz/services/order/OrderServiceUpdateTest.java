package com.company.mz.services.order;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import com.company.mz.entity.Orders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class OrderServiceUpdateTest extends BaseTest {
    Guest updatedGuest;
    LocalDateTime actualDate;
    LocalDateTime updatedDate;

    @BeforeMethod
    public void setUp() {
        testGuest = new Guest().withName(faker.name().fullName());
        guestService.create(testGuest);
        testGuest = guestService.read(testGuest.getName());
        testOrder = new Orders();
        orderService.create(testOrder);
        testOrder = orderService.read(testGuest);

        updatedGuest = new Guest().withName(faker.name().fullName());
        guestService.create(updatedGuest);
        updatedGuest = guestService.read(updatedGuest.getName());

        actualDate = testOrder.getDate();
        updatedDate = LocalDateTime.of(actualDate.getYear(),
                actualDate.getMonth(), actualDate.getDayOfMonth()+3,
                actualDate.getHour(), actualDate.getMinute(), actualDate.getSecond());
    }

    @Test
    public void testUpdateOrderGuest() {
        testOrder.setGuestId(updatedGuest.getId());
        orderService.update(testOrder);
        readOrder = orderService.read(updatedGuest);

        Assert.assertEquals(testOrder.getGuestId(), updatedGuest.getId());
    }

    @Test
    public void testUpdateOrderDate() {
        testOrder.setDate(updatedDate);
        orderService.updateOrderDate(testOrder);
        readOrder = orderService.read(testGuest);

        Assert.assertEquals(readOrder.getDate().getDayOfMonth(), updatedDate.getDayOfMonth());

    }

    @AfterMethod
    public void tearDown() {
        orderService.delete(testOrder);
        guestService.delete(testGuest);
        guestService.delete(updatedGuest);
    }
}
