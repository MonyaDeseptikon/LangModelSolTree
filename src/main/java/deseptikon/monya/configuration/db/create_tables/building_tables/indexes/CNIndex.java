package deseptikon.monya.configuration.db.create_tables.building_tables.indexes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class CNIndex {


    public static void main(String[] args) throws SQLException {
        CNIndex cnIndex = new CNIndex();

        cnIndex.simpleIndex();

//        usageCodeIndex.dropAllIndexes();

    }

    private void simpleIndex() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("CREATE UNIQUE INDEX IF NOT EXISTS INDEX_CN ON BUILDINGS.PARCEL_INNER_CN (CADASTRAL_NUMBER)" +
                ";");
        statement.execute("CREATE INDEX IF NOT EXISTS INDEX_PCN ON BUILDINGS.PARCEL_INNER_CN (PARCEL_CADASTRAL_NUMBERS)" +
                ";");


        closeCon(con);
    }


    private void dropAllIndexes() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DROP INDEX BUILDINGS." +
                ";");

        closeCon(con);
    }

}

