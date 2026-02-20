package deseptikon.monya.configuration.io_excel.mapper;

import deseptikon.monya.configuration.io_excel.auxiliary.ServiceForExcel;
import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.reader.Row;

import java.util.List;

public class ExcelRowMapper implements ServiceForExcel {
    public Parcel parcelsRow(Row row) {
        Parcel parcel = new Parcel();
        parcel.setCadastralNumber(row.getCellText(2));
        parcel.setCadastralBlock(row.getCellText(4));
        parcel.setArea(commaToDotCell(row.getCell(8)));
        parcel.setOKATO(row.getCellText(18));
        parcel.setOKTMO(row.getCellText(19));
        parcel.setKLADR(row.getCellText(20));
        parcel.setDISTRICT(row.getCellText(25));
        parcel.setTYPE_DISTRICT(row.getCellText(27));
        parcel.setCITY(row.getCellText(29));
        parcel.setTYPE_CITY(row.getCellText(31));
        parcel.setSOVIET_VILLAGE(row.getCellText(35));
        parcel.setLocality(row.getCellText(37));
        parcel.setTYPE_LOCALITY(row.getCellText(39));
        parcel.setSTREET(row.getCellText(43));
        parcel.setTYPE_STREET(row.getCellText(45));
        parcel.setOther(row.getCellText(59));
        parcel.setNote(row.getCellText(60));
        //Наименование документа об утверждении кадастровой стоимости  _Вывод_ из 1С
        parcel.setApprovalDocumentName(row.getCellText(75));
        parcel.setCategory(row.getCellText(77));
        parcel.setUtilizationLandUse(row.getCellText(79));
        parcel.setUtilizationByDoc(row.getCellText(81));
        parcel.setUtilizationPermittedUseText(row.getCellText(85));
        parcel.setUsageCode(row.getCellText(117));
        parcel.setInnerCadastralNumbers(List.of(row.getCellText(219).split("_x000D_\\n")));

        return parcel;
    }

    public CodeKLADR codeKLADRRow(Row row) {
        CodeKLADR codeKLADR = new CodeKLADR();
        codeKLADR.setCity(row.getCellText(0));
        codeKLADR.setTypeCity(row.getCellText(11));
        codeKLADR.setDistrict(row.getCellText(1));
        codeKLADR.setTypeLocality(row.getCellText(2));
        codeKLADR.setLocality(row.getCellText(3));
        codeKLADR.setTypeStreet(row.getCellText(5));
        codeKLADR.setStreet(row.getCellText(4));
        codeKLADR.setCodeKLADR(row.getCellText(6));
        codeKLADR.setCodeOKATO(row.getCellText(10));

        return codeKLADR;
    }
}
