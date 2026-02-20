package deseptikon.monya.configuration.db.create_tables.parcel_tables;

import java.sql.*;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class ParcelCreateProvisionalList {


    public static void main(String[] args) throws SQLException {
        ParcelCreateProvisionalList createTable = new ParcelCreateProvisionalList();
        createTable.deleteDataTable();
//        createTable.columnForKLADR();
//        createTable.dropTableParcel();
//        createTable.createScheme();
//        createTable.createParcelList();
//        erasePredictedUC();

    }

    private void deleteDataTable() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DELETE FROM PARCELS.PARCEL_LIST_2026");

    }

    private void createScheme() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("DROP SCHEMA PARCELS CASCADE");
        statement.execute("CREATE SCHEMA IF NOT EXISTS PARCELS");
    }

    //Метод для удаления не нужных таблиц
    private void dropTableParcel() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("DROP TABLE IF EXISTS PARCELS.PARCEL_LIST_2026");
//        statement.execute("DROP TABLE IF EXISTS PARCELS.INNER_CN_04");

        //Удаление копии БД, созданной бином SpringJDBC при попытке одновременного обращения двух бинов к одной встроенной БД
        statement.execute("DROP TABLE IF EXISTS PARCELS.PARCEL_LIST_2026_COPY_3_0");
    }

    private void createParcelList() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("CREATE TABLE IF NOT EXISTS PARCELS.PARCEL_LIST_2026 " +
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cadastral_number VARCHAR(20) NOT NULL, " +
                "CADASTRAL_BLOCK VARCHAR(20) NULL, " +
                "area FLOAT NULL, " +
                "OKATO VARCHAR(20) NULL, " +
                "OKTMO VARCHAR(20) NULL, " +
                "KLADR VARCHAR(MAX) NULL, " +
                "DISTRICT VARCHAR(MAX) NULL, " +
                "TYPE_DISTRICT VARCHAR(MAX) NULL, " +
                "CITY VARCHAR(MAX) NULL, " +
                "TYPE_CITY VARCHAR(MAX) NULL, " +
                "locality VARCHAR(MAX) NULL, " +
                "TYPE_LOCALITY VARCHAR(MAX) NULL, " +
                "SOVIET_VILLAGE VARCHAR(MAX) NULL, " +
                "STREET VARCHAR(MAX) NULL, " +
                "TYPE_STREET VARCHAR(MAX) NULL, " +
                "other VARCHAR(MAX) NULL, " +
                "note VARCHAR(MAX) NULL, " +
                "approval_document_name VARCHAR(MAX) NULL, " +
                "category VARCHAR(MAX) NULL, " +
                "utilization_land_use VARCHAR(MAX) NULL, " +
                "utilization_by_doc VARCHAR(MAX) NULL, " +
                "utilization_permitted_use_text VARCHAR(MAX) NULL, " +
                "inner_cadastral_numbers VARCHAR(MAX) NULL, " +
                "usage_code VARCHAR(15) NULL, " +
                "PREDICTED_USAGE_CODE VARCHAR(MAX) NULL, " +
                "EXP_KLADR VARCHAR(MAX) NULL, " +
                "REGEXP VARCHAR(MAX) NULL" +
                ");");

        closeCon(con);
    }

    //Добавлены столбцы, необходимые для определения КЛАДР
    private void columnForKLADR() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS DISTRICT VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS TYPE_DISTRICT VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS CITY VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS TYPE_CITY VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS SOVIET_VILLAGE VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS STREET VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS TYPE_STREET VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS KLADR VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS TYPE_LOCALITY VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS EXP_KLADR VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS REGEXP VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS CADASTRAL_BLOCK VARCHAR(20) DEFAULT ('');");

        closeCon(con);
    }

    public static void erasePredictedUC() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
/* Это не работает если этот столбец используется VIEW
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 DROP COLUMN IF EXISTS PREDICTED_USAGE_CODE;");
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        statement.execute("ALTER TABLE PARCELS.PARCEL_LIST_2026 ADD COLUMN IF NOT EXISTS PREDICTED_USAGE_CODE VARCHAR(MAX) DEFAULT ('');");
*/
        statement.execute("UPDATE PARCELS.PARCEL_LIST_2026 SET PREDICTED_USAGE_CODE = '';");

        closeCon(con);
    }

    public static void eraseKLADR() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
        statement.execute("UPDATE PARCELS.PARCEL_LIST_2026 SET " +
                "EXP_KLADR = '', " +
                "REGEXP = '';"
        );

        closeCon(con);
    }

}

