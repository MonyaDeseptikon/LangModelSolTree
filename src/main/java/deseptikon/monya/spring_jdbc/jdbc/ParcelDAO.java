package deseptikon.monya.spring_jdbc.jdbc;

import deseptikon.monya.spring_jdbc.model.Parcel;

import javax.sql.DataSource;
import java.util.List;

public interface ParcelDAO {
    public void setDataSource(DataSource dataSource);

    public List<Parcel> getListParcels();

    public List<Parcel> getListParcelsByICN(String innerCN);

    public void updateParcels(Integer id, String usageCode);

}
