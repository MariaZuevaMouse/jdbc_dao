package com.company.mz.services.menu;

import com.company.mz.BaseTest;
import com.company.mz.entity.MenuItems;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuServiceReadTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        testMenuItem = new MenuItems().withDishName(faker.food().dish()).withPrice(1+ (int) (Math.random()*100));
        menuService.create(testMenuItem);
        testMenuItem = menuService.read(testMenuItem.getDishName());
    }

    @Test
    public void testReadByName() {
        readMenuItem = menuService.read(testMenuItem.getDishName());

        Assert.assertEquals(readMenuItem.getDishName(), testMenuItem.getDishName());
        Assert.assertEquals(readMenuItem.getPrice(), testMenuItem.getPrice());
        Assert.assertEquals(readMenuItem.getId(), testMenuItem.getId());
    }

    @Test
    public void testReadById() {
        readMenuItem = menuService.getById(testMenuItem.getId());

        Assert.assertEquals(readMenuItem.getDishName(), testMenuItem.getDishName());
        Assert.assertEquals(readMenuItem.getPrice(), testMenuItem.getPrice());
        Assert.assertEquals(readMenuItem.getId(), testMenuItem.getId());
    }

    @AfterMethod
    public void tearDown() {
        menuService.delete(testMenuItem);
    }
}
