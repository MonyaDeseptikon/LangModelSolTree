package deseptikon.monya.parcels.spring_jdbc.jdbc.parcel;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;

import java.util.List;
import java.util.Set;

public interface UpdateParcelDAO {

    void updatePredictedUC(Set<Integer> idList, String usageCode);

    void updateParcelsForReplaceLatin(List<Parcel> parcels);

    void concatParcelsPredictedUsageCode(Set<Integer> idList, String predicatedUsageCode);
}
