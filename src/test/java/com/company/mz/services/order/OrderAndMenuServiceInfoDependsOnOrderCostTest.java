package com.company.mz.services.order;

import com.company.mz.BaseTest;
import com.company.mz.entity.Guest;
import com.company.mz.entity.MenuItems;
import com.company.mz.entity.Orders;
import com.company.mz.entity.statisticentity.OrderVsCost;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderAndMenuServiceInfoDependsOnOrderCostTest extends BaseTest {

    Orders[] threeOrders = new Orders[]{testOrder, testOrder2, testOrder3};
    Guest[] threeGuest = new Guest[]{testGuest, testGuest2, testGuest3};

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();

        createTestMenu();
        for (int i = 1; i < 4; i++) {
            threeGuest[i-1] = new Guest().withName(faker.name().fullName());
            guestService.create(threeGuest[i-1]);
            threeGuest[i-1] = guestService.read(threeGuest[i-1].getName());

            threeOrders[i-1] = new Orders();
            orderService.create(threeOrders[i-1]);
            threeOrders[i-1] = orderService.read(threeGuest[i-1]);

            orderVsMenu.create(threeOrders[i-1], testMenuItem, i);
            orderVsMenu.create(threeOrders[i-1], testMenuItem2, i);
            orderVsMenu.create(threeOrders[i-1], testMenuItem3, i);
        }
    }

    @Test(groups = "order vs menu")
    public void testGetTheMostExpensiveOrder() {
        Map<Orders, Integer> theMostExpensive = orderService.getTheMostExpensive();
        List<OrderVsCost> allOrderWithTotalCost= orderService.getAllOrderWithTotalCost();

        for(Map.Entry<Orders, Integer> entry : theMostExpensive.entrySet()){
            allOrderWithTotalCost.forEach(orderVsCost ->
                    Assert.assertTrue(entry.getValue()>=orderVsCost.getTotalPrice()));
        }
    }

    @Test(groups = "order vs menu")
    public void testGetTheCheapestOrder() {
        Map<Orders, Integer> theMostExpensive = orderService.getTheCheapest();
        List<OrderVsCost> allOrderWithTotalCost= orderService.getAllOrderWithTotalCost();

        for(Map.Entry<Orders, Integer> entry : theMostExpensive.entrySet()){
            allOrderWithTotalCost.forEach(orderVsCost ->
                    Assert.assertTrue(entry.getValue()<=orderVsCost.getTotalPrice()));
        }
    }

    @Test(groups = "order vs menu")
    public void testGetMostPopularMenuItem() {
        MenuItems popularMenuItem = menuService.getPopularMenuItem();

    }

    @Test(groups = "order vs menu")
    public void testGetUnpopularMenuItem() {
        MenuItems unPopularMenuItem = menuService.getUnPopularMenuItem();

    }

    @AfterClass
    public void afterClass() {
        removeTestMenu();
        for (int i = 0; i < 3; i++) {
            orderService.delete(threeOrders[i]);
            guestService.delete(threeGuest[i]);
        }
        orderVsMenu.clearTable();
    }

}
