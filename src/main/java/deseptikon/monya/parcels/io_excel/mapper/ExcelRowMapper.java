package deseptikon.monya.parcels.io_excel.mapper;

import deseptikon.monya.parcels.io_excel.auxiliary.ServiceForExcel;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.reader.Row;

import java.util.List;

public class ExcelRowMapper implements ServiceForExcel {
    public Parcel parcelsRow(Row row) {
        Parcel parcel = new Parcel();
        parcel.setCadastralNumber(row.getCellText(2));
        parcel.setArea(commaToDotCell(row.getCell(8)));
        parcel.setOKATO(row.getCellText(19));
        parcel.setOKTMO(row.getCellText(20));
        parcel.setKLADR(row.getCellText(22));
        parcel.setDISTRICT(row.getCellText(28));
        parcel.setTYPE_DISTRICT(row.getCellText(30));
        parcel.setCITY(row.getCellText(32));
        parcel.setTYPE_CITY(row.getCellText(34));
        parcel.setSOVIET_VILLAGE(row.getCellText(38));
        parcel.setLocality(row.getCellText(40));
        parcel.setTYPE_LOCALITY(row.getCellText(42));
        parcel.setSTREET(row.getCellText(46));
        parcel.setTYPE_STREET(row.getCellText(48));
        parcel.setOther(row.getCellText(58));
        parcel.setNote(row.getCellText(60));
        //Наименование документа об утверждении кадастровой стоимости  _Вывод_ из 1С
        parcel.setApprovalDocumentName(row.getCellText(74));
        parcel.setCategory(row.getCellText(76));
        parcel.setUtilizationLandUse(row.getCellText(78));
        parcel.setUtilizationByDoc(row.getCellText(80));
        parcel.setUtilizationPermittedUseText(row.getCellText(84));
        parcel.setUsageCode(row.getCellText(116));
        parcel.setInnerCadastralNumbers(List.of(row.getCellText(208).split("_x000D_\\n")));

        return parcel;
    }
}
