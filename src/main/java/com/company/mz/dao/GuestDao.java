package com.company.mz.dao;

import com.company.mz.entity.Guest;
import com.company.mz.util.DBConnection;

import java.sql.*;

public class GuestDao implements CrudDao<Guest, Guest, String> {

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
    public Guest read(Guest guestCome) {
        ResultSet resultSet = null;
        Guest guest = new Guest();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GuestQueries.READ.query)) {
            statement.setString(1, guestCome.getName());
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
    public void update(Guest guest, String newName) {
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(GuestQueries.UPDATE.query)) {
            statement.setString(1, newName);
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

    enum GuestQueries{
        CREATE("INSERT INTO guest (name) VALUES (?)"),
        READ("SELECT * FROM guest WHERE name = (?);"),
        UPDATE("UPDATE guest SET name = (?) WHERE id = (?);"),
        DELETE("DELETE FROM guest WHERE id = (?);");

        String query;

        GuestQueries(String query) {
            this.query = query;
        }
    }
}
