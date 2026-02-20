package deseptikon.monya.usage_code_new.usage_codes_old.model.uc;

import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;

import java.util.Set;

public interface checkAreaBuildings {
    Float shareAreaBuildings();
    Set<Parcel> parcelListCheckedArea(Set<Parcel> parcelListToCheckArea, LmstQuery queryTemplate);
}
