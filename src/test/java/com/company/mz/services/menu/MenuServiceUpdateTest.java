package com.company.mz.services.menu;

import com.company.mz.BaseTest;
import com.company.mz.entity.MenuItems;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuServiceUpdateTest extends BaseTest {
    int newPrice;

    @BeforeMethod
    public void setUp() {
        testMenuItem = new MenuItems().withDishName(faker.food().dish()).withPrice(1+(int)(Math.random()*100));
        menuService.create(testMenuItem);
        testMenuItem = menuService.read(testMenuItem.getDishName());
    }

    @Test
    public void updatePricePositiveTest() {
        newPrice = 1 + (int)(Math.random()*100);
        testMenuItem.setPrice(newPrice);
        menuService.update(testMenuItem);

        readMenuItem = menuService.getById(testMenuItem.getId());

        Assert.assertEquals(readMenuItem.getPrice(), newPrice);
    }

    @Test (enabled = false,  description = "BUG: negative price can be set")
    public void setNegativePriceTest() {
        newPrice = -1;
        testMenuItem.setPrice(newPrice);
        menuService.update(testMenuItem);

        readMenuItem = menuService.getById(testMenuItem.getId());

        Assert.assertNotEquals(readMenuItem.getPrice(), newPrice);
    }

    @Test(expectedExceptions = java.lang.NullPointerException.class)
    public void setNullPriceTest() {
        Integer nullPrice = null;
        testMenuItem.setPrice(nullPrice);
        menuService.update(testMenuItem);

        readMenuItem = menuService.getById(testMenuItem.getId());

    }

    @AfterMethod
    public void tearDown() {
        menuService.delete(testMenuItem);
    }
}
