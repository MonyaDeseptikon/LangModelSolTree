package deseptikon.monya.parcels.spring_jdbc.jdbc;

import deseptikon.monya.parcels.spring_jdbc.models.InnerCN;
import deseptikon.monya.parcels.spring_jdbc.util.inner_cn.InnerCNArrayMakerToDB;
import deseptikon.monya.parcels.spring_jdbc.util.inner_cn.InnerCNMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class QueryInnerCN implements InnerCNArrayMakerToDB {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;

    public void setDataSource(DataSource dataSourceInnerCN) {
        this.jdbcTemplate = new JdbcTemplate(dataSourceInnerCN);
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);

    }

    public List<InnerCN> getListInnerCN(String innerCNTableName) {

        String SQLQuery = "SELECT * FROM PARCELS." +
                innerCNTableName;
        return jdbcTemplate.query(SQLQuery, new InnerCNMapper());
    }

    public void insertInnerCN(final List <InnerCN> innerCNList, String innerCNTableName) throws SQLException {
        String insertRowSQL = "INSERT INTO PARCELS." +
                innerCNTableName + " " +
                "(cadastral_number, building_name, area, note, usage_code, parcel_cadastral_numbers) " +
                "VALUES (cadastral_numberList, building_nameList, areaList, noteList, usage_codeList, parcel_cadastral_numbersList)";

        template.batchUpdate(insertRowSQL, insertInnerCNArrayMakerToDB(innerCNList));
    }
}