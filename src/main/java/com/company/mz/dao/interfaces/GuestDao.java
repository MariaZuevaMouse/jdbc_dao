package com.company.mz.dao.interfaces;

import com.company.mz.entity.Guest;

import java.util.List;

public interface GuestDao extends CrudDao <Guest, String> {

    public List<Guest> getAllGuestList();
    public List<Guest> getAllGuestListAscOrder();
    public List<Guest> getAllGuestListDescOrder();
    public Guest getGuestById( int id);
}
