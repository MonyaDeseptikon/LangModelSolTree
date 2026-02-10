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
        createViewParcel.viewKLADRForTest();
    }

    private void viewKLADR() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DROP VIEW IF EXISTS PARCELS.VIEW_KLADR" +
                ";");
        statement.execute("CREATE VIEW IF NOT EXISTS PARCELS.VIEW_KLADR " +
                "AS SELECT ID, CADASTRAL_NUMBER, OKATO, OKTMO, DISTRICT, TYPE_DISTRICT, CITY, TYPE_CITY, LOCALITY, TYPE_LOCALITY, STREET, TYPE_STREET, NOTE, EXP_KLADR, REGEXP FROM PARCELS.PARCEL_LIST_2026 " +
                "WHERE LENGTH(KLADR) < 16 " +
                "AND "+
                "NOTE !=''" +
                ";");
        closeCon(con);
    }

    private void viewKLADRForTest() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DROP VIEW IF EXISTS PARCELS.VIEW_KLADR_TEST" +
                ";");
        statement.execute("CREATE VIEW IF NOT EXISTS PARCELS.VIEW_KLADR_TEST " +
                "AS SELECT ID, CADASTRAL_NUMBER, OKATO, OKTMO, DISTRICT, TYPE_DISTRICT, CITY, TYPE_CITY, LOCALITY, TYPE_LOCALITY, STREET, TYPE_STREET, NOTE, EXP_KLADR, REGEXP  FROM PARCELS.PARCEL_LIST_2026 " +
                "WHERE LENGTH(KLADR) < 16 " +
                "AND "+
                "NOTE !='' " +
                "AND ROWNUM < 6000" +
                ";");
        closeCon(con);
    }

}

