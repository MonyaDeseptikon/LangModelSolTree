package deseptikon.monya.configuration.io_excel.mapper;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.Worksheet;

public interface DBtoExcelRowMapper {
    default void parcelsFill(Worksheet ws, int row, Parcel parcel) {


        ws.value(row, 0, parcel.getCadastralNumber());
        ws.value(row, 1, parcel.getArea());
        ws.value(row, 2, parcel.getCategory());
        ws.value(row, 3, parcel.getUtilizationByDoc());
        ws.value(row, 4, parcel.getUsageCode());
        ws.value(row, 5, parcel.getPredictedUsageCode());
        ws.value(row, 6, parcel.getInnerCadastralNumbersString());

        ws.value(row, 7, parcel.getNote());
        ws.value(row, 8, parcel.getExpKLADR());
        ws.value(row, 9, parcel.getREGEXP());
        ws.value(row, 10, parcel.getKLADR());
        ws.value(row, 11, parcel.getTYPE_CITY());
        ws.value(row, 12, parcel.getCITY());
        ws.value(row, 13, parcel.getDISTRICT());
        ws.value(row, 14, parcel.getTYPE_LOCALITY());
        ws.value(row, 15, parcel.getLocality());
        ws.value(row, 16, parcel.getTYPE_STREET());
        ws.value(row, 17, parcel.getSTREET());
        ws.value(row, 18, parcel.getOKATO());
        ws.value(row, 19, parcel.getOKTMO());


    }

    default void parcelsHeads(Worksheet ws, int row) {
        ws.value(row, 0, "cadastralNumber");
        ws.value(row, 1, "area");
        ws.value(row, 2, "category");
        ws.value(row, 3, "utilizationByDoc");
        ws.value(row, 4, "usageCode");
        ws.value(row, 5, "predictedUsageCode");
        ws.value(row, 6, "innerCadastralNumbers");

        ws.value(row, 7, "note");
        ws.value(row, 8, "expKLADR");
        ws.value(row, 9, "REGEXP");
        ws.value(row, 10, "KLADR");
        ws.value(row, 11, "TYPE_CITY");
        ws.value(row, 12, "CITY");
        ws.value(row, 13, "DISTRICT");
        ws.value(row, 14, "TYPE_LOCALITY");
        ws.value(row, 15, "locality");
        ws.value(row, 16, "TYPE_STREET");
        ws.value(row, 17, "STREET");
        ws.value(row, 18, "OKATO");
        ws.value(row, 19, "OKTMO");
    }

}
