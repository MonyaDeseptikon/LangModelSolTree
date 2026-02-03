package deseptikon.monya.parcels.db;

import java.sql.*;

import static deseptikon.monya.auxiliary.ConnectionDB.getConnections;
//Этого быть не должно - это нужно перенести в Spring JDBC
public class FillDB {

    Connection con = getConnections();
    PreparedStatement statement;

    public FillDB() throws SQLException {
    }

    public void fillProvisionalList(String cadastral_number,
                                    Float area,
                                    String OKATO,
                                    String OKTMO,
                                    String locality,
                                    String other,
                                    String note,
                                    String approval_document_name,
                                    String category,
                                    String utilization_land_use,
                                    String utilization_by_doc,
                                    String utilization_permitted_use_text,
                                    String inner_cadastral_numbers,
                                    String usage_code) throws SQLException {
        String fillRowSQL = "INSERT INTO PARCELS.PARCEL_LIST_2026 (cadastral_number, area, OKATO, KLADR, locality, other, note, approval_document_name, category, " +
                "utilization_land_use, utilization_by_doc, utilization_permitted_use_text, inner_cadastral_numbers,  usage_code) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        statement = con.prepareStatement(fillRowSQL);
        statement.setString(1, cadastral_number);
        statement.setFloat(2, area);
        statement.setString(3, OKATO);
        statement.setString(4, OKTMO);
        statement.setString(5, locality);
        statement.setString(6, other);
        statement.setString(7, note);
        statement.setString(8, approval_document_name);
        statement.setString(9, category);
        statement.setString(10, utilization_land_use);
        statement.setString(11, utilization_by_doc);
        statement.setString(12, utilization_permitted_use_text);
        statement.setString(13, inner_cadastral_numbers);
        statement.setString(14, usage_code);

        statement.execute();
    }




}

