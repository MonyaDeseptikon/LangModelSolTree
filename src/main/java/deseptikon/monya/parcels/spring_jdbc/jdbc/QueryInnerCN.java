package deseptikon.monya.parcels.spring_jdbc.jdbc;

import deseptikon.monya.parcels.spring_jdbc.models.InnerCN;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelMapperPredicted;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class QueryInnerCN {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);

    }

    public List<InnerCN> getListInnerCN() {
        String SQLQuery = "SELECT * FROM PARCELS.INNER_CN_04";
        return jdbcTemplate.query(SQLQuery, new ParcelMapperPredicted());
    }

}
