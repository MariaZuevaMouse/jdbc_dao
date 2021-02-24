package com.company.mz.services.guest.crud;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import com.company.mz.service.GuestService;
import com.company.mz.util.DatabaseType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GuestServiceReadTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
//        createTestGuest();
        testGuest = new Guest().withName(faker.name().fullName());
        guestService.create(testGuest);
        testGuest = guestService.read(testGuest.getName());
    }

    @Test
    public void positiveReadByNameTest() {
        readGuest = guestService.read(testGuest.getName());

        Assert.assertEquals(testGuest.getName(), readGuest.getName());
        Assert.assertEquals(testGuest.getId(), readGuest.getId());
    }

    @Test
    public void positiveReadByIdTest() {
        Guest readGuest = guestService.getGuestById(testGuest.getId());

        Assert.assertEquals(testGuest.getName(), readGuest.getName());
        Assert.assertEquals(testGuest.getId(), readGuest.getId());
    }

    @AfterMethod
    public void tearDown() {
//        removeTestGuest();
        guestService.delete(testGuest);
    }
}
