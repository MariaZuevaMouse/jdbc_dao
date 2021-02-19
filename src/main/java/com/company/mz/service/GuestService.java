package com.company.mz.service;

import com.company.mz.dao.implementation.GuestDaoSQLiteImpl;
import com.company.mz.entity.Guest;
import com.company.mz.util.DatabaseType;

import java.util.List;

public class GuestService {
    private GuestDaoSQLiteImpl guestDao;

    public GuestService(DatabaseType databaseType) {
        guestDao = new GuestDaoSQLiteImpl(databaseType);
    }

    public void create(Guest guest) {
        guestDao.create(guest);
        System.out.println("New guest is added: " + guest);
    }

    public Guest read(String name) {
        Guest guest = guestDao.read(name);
        System.out.println("Full guest record from database: " + guest);
        return guest;
    }

    public void update(Guest guest) {
        guestDao.update(guest);
        System.out.println("Guest name was update: " + guest);
    }

    public void delete(Guest guest) {
        guestDao.delete(guest);
        System.out.println("Guest record: " + "\"" + guest + "\" " + " was removed");
    }

    public List<Guest> getAllGuestList() {
        List<Guest> allGuestList = guestDao.getAllGuestList();
        System.out.println("\n Guest list from database: ");
        allGuestList.forEach(System.out::println);
        return allGuestList;
    }

    public List<Guest> getAllGuestListAscOrder() {
        List<Guest> allGuestList = guestDao.getAllGuestListAscOrder();
        System.out.println("\n Guest list in ASC order by name: ");
        allGuestList.forEach(System.out::println);
        return allGuestList;
    }

    public List<Guest> getAllGuestListDescOrder() {
        List<Guest> allGuestList = guestDao.getAllGuestListDescOrder();
        System.out.println("\n Guest list in DESC order by name: ");
        allGuestList.forEach(System.out::println);
        return allGuestList;
    }

    public Guest getGuestById(int id) {
        Guest guestById = guestDao.getGuestById(id);
        System.out.println(guestById);
        return guestById;
    }

}
