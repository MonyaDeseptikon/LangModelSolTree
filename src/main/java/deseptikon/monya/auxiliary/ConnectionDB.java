package deseptikon.monya.auxiliary;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String url = "jdbc:h2:./lmst";
    private static final String user = "";
    private static final String password = "";

    public static void main(String[] args) throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
    }

    Server webServer = Server.createWebServer().start(); // http://localhost:8082
    //        Server server = Server.createTcpServer().start(); //jdbc:h2:tcp://localhost:не знаю номер порта

    public ConnectionDB() throws SQLException {
    }


    public static Connection getConnections() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection(url, user, password);
        return con;
    }

    public static void closeCon(Connection con) throws SQLException {
        con.close();
    }


}
