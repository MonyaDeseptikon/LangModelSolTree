package deseptikon.monya.inner_cn.spring_jdbc.jdbc;

import deseptikon.monya.inner_cn.spring_jdbc.model.Parcel;

import java.util.List;
import java.util.Set;

public interface UpdateParcelDAO {


    void updatePredictedUC(Set<Integer> idList, String usageCode);
    void updateParcelsForReplaceLatin(List<Parcel> parcels);
    void concatParcelsPredictedUsageCode(Set<Integer> idList, String predicatedUsageCode);
}
