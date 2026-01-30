package deseptikon.monya.parcels.io_excel.mapper;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.Worksheet;

import java.util.List;

public interface FillRow {
    default void parcelsFill(Worksheet ws, int row, Parcel parcel) {

        ws.value(row, 0, parcel.getCadastralNumber());
        ws.value(row, 1, parcel.getArea());
        ws.value(row, 2, parcel.getCategory());
        ws.value(row, 3, parcel.getLocality());
        ws.value(row, 4, parcel.getNote());
        ws.value(row, 5, parcel.getInnerCadastralNumbersString());
        ws.value(row, 6, parcel.getUsageCode());
        ws.value(row, 7, parcel.getPredictedUsageCode());
        ws.value(row, 8, parcel.getUtilizationByDoc());


    }

    default void parcelsHeads(Worksheet ws, int row, List<String> heads) {

        ws.value(row, 0, heads.get(1));
        ws.value(row, 1, heads.get(2));
        ws.value(row, 2, heads.get(9));
        ws.value(row, 3, heads.get(5));
        ws.value(row, 4, heads.get(7));
        ws.value(row, 5, heads.get(13));
        ws.value(row, 6, heads.get(14));
        ws.value(row, 7, heads.get(15));
        ws.value(row, 8, heads.get(11));
    }


}
