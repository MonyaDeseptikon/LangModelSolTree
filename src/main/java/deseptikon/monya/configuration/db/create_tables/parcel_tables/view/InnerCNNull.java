package deseptikon.monya.configuration.db.create_tables.parcel_tables.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class InnerCNNull {


    public static void main(String[] args) throws SQLException {
        InnerCNNull innerCnNull = new InnerCNNull();

        innerCnNull.createView();
    }

    private void createView() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DROP VIEW IF EXISTS PARCELS.VIEW_INNER_CN_NULL" +
                ";");
        statement.execute("CREATE VIEW IF NOT EXISTS PARCELS.VIEW_INNER_CN_NULL " +
                "AS SELECT * FROM PARCELS.PARCEL_LIST_2026 " +
                "WHERE INNER_CADASTRAL_NUMBERS = '' " +
                "OR " +
                "INNER_CADASTRAL_NUMBERS IS NULL" +
                ";");
        closeCon(con);
    }




}

