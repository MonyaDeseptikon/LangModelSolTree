package deseptikon.monya.parcels.spring_jdbc.jdbc.auxiliary_jdbc;

import deseptikon.monya.parcels.spring_jdbc.models.Building;
import deseptikon.monya.parcels.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;

import java.util.List;
import java.util.Set;

public interface AuxiliaryTableQuery {
    void insertCodeKLADRList(List<CodeKLADR> codeKLADRList);
}
