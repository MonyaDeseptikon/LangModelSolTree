package deseptikon.monya.configuration.db.create_tables.building_tables.indexes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class UsageCodeIndex {


    public static void main(String[] args) throws SQLException {
        UsageCodeIndex usageCodeIndex = new UsageCodeIndex();

        usageCodeIndex.simpleIndex();

//        usageCodeIndex.dropAllIndexes();

    }

    private void simpleIndex() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("CREATE INDEX IF NOT EXISTS INDEX_USAGE_CODE ON BUILDINGS.PARCEL_INNER_CN (USAGE_CODE)" +
                ";");

        closeCon(con);
    }


    private void dropAllIndexes() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DROP INDEX BUILDINGS.INDEX_USAGE_CODE" +
                ";");

        closeCon(con);
    }

}

