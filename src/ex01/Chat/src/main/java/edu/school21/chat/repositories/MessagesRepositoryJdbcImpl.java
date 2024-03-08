package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private final DataSource dataSource;
    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Optional<Message> findById(Long id) {
        String query = "SELECT * FROM chatSchema.Message WHERE id = " + id;
        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(new Message(id, userById(resultSet.getLong(2)), chatroomById(resultSet.getLong(3)), resultSet.getString(4), resultSet.getTimestamp("dateAndTime").toLocalDateTime()));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User userById(Long id) {
        String query = "SELECT * FROM chatSchema.User WHERE id = " + id;
        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                return null;
            }
            return new User(id, resultSet.getString(2), resultSet.getString(3), null, null);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Chatroom chatroomById(Long id) {
        String query = "SELECT * FROM chatSchema.Room WHERE id = " + id;
        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                return null;
            }
            return new Chatroom(id, resultSet.getString(2), null, null);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
