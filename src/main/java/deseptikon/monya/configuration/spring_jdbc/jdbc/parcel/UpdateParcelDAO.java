package deseptikon.monya.configuration.spring_jdbc.jdbc.parcel;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;

import java.util.List;
import java.util.Set;

public interface UpdateParcelDAO {

    void updatePredictedUC(Set<Integer> idList, String usageCode);

    void updateParcelsForReplaceLatin(List<Parcel> parcels);

    void concatParcelsPredictedUsageCode(List<Parcel> parcels);
    void insertParcelList(List<Parcel> parcelList);
    void concatParcelsKLADRTags(List<Parcel> parcels);
    void updateParcelsKLADR(List<Parcel> parcels);
}
