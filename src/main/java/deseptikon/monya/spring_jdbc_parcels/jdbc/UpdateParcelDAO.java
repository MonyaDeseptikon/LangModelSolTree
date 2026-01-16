package deseptikon.monya.spring_jdbc_parcels.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Set;

public interface UpdateParcelDAO {


    void updatePredictedUC(Set<Integer> idList, String usageCode);

    void concatParcelsPredictedUsageCode(Set<Integer> idList, String predicatedUsageCode);
}
