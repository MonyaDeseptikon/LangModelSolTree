package deseptikon.monya.configuration.db.create_tables.parcel_tables.indexes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class CadstralNumberIndex {


    public static void main(String[] args) throws SQLException {
        CadstralNumberIndex cadstralNumberIndex = new CadstralNumberIndex();

        cadstralNumberIndex.simpleIndex();



    }

    private void simpleIndex() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("CREATE UNIQUE INDEX IF NOT EXISTS INDEX_CN ON PARCELS.PARCEL_LIST_2026 (CADASTRAL_NUMBER)" +
                ";");

        closeCon(con);
    }


}

