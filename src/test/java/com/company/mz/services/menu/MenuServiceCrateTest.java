package com.company.mz.services.menu;

import com.company.mz.BaseTest;
import com.company.mz.entity.MenuItems;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MenuServiceCrateTest extends BaseTest {

    @Test
    public void testCreateMenuItem() {
        testMenuItem = new MenuItems().withDishName(faker.food().dish()).withPrice(1+(int)(Math.random()*10));
        menuService.create(testMenuItem);
        readMenuItem = menuService.read(testMenuItem.getDishName());
        Assert.assertTrue(readMenuItem.getId()>0);
        Assert.assertEquals(testMenuItem.getDishName(), readMenuItem.getDishName());
        Assert.assertEquals(testMenuItem.getPrice(), readMenuItem.getPrice());
    }

    @AfterMethod
    public void tearDown() {
        menuService.delete(readMenuItem);
    }
}
