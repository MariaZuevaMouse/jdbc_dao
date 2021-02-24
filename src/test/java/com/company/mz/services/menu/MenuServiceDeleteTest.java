package com.company.mz.services.menu;

import com.company.mz.BaseTest;
import com.company.mz.entity.MenuItems;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuServiceDeleteTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        testMenuItem = new MenuItems().withDishName(faker.food().dish()).withPrice(1+(int)(Math.random()*100));
        menuService.create(testMenuItem);
        testMenuItem = menuService.read(testMenuItem.getDishName());
    }

    @Test
    public void deleteMenuItemPositiveTest() {
        menuService.delete(testMenuItem);
        readMenuItem = menuService.read(testMenuItem.getDishName());

        Assert.assertNull(readMenuItem.getDishName());
    }
}
