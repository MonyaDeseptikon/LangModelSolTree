package deseptikon.monya.service;

import deseptikon.monya.db.RetrieveRow;
import deseptikon.monya.db.UpdateRow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static deseptikon.monya.db.ConnectionDB.getConnections;

public class ReplaceLatin {
    static RetrieveRow retrieveRow;
    static UpdateRow updateRow;
    static Connection con;

    static {
        try {
            con = getConnections();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            retrieveRow = new RetrieveRow(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            updateRow = new UpdateRow(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ReplaceLatin() {
    }


    public static void main(String[] args) throws SQLException {
        ResultSet resultSet = retrieveRow.retrieveRowRS(" id, utilization_by_doc ");
        int updateCount = 0;
        while (resultSet.next()) {
//            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            if (!Objects.equals(resultSet.getString(2), null) && resultSet.getString(2).matches(".*[а-яА-Я].*")) {
                String clearedString = replaceLatinChar(resultSet.getString(2));
                if (!Objects.equals(clearedString, resultSet.getString(2))) {
                    updateRow.updateRowRS(" utilization_by_doc = " + "'" + clearedString
                            + "'" + " WHERE id = " + resultSet.getInt(1));
                    updateCount++;
                    System.out.println("replaceCount = " + updateCount);
                    System.out.println("checkString = " + resultSet.getString(2));
                }
            }
        }
    }

    private static String replaceLatinChar(String checkString) {
        String resultString;

        if (checkString.matches(".*[CcEeTOopPAaHKkXxBMYy].*")) {
            resultString = checkString;
            resultString = resultString.replace("'", " ");
            resultString = resultString.replace("C", "С");
            resultString = resultString.replace("c", "с");
            resultString = resultString.replace("E", "Е");
            resultString = resultString.replace("e", "е");
            resultString = resultString.replace("T", "Т");
            resultString = resultString.replace("O", "О");
            resultString = resultString.replace("o", "о");
            resultString = resultString.replace("p", "р");
            resultString = resultString.replace("P", "Р");
            resultString = resultString.replace("A", "А");
            resultString = resultString.replace("a", "а");
            resultString = resultString.replace("H", "Н");
            resultString = resultString.replace("K", "К");
            resultString = resultString.replace("k", "к");
            resultString = resultString.replace("X", "Х");
            resultString = resultString.replace("x", "х");
            resultString = resultString.replace("B", "В");
            resultString = resultString.replace("M", "М");
            resultString = resultString.replace("y", "у");
            resultString = resultString.replace("Y", "У");
            return resultString;
        }

        return checkString;
    }

}
