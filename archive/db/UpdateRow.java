package deseptikon.monya.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateRow implements ServiceDB {
    Connection con;
    Statement statement;

    public UpdateRow(Connection con) throws SQLException {
        this.con = con;
    }


    public void updateRowRS(String updateRow) throws SQLException {
        String updateRowSQL = "UPDATE PARCELS.PARCEL_LIST_2026 SET" + updateRow;
        statement = con.createStatement();
        statement.executeUpdate(updateRowSQL);
    }


}
