package deseptikon.monya.configuration.db.create_tables.parcel_tables.indexes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class UtilizationByDocIndex {


    public static void main(String[] args) throws SQLException {
        UtilizationByDocIndex noteIndex = new UtilizationByDocIndex();

        noteIndex.simpleIndex();



    }

    private void simpleIndex() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("CREATE INDEX IF NOT EXISTS INDEX_UTIL_BY_DOC ON PARCELS.PARCEL_LIST_2026 (utilization_by_doc)" +
                ";");

        closeCon(con);
    }


}

