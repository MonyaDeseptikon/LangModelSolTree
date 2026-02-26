package deseptikon.monya.configuration.db.create_tables.parcel_tables.indexes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class CadastralNumberIndex {


    public static void main(String[] args) throws SQLException {
        CadastralNumberIndex cadastralNumberIndex = new CadastralNumberIndex();

        cadastralNumberIndex.simpleIndex();

//        cadastralNumberIndex.dropAllIndexes();

    }

    private void simpleIndex() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("CREATE UNIQUE INDEX IF NOT EXISTS INDEX_CN ON PARCELS.PARCEL_LIST_2026 (CADASTRAL_NUMBER)" +
                ";");

        statement.execute("CREATE INDEX IF NOT EXISTS INDEX_INNER_CN ON PARCELS.PARCEL_LIST_2026 (INNER_CADASTRAL_NUMBERS)" +
                ";");

        closeCon(con);
    }


    private void dropAllIndexes() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DROP INDEX PARCELS.INDEX_CN" +
                ";");

        closeCon(con);
    }

}

