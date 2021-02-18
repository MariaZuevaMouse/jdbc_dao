package com.company.mz.dao.implementation;

import com.company.mz.dao.interfaces.GuestDao;
import com.company.mz.entity.Guest;
import com.company.mz.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDaoSQLiteImpl implements GuestDao {

    @Override
    public void create(Guest guest) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GuestQueries.CREATE.query)) {
            statement.setString(1, guest.getName());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Guest read(String name) {
        ResultSet resultSet = null;
        Guest guest = new Guest();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GuestQueries.READ.query)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
               guest.setId(resultSet.getInt("id"));
               guest.setName(resultSet.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return guest;
    }

    @Override
    public void update(Guest guest) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GuestQueries.UPDATE.query)) {
            statement.setString(1, guest.getName());
            statement.setInt(2, guest.getId());
            final int i = statement.executeUpdate();
            System.out.println(i);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Guest guest) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GuestQueries.DELETE.query)) {
            statement.setInt(1, guest.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Guest> getAllGuestList() {
        ResultSet resultSet =null;
        Guest guest;
        List<Guest> guestList = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(GuestQueries.GET_ALL.query)){
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                guest = new Guest();
                guest.setId(resultSet.getInt("id"));
                guest.setName(resultSet.getString("name"));
                guestList.add(guest);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return guestList;
    }

    @Override
    public List<Guest> getAllGuestListAscOrder() {
        ResultSet resultSet =null;
        Guest guest;
        List<Guest> guestList = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GuestQueries.GET_ALL_ORDER_BYNAME_ASC.query)){
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                guest = new Guest();
                guest.setId(resultSet.getInt("id"));
                guest.setName(resultSet.getString("name"));
                guestList.add(guest);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return guestList;
    }

    @Override
    public List<Guest> getAllGuestListDescOrder() {
        ResultSet resultSet =null;
        Guest guest;
        List<Guest> guestList = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GuestQueries.GET_ALL_ORDER_BY_NAME_DESC.query)){
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                guest = new Guest();
                guest.setId(resultSet.getInt("id"));
                guest.setName(resultSet.getString("name"));
                guestList.add(guest);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return guestList;
    }

    @Override
    public Guest getGuestById(int id) {
        ResultSet resultSet = null;
        Guest guest = new Guest();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GuestQueries.GET_BY_ID.query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                guest.setId(resultSet.getInt("id"));
                guest.setName(resultSet.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return guest;
    }

    enum GuestQueries{
        CREATE("INSERT INTO guest (name) VALUES (?)"),
        READ("SELECT * FROM guest WHERE name = (?);"),
        UPDATE("UPDATE guest SET name = (?) WHERE id = (?);"),
        DELETE("DELETE FROM guest WHERE id = (?);"),
        GET_BY_ID("SELECT * FROM guest WHERE id = ?;"),
        GET_ALL("SELECT * FROM guest;"),
        GET_ALL_ORDER_BYNAME_ASC("SELECT * FROM guest ORDER BY name;"),
        GET_ALL_ORDER_BY_NAME_DESC("SELECT * FROM guest ORDER BY name DESC;");


        String query;

        GuestQueries(String query) {
            this.query = query;
        }
    }
}
