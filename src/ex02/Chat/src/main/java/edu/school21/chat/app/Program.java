package edu.school21.chat.app;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.sql.DataSource;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DataSourceCreator;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

public class Program {
    private static final String PATH_TO_DB_INIT_FILE = "src/main/resources/schema.sql";
    private static final String PATH_TO_DB_CONTENT_FILE = "src/main/resources/data.sql";
    public static void main(String... args) {
        DataSourceCreator dsc = new DataSourceCreator();
        MessagesRepositoryJdbcImpl a = new MessagesRepositoryJdbcImpl(dsc.getDataSource());
        update(dsc.getDataSource(), PATH_TO_DB_INIT_FILE);
        update(dsc.getDataSource(), PATH_TO_DB_CONTENT_FILE);
        try {
            System.out.println("Working case:");
            User u = new User(4L, "volodyaXS2012", "456rty", null, null);
            Chatroom c = new Chatroom(2L, "friends", u, null);
            Message message = new Message(null, u, c, "HIIIIIII!", LocalDateTime.now());
            a.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("case: non-existent user");
            User u = new User(100L, "volodyaXS2012", "456rty", null, null);
            Chatroom c = new Chatroom(2L, "friends", u, null);
            Message message = new Message(null, u, c, "HIIIIIII!", LocalDateTime.now());
            a.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("case: non-existent room");
            User u = new User(4L, "volodyaXS2012", "456rty", null, null);
            Chatroom c = new Chatroom(8L, "friends", u, null);
            Message message = new Message(null, u, c, "HIIIIIII!", LocalDateTime.now());
            a.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void update(DataSource ds, String path) {
        try (Connection con = ds.getConnection();
            Statement st = con.createStatement();
            Scanner read = new Scanner(new File(path))) {
            read.useDelimiter(";");
            while (read.hasNext()) {
                st.execute(read.next().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
			
    }

}
