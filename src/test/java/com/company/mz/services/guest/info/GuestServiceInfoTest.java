package com.company.mz.services.guest.info;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GuestServiceInfoTest extends BaseTest {

    String name1;
    String name2;
    String name3;
    String[] names = new String[]{name1,name2, name3};

    @BeforeMethod
    public void setUp() {
        for (int i = 0; i < 3; i++) {
//            createTestGuest();
            testGuest = new Guest().withName(faker.name().fullName());
            guestService.create(testGuest);
            testGuest = guestService.read(testGuest.getName());
            names[i] = testGuest.getName();
        }

    }

    @Test
    public void testGetAllGuests() {
        List<Guest> allGuestList = guestService.getAllGuestList();
        Assert.assertEquals(allGuestList.size(), 3);
    }

    @Test
    public void testGetAllAsc() {
        List<Guest> allGuestList = guestService.getAllGuestListAscOrder();

        Assert.assertEquals(3, allGuestList.size());
        Assert.assertTrue(allGuestList.get(0).getName().compareTo(allGuestList.get(1).getName()) < 0);
        Assert.assertTrue(allGuestList.get(1).getName().compareTo(allGuestList.get(2).getName()) < 0);
    }

    @Test
    public void testGetAllDesc() {
        List<Guest> allGuestList = guestService.getAllGuestListDescOrder();

        Assert.assertEquals(3, allGuestList.size());
        Assert.assertTrue(allGuestList.get(0).getName().compareTo(allGuestList.get(1).getName()) > 0);
        Assert.assertTrue(allGuestList.get(1).getName().compareTo(allGuestList.get(2).getName()) > 0);

    }

    @AfterMethod
    public void tearDown() {
        for (int i = 0; i < 3; i++) {
            testGuest = guestService.read(names[i]);
//            removeTestGuest();
            guestService.delete(testGuest);
        }
    }

}
