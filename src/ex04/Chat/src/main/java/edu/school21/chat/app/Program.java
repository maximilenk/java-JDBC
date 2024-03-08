package edu.school21.chat.app;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DataSourceCreator;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryImpl;

public class Program {
    private static final String PATH_TO_DB_INIT_FILE = "src/main/resources/schema.sql";
    private static final String PATH_TO_DB_CONTENT_FILE = "src/main/resources/data.sql";
    public static void main(String... args) {
        DataSourceCreator dsc = new DataSourceCreator();
        UsersRepository a = new UsersRepositoryImpl(dsc.getDataSource());
        update(dsc.getDataSource(), PATH_TO_DB_INIT_FILE);
        update(dsc.getDataSource(), PATH_TO_DB_CONTENT_FILE);
        List<User> b = a.findAll(0, 8);
        for (User u : b) {
            System.out.println(u);
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
