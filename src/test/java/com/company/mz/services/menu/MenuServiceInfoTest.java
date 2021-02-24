package com.company.mz.services.menu;

import com.company.mz.BaseTest;
import com.company.mz.entity.MenuItems;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class MenuServiceInfoTest extends BaseTest {

    String name1;
    String name2;
    String name3;
    String[] names = new String[]{name1,name2, name3};

    int price1;
    int price2;
    int price3;
    int[] prices = new int[]{price1, price2, price3};
    int max , min ;

    @BeforeMethod
    public void setUp() {
        for (int i = 0; i < 3; i++) {
            testMenuItem = new MenuItems().withDishName(faker.food().dish()).withPrice(1+(int)(Math.random()*100));
            menuService.create(testMenuItem);
            testMenuItem = menuService.read(testMenuItem.getDishName());
            names[i] = testMenuItem.getDishName();
            prices[i] = testMenuItem.getPrice();
        }
        min = prices[0];
        max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if(prices[i]> max) max = prices[i];
            if(prices[i]< min) min = prices[i];
        }
    }

    @Test
    public void getAllMenuTest() {
        List<MenuItems> allMenuItems = menuService.getAllMenuItems();
        Assert.assertEquals(allMenuItems.size(), 3);
    }

    @Test
    public void getAllInAskNameOrderTest() {
        List<MenuItems> allMenuItems = menuService.getAllMenuItemsOrderByNameAsc();

        Assert.assertEquals(allMenuItems.size(), 3);
        Assert.assertTrue(allMenuItems.get(0).getDishName().compareTo(allMenuItems.get(1).getDishName()) < 0);
        Assert.assertTrue(allMenuItems.get(1).getDishName().compareTo(allMenuItems.get(2).getDishName()) < 0);

    }

    @Test
    public void getAllInDescNameOrderTest() {
        List<MenuItems> allMenuItems = menuService.getAllMenuItemsOrderByNameDesc();

        Assert.assertEquals(allMenuItems.size(), 3);
        System.out.println(allMenuItems.get(0).getDishName().compareTo(allMenuItems.get(1).getDishName()));
        Assert.assertTrue(allMenuItems.get(0).getDishName().compareTo(allMenuItems.get(1).getDishName()) > 0);
        Assert.assertTrue(allMenuItems.get(1).getDishName().compareTo(allMenuItems.get(2).getDishName()) > 0);

    }

    @Test
    public void getMostExpensiveTest() {
        MenuItems expensiveMenuItem = menuService.getMostExpensiveMenuItem();

        Assert.assertEquals(expensiveMenuItem.getPrice(), max);
    }

    @Test
    public void getCheapestTest() {
        MenuItems cheapestMenuItem = menuService.getCheapestMenuItem();

        Assert.assertEquals(cheapestMenuItem.getPrice(), min);
    }

    @AfterMethod
    public void tearDown() {
        for (int i = 0; i < 3; i++) {
            testMenuItem = menuService.read(names[i]);
            menuService.delete(testMenuItem);
        }
    }
}
