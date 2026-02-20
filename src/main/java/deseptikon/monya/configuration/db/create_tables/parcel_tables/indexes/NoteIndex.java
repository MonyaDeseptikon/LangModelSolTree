package deseptikon.monya.configuration.db.create_tables.parcel_tables.indexes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class NoteIndex {


    public static void main(String[] args) throws SQLException {
        NoteIndex noteIndex = new NoteIndex();

        noteIndex.simpleIndex();



    }

    private void simpleIndex() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("CREATE INDEX IF NOT EXISTS INDEX_NOTE ON PARCELS.PARCEL_LIST_2026 (NOTE)" +
                ";");

        closeCon(con);
    }


}

