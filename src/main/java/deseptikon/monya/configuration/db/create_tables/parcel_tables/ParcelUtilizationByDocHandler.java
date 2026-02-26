package deseptikon.monya.configuration.db.create_tables.parcel_tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class ParcelUtilizationByDocHandler {


    public static void main(String[] args) throws SQLException {
        ParcelUtilizationByDocHandler parcelUtilizationByDocHandler = new ParcelUtilizationByDocHandler();
        parcelUtilizationByDocHandler.createColumnForUtByDoc();
        parcelUtilizationByDocHandler.copyColumnUtByDoc();

        parcelUtilizationByDocHandler.trimColumnUtByDoc();

    }


    private void createColumnForUtByDoc() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS UTILIZATION_BY_DOC_ORIGINAL VARCHAR(MAX) DEFAULT ('');");
        closeCon(con);
    }

    public void copyColumnUtByDoc() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("UPDATE PARCELS.PARCEL_LIST_2026 SET UTILIZATION_BY_DOC_ORIGINAL = UTILIZATION_BY_DOC;");

        closeCon(con);
    }

    public void trimColumnUtByDoc() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("UPDATE PARCELS.PARCEL_LIST_2026 SET UTILIZATION_BY_DOC = " +
                "CASE " +
                "WHEN INSTR(LOWER(UTILIZATION_BY_DOC_ORIGINAL), 'зона') > 0 " +
                "THEN SUBSTRING(LOWER(UTILIZATION_BY_DOC_ORIGINAL),1, INSTR(LOWER(UTILIZATION_BY_DOC_ORIGINAL), 'зона')) " +
                "ELSE UTILIZATION_BY_DOC_ORIGINAL " +
                "END " +
                ";");

        closeCon(con);
    }


}

