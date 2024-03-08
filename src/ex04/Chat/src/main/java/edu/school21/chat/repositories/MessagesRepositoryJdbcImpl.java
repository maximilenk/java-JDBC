package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void update(Message message) {
        String text = message.getText() == null ? "" + message.getText() : "'" + message.getText() + "'";;
        String time = message.getDateAndTime() == null ? "" + message.getDateAndTime() : "'" + message.getDateAndTime() + "'";
        String query = "UPDATE chatSchema.Message SET author = " 
            + message.getAuthor().getId() + ", room = " +
            message.getRoom().getId() + ", text = " + text +
            ", dateAndTime = " + time + " WHERE id = "
            + message.getId();
        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
            if (userById(message.getAuthor().getId()) == null) {
                throw new NotSavedSubEntityException("User not found");
            }

            if (chatroomById(message.getRoom().getId()) == null) {
                throw new NotSavedSubEntityException("Chatroom not found");
            }
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            LocalDateTime ldt = resultSet.getTimestamp("dateAndTime") == null ? null : resultSet.getTimestamp("dateAndTime").toLocalDateTime();
            return Optional.of(new Message(id, userById(resultSet.getLong(2)), chatroomById(resultSet.getLong(3)), resultSet.getString(4), ldt));
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

    @Override
    public void save(Message message) {
        String query = "INSERT INTO chatSchema.Message (author, room, text, dateAndTime) VALUES ("
            + message.getAuthor().getId() + ", " + message.getRoom().getId()
            + ", '" + message.getText() + "', '" + message.getDateAndTime() + 
            "')" + "RETURNING id";
        try (Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
            if (userById(message.getAuthor().getId()) == null) {
                throw new NotSavedSubEntityException("User not found");
            }

            if (chatroomById(message.getRoom().getId()) == null) {
                throw new NotSavedSubEntityException("Chatroom not found");
            }

            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                throw new NotSavedSubEntityException("message is not saved");
            }
            message.setId(rs.getLong(1));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
