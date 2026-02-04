package deseptikon.monya.parcels.usage_codes.model.uc;

import deseptikon.monya.parcels.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;

import java.util.Set;

public interface checkAreaBuildings {
    Float shareAreaBuildings();
    Set<Parcel> parcelListCheckedArea(Set<Parcel> parcelListToCheckArea, LmstQuery queryTemplate);
}
