package edu.school21.chat.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

public class UsersRepositoryImpl implements UsersRepository {

    private final DataSource dataSource;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<User> findAll(int page, int size) {
        List<User> result = new ArrayList<>();
        int from = (page) * size + 1;
        int to = (page + 1) * size;
        String query = "SELECT id, login, password FROM chatSchema.User WHERE id BETWEEN " + from + " AND " + to ;
        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    result.add(new User(rs.getLong(1), rs.getString(2), rs.getString(3),
                        createdChatrooms(rs.getLong(1)),
                        allRooms(rs.getLong(1))));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Chatroom> allRooms(Long id) {
        List<Chatroom> result = new ArrayList<>();
        String query = "SELECT chatSchema.room.id, chatSchema.room.name " +
                        "FROM chatSchema.room " +
                        "JOIN chatSchema.message ON chatSchema.room.id = chatSchema.message.room " +
                        "where chatSchema.message.author = " + id;
        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result.add(new Chatroom(rs.getLong(1), rs.getString(2),null, null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Chatroom> createdChatrooms(Long id) {
        List<Chatroom> result = new ArrayList<>();
        String query = "SELECT id, name FROM chatSchema.Room WHERE owner = " + id;
        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result.add(new Chatroom(rs.getLong(1), rs.getString(2),
                    null, null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
