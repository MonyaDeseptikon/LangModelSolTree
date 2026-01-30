package deseptikon.monya.parcels.spring_jdbc.jdbc;

import deseptikon.monya.parcels.spring_jdbc.models.Building;
import deseptikon.monya.parcels.spring_jdbc.util.building.BuildingArrayMakerToDB;
import deseptikon.monya.parcels.spring_jdbc.util.building.BuildingMapper;
import deseptikon.monya.parcels.spring_jdbc.util.parcel.ParcelMapperPredicted;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public class QueryBuilding implements BuildingArrayMakerToDB {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;

    public void setDataSourceInnerCN(DataSource dataSourceInnerCN) {
        this.jdbcTemplate = new JdbcTemplate(dataSourceInnerCN);
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);

    }

}