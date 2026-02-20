package deseptikon.monya.configuration.db.create_tables.auxiliary_tables.indexes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class RegexpIndex {


    public static void main(String[] args) throws SQLException {
        RegexpIndex cadstralNumberIndex = new RegexpIndex();

        cadstralNumberIndex.simpleIndex();



    }

    private void simpleIndex() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("CREATE INDEX IF NOT EXISTS INDEX_REGEXP ON AUXILIARY.KLADR (REGEXP)" +
                ";");

        closeCon(con);
    }


}

