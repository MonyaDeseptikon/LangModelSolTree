package deseptikon.monya.usage_codes;

import deseptikon.monya.db.RetrieveRow;
import deseptikon.monya.db.UpdateRow;

import javax.validation.OverridesAttribute;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static deseptikon.monya.db.ConnectionDB.getConnections;

public class uc01_010 {
    Tags tag1 = new Tags(List.of("сельскохозяйствен", "использова"),
            false, new BigDecimal(6000), null, null, new BigDecimal("0.1"));
    Tags tag2 = new Tags(List.of("сельскохозяйствен", "произв"),
            false, new BigDecimal(1000000), null, null, null);
    Tags tag3 = new Tags(List.of("сельскохозяйствен", "деятельн"),
            false, new BigDecimal(6000), null, null, new BigDecimal("0.1"));
    Tags tag4 = new Tags(List.of("сельскохозяйствен", "угод"),
            false, new BigDecimal(100000), null, null, new BigDecimal("0.1"));
    Tags tag5 = new Tags(List.of("крестьянск", "хозяйс"),
            false, new BigDecimal(100000), null, List.of("животноводс", "скотоводс", "вероводс",
            "вероводс", "тицеводс", "человодс", "виноводс", "ыбоводс"), new BigDecimal("0.1"));
    Tags tag6 = new Tags(List.of("многолет", "насажде"),
            false, BigDecimal.ZERO, null, null, null);
    Tags tag7 = new Tags(List.of("фермерск", "хозяйс"),
            false, new BigDecimal(100000), null, List.of("животноводс", "скотоводс", "вероводс",
            "вероводс", "тицеводс", "человодс", "виноводс", "ыбоводс"), new BigDecimal("0.1"));
    Tags tag8 = new Tags(List.of("растениеводс"),
            false, BigDecimal.ZERO, null, null, null);
    Tags tag9 = new Tags(List.of("пашн"),
            false, BigDecimal.ZERO, null, null, null);

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

    public static void main(String[] args) throws SQLException {
      ResultSet resultSet = retrieveRow.retrieveRowRS(" id, area, utilization_by_doc, inner_cadastral_numbers, PREDICTED_USAGE_CODE ");
      while (resultSet.next()){
          System.out.println(resultSet.getRow() + " "+ resultSet.getString(3)+ " "+ resultSet.getString(4) + " "+ resultSet.getString(5));
      }


    }

}
