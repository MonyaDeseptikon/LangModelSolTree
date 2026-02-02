package deseptikon.monya.parcels.db.create_tables;

import java.sql.*;

import static deseptikon.monya.auxiliary.ConnectionDB.closeCon;
import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;

public class ParcelCreateProvisionalList {


    public static void main(String[] args) throws SQLException {
        ParcelCreateProvisionalList createTable = new ParcelCreateProvisionalList();
        createTable.columnForKLADR();
//        createTable.dropTableParcel();
//        createTable.createScheme();
//        createTable.createProvisionalList();
//        erasePredictedUC();

    }

    private void createScheme() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
//Не будет удаляться, пока не удалить все таблицы, либо удалять сразу пользователя
        statement.execute("DROP SCHEMA PARCELS");
//        statement.execute("DROP SCHEMA PARCELS CASCADE"); // ?????????????? Будет ли это работать неизвестно
        statement.execute("CREATE SCHEMA IF NOT EXISTS PARCELS");
    }

    //Метод для удаления не нужных таблиц
    private void dropTableParcel() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();
//        statement.execute("DROP TABLE IF EXISTS PARCELS.INNER_CN_04");

        //Удаление копии БД, созданной бином SpringJDBC при попытке одновременного обращения двух бинов к одной встроенной БД
        statement.execute("DROP TABLE IF EXISTS PARCELS.PRIVISIONAL_2026_COPY_3_0");
    }

    private void createProvisionalList() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("CREATE TABLE IF NOT EXISTS PARCELS.PRIVISIONAL_2026 " +
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cadastral_number VARCHAR(20) NOT NULL, " +
                "area FLOAT NULL, " +
                "OKATO VARCHAR(20) NULL, " +
                "OKTMO VARCHAR(20) NULL, " +
                "locality VARCHAR(MAX) NULL, " +
                "other VARCHAR(MAX) NULL, " +
                "note VARCHAR(MAX) NULL, " +
                "approval_document_name VARCHAR(MAX) NULL, " +
                "category VARCHAR(MAX) NULL, " +
                "utilization_land_use VARCHAR(MAX) NULL, " +
                "utilization_by_doc VARCHAR(MAX) NULL, " +
                "utilization_permitted_use_text VARCHAR(MAX) NULL, " +
                "inner_cadastral_numbers VARCHAR(MAX) NULL, " +
                "usage_code VARCHAR(15) NULL, " +
                "PREDICTED_USAGE_CODE VARCHAR(MAX)" +
                "DISTRICT VARCHAR(MAX)" +
                "TYPE_DISTRICT VARCHAR(MAX)" +
                "CITY VARCHAR(MAX)" +
                "TYPE_CITY VARCHAR(MAX)" +
                "SOVIET_VILLAGE VARCHAR(MAX)" +
                "STREET VARCHAR(MAX)" +
                "TYPE_STREET VARCHAR(MAX)" +
                "KLADR VARCHAR(MAX)" +
                ");");

        closeCon(con);
    }

    //Добавлены столбцы, необходимые для определения КЛАДР
    private void columnForKLADR() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS DISTRICT VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS TYPE_DISTRICT VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS CITY VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS TYPE_CITY VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS SOVIET_VILLAGE VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS STREET VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS TYPE_STREET VARCHAR(MAX) DEFAULT ('');");
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS KLADR VARCHAR(MAX) DEFAULT ('');");

        closeCon(con);
    }

    public static void erasePredictedUC() throws SQLException {
        Connection con = getConnections();
        Statement statement = con.createStatement();

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 DROP COLUMN IF EXISTS PREDICTED_USAGE_CODE;");
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        statement.execute("ALTER TABLE PARCELS.PRIVISIONAL_2026 ADD COLUMN IF NOT EXISTS PREDICTED_USAGE_CODE VARCHAR(MAX) DEFAULT ('');");

        closeCon(con);
    }

}

