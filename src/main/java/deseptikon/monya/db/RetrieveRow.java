package deseptikon.monya.db;

import java.math.BigDecimal;
import java.sql.*;

import static deseptikon.monya.db.ConnectionDB.closeCon;
import static deseptikon.monya.db.ConnectionDB.getConnections;

public class RetrieveRow implements ServiceDB {
    Connection con;
    Statement statement;

    public RetrieveRow(Connection con) throws SQLException {
        this.con = con;
    }


    public ResultSet retrieveRowRS(String columnsName) throws SQLException {
        String retrieveRowSQL = "SELECT" + columnsName + "FROM PARCELS.PRIVISIONAL_2026";
        statement = con.createStatement();

        ResultSet resultSet = statement.executeQuery(retrieveRowSQL);

        return resultSet;
    }

    public void retrieveRowF() {

    }

}
