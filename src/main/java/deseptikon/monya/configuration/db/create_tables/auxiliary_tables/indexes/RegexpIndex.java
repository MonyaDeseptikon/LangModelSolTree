package deseptikon.monya.configuration.db.create_tables.auxiliary_tables.indexes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class RegexpIndex {


    public static void main(String[] args) throws SQLException {
        RegexpIndex regexpIndex = new RegexpIndex();

        regexpIndex.simpleIndex();

        regexpIndex.dropAllIndexes();

    }

    private void simpleIndex() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("CREATE INDEX IF NOT EXISTS INDEX_REGEXP ON AUXILIARY.KLADR (REGEXP)" +
                ";");

        closeCon(con);
    }

    private void dropAllIndexes() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DROP INDEX  AUXILIARY.INDEX_REGEXP" +
                ";");

        closeCon(con);
    }
}

