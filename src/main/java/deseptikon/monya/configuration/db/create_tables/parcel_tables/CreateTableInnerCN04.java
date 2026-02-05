package deseptikon.monya.configuration.db.create_tables.parcel_tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class CreateTableInnerCN04 {


    public static void main(String[] args) throws SQLException {
        CreateTableInnerCN04 createTable = new CreateTableInnerCN04();

        createTable.createTable();

    }

    private void createTable() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("CREATE TABLE IF NOT EXISTS PARCELS.INNER_CN_04 " +
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cadastral_number VARCHAR(20) NOT NULL, " +
                "building_name VARCHAR(MAX) NULL, " +
                "area FLOAT NULL, " +
                "note VARCHAR(MAX) NULL, " +
                "usage_code VARCHAR(15) NULL, " +
                "parcel_cadastral_numbers VARCHAR(MAX) NULL" +
                ");");
        closeCon(con);
    }

}

