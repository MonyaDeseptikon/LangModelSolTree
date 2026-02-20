package deseptikon.monya.configuration.spring_jdbc.jdbc.parcel;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;

import java.util.List;

public interface CombineMethodsParcel {

    List<Parcel> getListParcelsByTagsKLADRInnerJoin(String parcelTableName);
}
