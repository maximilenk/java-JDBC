package edu.school21.chat.app;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.DataSource;

import edu.school21.chat.models.Message;
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
        System.out.println("enter a message ID");
        try (Scanner sc = new Scanner(System.in)) {
            Long id = Long.valueOf(sc.nextLine());
            Message m = a.findById(id).isPresent()? a.findById(id).get() : null;
            System.out.println(m);
        } catch (Exception e) {
            e.printStackTrace();
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
