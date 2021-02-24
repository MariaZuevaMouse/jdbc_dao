package com.company.mz.services.guest.crud;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import com.company.mz.service.GuestService;
import com.company.mz.util.DatabaseType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GuestServiceDeleteTest extends BaseTest {


    @BeforeMethod
    public void setUp() {
//        createTestGuest();
        testGuest = new Guest().withName(faker.name().fullName());
        guestService.create(testGuest);
        testGuest = guestService.read(testGuest.getName());
    }

    @Test
    public void deleteGuestPositiveTest() {
        guestService.delete(testGuest);
        readGuest = guestService.getGuestById(testGuest.getId());

        Assert.assertNull(readGuest.getName());
    }
}
