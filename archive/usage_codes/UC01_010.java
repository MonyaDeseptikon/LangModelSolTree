package deseptikon.monya.configuration.usage_codes;

import deseptikon.monya.db.RetrieveRow;
import deseptikon.monya.db.UpdateRow;
import deseptikon.monya.groups.GroupCode;

import deseptikon.monya.service.FindMatches;
import org.apache.commons.lang3.time.StopWatch;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static deseptikon.monya.db.ConnectionDB.getConnections;

public class UC01_010 {
    static Tags tag1 = new Tags(List.of("сельскохозяйствен", "использова"),
            null, false,
            new BigDecimal(6000),
            null,
            new BigDecimal("0.1"));
    static Tags tag2 = new Tags(List.of("сельскохозяйствен", "произв"),
            null, false, new BigDecimal(1000000), null, null);
    static Tags tag3 = new Tags(List.of("сельскохозяйствен", "деятельн"),
            null, false, new BigDecimal(6000), null, new BigDecimal("0.1"));
    static Tags tag4 = new Tags(List.of("сельскохозяйствен", "угод"),
            null, false, new BigDecimal(100000), null, new BigDecimal("0.1"));
    static Tags tag5 = new Tags(List.of("крестьянск", "хозяйс"),
            List.of("животноводс", "скотоводс", "вероводс",
            "вероводс", "тицеводс", "человодс", "виноводс", "ыбоводс"), false, new BigDecimal(100000), null, new BigDecimal("0.1"));
    static Tags tag6 = new Tags(List.of("многолет", "насажде"),
            null, false, BigDecimal.ZERO, null, null);
    static Tags tag7 = new Tags(List.of("фермерск", "хозяйс"),
            List.of("животноводс", "скотоводс", "вероводс",
            "вероводс", "тицеводс", "человодс", "виноводс", "ыбоводс"), false, new BigDecimal(100000), null, new BigDecimal("0.1"));
    static Tags tag8 = new Tags(List.of("растениеводс"),
            null, false, BigDecimal.ZERO, null, null);
    static Tags tag9 = new Tags(List.of("пашн"),
            null, false, BigDecimal.ZERO, null, null);
    static List<Tags> tagsList = List.of(tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9);
    static RetrieveRow retrieveRow;
    static UpdateRow updateRow;
    static Connection con;
    static String usageCode = "01:010";

    static Integer idColumn = 1;
    static Integer areaColumn = 2;
    static Integer utilization_by_docColumn = 3;
    static Integer inner_cadastral_numbersColumn = 4;
    static Integer PREDICTED_USAGE_CODEColumn = 5;

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
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ResultSet resultSet = retrieveRow.retrieveRowRS(" id, area, utilization_by_doc, inner_cadastral_numbers, PREDICTED_USAGE_CODE ");
        int updateCount = 0;
        while (resultSet.next()) {

            if (!Objects.equals(resultSet.getString(utilization_by_docColumn), null) && resultSet.getString(utilization_by_docColumn).matches(".*[а-яА-Я].*")) {

                String findingCode = GroupCode.containedGroupCodes(resultSet.getString(utilization_by_docColumn));
                if (findingCode.equals(usageCode)) {
//                        updateRow.updateRowRS(" PREDICTED_USAGE_CODE = " + "'" + usageCode
//                                + "'" + " WHERE id = " + resultSet.getInt(idColumn));
                    updateCount++;
                    System.out.println(updateCount + " " + resultSet.getInt(idColumn) + " " + resultSet.getString(utilization_by_docColumn) + " " + usageCode);
                } else {
                    for (Tags tag : tagsList) {

                        if (isTagsMatchFound(tag, resultSet) > 0 && isTagsMatchNotFound(tag, resultSet) < 1) {
                            if (resultSet.getBigDecimal(areaColumn).compareTo(tag.moreThisArea) == 1) {
                                if (resultSet.getString(inner_cadastral_numbersColumn).isEmpty() || resultSet.getString(inner_cadastral_numbersColumn).isBlank()) {
//                        updateRow.updateRowRS(" PREDICTED_USAGE_CODE = " + "'" + usageCode
//                                + "'" + " WHERE id = " + resultSet.getInt(idColumn));
                                    updateCount++;
                                    System.out.println(updateCount + " " + resultSet.getInt(idColumn) + " " + resultSet.getBigDecimal(areaColumn) + " " + resultSet.getString(utilization_by_docColumn) + " " + resultSet.getString(inner_cadastral_numbersColumn) + usageCode);
                                } else {
                                    //Проверка доли площади ОКС на ЗУ
                                }
                            }


                        }

                    }

                }

            }
        }

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }

    private static Integer isTagsMatchFound(Tags tag, ResultSet resultSet) throws SQLException {
        Integer tempPosition = 1;
        Iterator<String> tagIterator = tag.utilizationByDoc.iterator();
        while (tempPosition > 0 && tagIterator.hasNext()) {
            tempPosition = FindMatches.matchTagPositionConsecutive(resultSet.getString(utilization_by_docColumn), tagIterator.next(), tempPosition);
        }
        return tempPosition;
    }

    private static Integer isTagsMatchNotFound(Tags tag, ResultSet resultSet) throws SQLException {
        if (Objects.equals(tag.notUtilizationByDoc, null)) return -1;
        Integer tempPosition = -1;
        Iterator<String> tagIterator = tag.notUtilizationByDoc.iterator();
        while (tempPosition < 1 && tagIterator.hasNext()) {
            tempPosition = FindMatches.matchTagPositionArbitrary(resultSet.getString(utilization_by_docColumn), tagIterator.next());
        }
        return tempPosition;
    }

}
