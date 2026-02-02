package deseptikon.monya.parcels.io_excel.mapper;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.Row;

import java.util.List;

public interface ExcelRowMapperOld {
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


    default Parcel parcelsRow(Row row){
        Parcel parcel = new Parcel();

        parcel.setCadastralNumber(row.getCellText(2));
        parcel.setLocality(row.getCellText(6));
        parcel.setOther(row.getCellText(5));
        parcel.setApprovalDocumentName(row.getCellText(7));
        parcel.setCategory(row.getCellText(200));

        parcel.setUtilizationLandUse(row.getCellText(200));
        parcel.setUtilizationByDoc(row.getCellText(200));
        parcel.setUtilizationPermittedUseText(row.getCellText(200));
        parcel.setInnerCadastralNumbers(row.getCellText(200));
        parcel.setDISTRICT(row.getCellText(200));
        parcel.setTYPE_DISTRICT(row.getCellText(200));
        parcel.setCITY(row.getCellText(200));
        parcel.setTYPE_CITY(row.getCellText(200));
        parcel.setSOVIET_VILLAGE(row.getCellText(200));
        parcel.setSTREET(row.getCellText(200));
        parcel.setTYPE_STREET(row.getCellText(200));
        parcel.setKLADR(row.getCellText(200));


        parcel.setOKATO(row.getCellText(14));
        parcel.setOKTMO(row.getCellText(15));
        parcel.setArea(row.getCell(12));
        parcel.setNote(row.getCellText(44));
        parcel.setUsageCode(row.getCellText(67));
        parcel.setPredictedUsageCode(row.getCellText(202));

        return parcel;
    }

}
