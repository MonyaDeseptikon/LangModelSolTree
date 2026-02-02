package deseptikon.monya.parcels.io_excel.mapper;

import deseptikon.monya.parcels.io_excel.auxiliary.ServiceForExcel;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.reader.Row;

public class ExcelRowMapper implements ServiceForExcel {
    public Parcel parcelsRow(Row row){
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
        parcel.setArea(commaToDotCell(row.getCell(12)));
        parcel.setNote(row.getCellText(44));
        parcel.setUsageCode(row.getCellText(67));
        parcel.setPredictedUsageCode(row.getCellText(202));

        return parcel;
    }
}
