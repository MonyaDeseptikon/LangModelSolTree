package deseptikon.monya;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    private static final String url = "jdbc:h2:/parcels";
    private static final String user = "";
    private static final String password = "";

    public static void main(String[] args) throws SQLException {
        Connection con = getConnections(url, user, password);
        Statement statement = con.createStatement();
//        statement.execute("DROP TABLE test.testTable");
//        statement.execute("DROP SCHEMA test");
//        statement.execute("CREATE SCHEMA IF NOT EXISTS test");

//        statement.execute("CREATE TABLE IF NOT EXISTS test.testTable " +
//                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "firstname VARCHAR(45) NULL, " +
//                "lastname VARCHAR(45) NULL);");

//        statement.execute("INSERT INTO test.testTable (firstname, lastname) "
//                + "VALUES('Иван', 'Иванов')");
//        statement.execute("INSERT INTO test.testTable"+
//                "(lastname)" +
//                "VALUES('Петров');");
        statement.execute("INSERT INTO test.testTable (firstname, lastname) "
                + "VALUES('Иван2', 'Иванов2')");
        statement.execute("INSERT INTO test.testTable"+
        "(lastname)" +
        "VALUES('Петров2');");

        ResultSet set = statement.executeQuery("SELECT * FROM test.testTable;");
        System.out.println(getDataFromSchema(set));
//        System.out.println(set.getMetaData());

con.close();
    }

    public static Connection getConnections(String url, String user, String password) throws SQLException {
        Connection con = null;
                   con =DriverManager.getConnection(url, user, password);
               return con;
    }

    public static ArrayList<String> getDataFromSchema (ResultSet set) throws SQLException {
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getInt(1) + " " + set.getString(2) + " " + set.getString(3));
        }
        return list;
    }
}

