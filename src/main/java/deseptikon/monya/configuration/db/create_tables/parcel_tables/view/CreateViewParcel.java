package deseptikon.monya.configuration.db.create_tables.parcel_tables.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class CreateViewParcel {


    public static void main(String[] args) throws SQLException {
        CreateViewParcel createViewParcel = new CreateViewParcel();

        createViewParcel.viewKLADR();

    }

    private void viewKLADR() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("CREATE VIEW IF NOT EXISTS PARCELS.VIEW_KLADR " +
                "AS SELECT * FROM PARCELS.PARCEL_LIST_2026 " +
                "WHERE LENGTH(KLADR) < 17" +
                ";");
        closeCon(con);
    }

}

