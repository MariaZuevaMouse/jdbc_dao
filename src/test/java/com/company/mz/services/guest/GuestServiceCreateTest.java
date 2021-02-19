package com.company.mz.services.guest;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import com.company.mz.service.GuestService;
import org.testng.Assert;
import org.testng.annotations.*;

public class GuestServiceCreateTest extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        guestService = new GuestService();
    }

    @Test
    public void positiveCreteGuestTest() {
        testGuest = new Guest().withName(faker.name().fullName());
        guestService.create(testGuest);
        readGuest = guestService.read(testGuest.getName());

        Assert.assertEquals(testGuest.getName(), readGuest.getName());
        Assert.assertNotNull(readGuest.getId());
    }

    @Test
    public void createGuestWithEmptyNameTest() {
        testGuest = new Guest().withName("");
        guestService.create(testGuest);
        readGuest = guestService.read(testGuest.getName());

        Assert.assertEquals(testGuest.getName(), readGuest.getName());
        Assert.assertNotNull(readGuest.getId());
    }

    @Test
    public void createGuestWithSpecialSymbolsNameTest() {
        testGuest = new Guest().withName("%@#.,|\\\\/[]{}?:;!~`=+-&^$");
        guestService.create(testGuest);
        readGuest = guestService.read(testGuest.getName());

        Assert.assertEquals(testGuest.getName(), readGuest.getName());
        Assert.assertNotNull(readGuest.getId());
    }

    @Test(enabled = false,  description = "BUG: Guest with null ID can be created")
    public void createGuestWithNullNameTest() {
        testGuest = new Guest();
        guestService.create(testGuest);
    }

    @Test(description = "BUG: the same guest name can be created")
    public void CreateTheSameGuestNameTest() {
        String checkName = faker.name().fullName();
        testGuest = new Guest().withName(checkName);
        guestService.create(testGuest);

        readGuest = guestService.read(testGuest.getName());

        Assert.assertEquals(testGuest.getName(), readGuest.getName());
        Assert.assertNotNull(readGuest.getId());

        guestService.create(testGuest);
        guestService.delete(readGuest);
        readGuest = guestService.read(testGuest.getName());

    }

    @AfterMethod
    public void tearDown() {
        guestService.delete(readGuest);
    }

}
