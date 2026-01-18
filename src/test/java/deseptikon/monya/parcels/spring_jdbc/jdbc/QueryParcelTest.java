package deseptikon.monya.parcels.spring_jdbc.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QueryParcelTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring _config.xml");

    JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) context.getBean("dataSourceConnection"));
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

    @Test
    void concatParcelsPredictedUsageCode() {
        Set<Integer> idList = new HashSet<>(List.of(4, 5));
        String predicatedUsageCode = "test2";
        String columns = "PREDICTED_USAGE_CODE";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("predicatedUsageCode", predicatedUsageCode);
        parameters.addValue("idList", idList);
        parameters.addValue("columns", columns);

        String SQLUpdate = "UPDATE PARCELS.PRIVISIONAL_2026 SET PREDICTED_USAGE_CODE = " +
                "CASE " +
                "WHEN PREDICTED_USAGE_CODE = '' " +
                "THEN :predicatedUsageCode " +
                "ELSE CONCAT(PREDICTED_USAGE_CODE, '; ', :predicatedUsageCode) " +
                "END " +
                "WHERE id IN (:idList)";
        template.update(SQLUpdate, parameters);
    }

    @Test
    void getListParcelsForReplaceLatin() {
        List<String> columnsName = List.of("a", "b", "c");
            String colNameString = String.join(", ", columnsName);
        assertEquals( "a, b, c", colNameString);

    }
}