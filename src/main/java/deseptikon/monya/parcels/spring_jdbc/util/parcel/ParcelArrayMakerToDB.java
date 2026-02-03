package deseptikon.monya.parcels.spring_jdbc.util.parcel;

import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface ParcelArrayMakerToDB {
    default MapSqlParameterSource[] ParcelArrayMakerToDBForReplaceLatin(List<Parcel> parcels) {
        MapSqlParameterSource[] parameters = new MapSqlParameterSource[parcels.size()];
        int count = 0;
        for (Parcel parcel : parcels) {
            MapSqlParameterSource parameter = new MapSqlParameterSource();
            parameter.addValue("idList", parcel.getId());
            parameter.addValue("utilization_by_docList", parcel.getUtilizationByDoc());
            parameter.addValue("categoryList", parcel.getCategory());
            parameter.addValue("noteList", parcel.getNote());
            parameter.addValue("localityList", parcel.getLocality());
            parameter.addValue("DISTRICT", parcel.getDISTRICT());
            parameter.addValue("TYPE_DISTRICT", parcel.getTYPE_DISTRICT());
            parameter.addValue("CITY", parcel.getCITY());
            parameter.addValue("TYPE_CITY", parcel.getTYPE_CITY());
            parameter.addValue("TYPE_LOCALITY", parcel.getTYPE_LOCALITY());
            parameter.addValue("STREET", parcel.getSTREET());
            parameter.addValue("TYPE_STREET", parcel.getTYPE_STREET());
            parameter.addValue("OTHER", parcel.getOther());
            parameter.addValue("UTILIZATION_LAND_USE", parcel.getUtilizationLandUse());
            parameter.addValue("UTILIZATION_PERMITTED_USE_TEXT", parcel.getUtilizationPermittedUseText());

            parameters[count++] = parameter;
        }
        return parameters;
    }

    default MapSqlParameterSource[] excelParcelToDB(List<Parcel> parcelList) {
        MapSqlParameterSource[] parameters = new MapSqlParameterSource[parcelList.size()];
        int count = 0;

        for (Parcel parcel : parcelList) {
            MapSqlParameterSource parameter = new MapSqlParameterSource();

            if (parcel.getCadastralNumber().length() > 20) {
                File file = new File("checkLongCN");
                try {
                    FileWriter writer = new FileWriter(file, true);
                    writer.append(parcel.getCadastralNumber()).append('\n');
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка записи длинных КН в файл");
                }
                parcel.setCadastralNumber(parcel.getCadastralNumber().substring(0, 19));
            }
            parameter.addValue("CADASTRAL_NUMBER", parcel.getCadastralNumber());
            parameter.addValue("AREA", parcel.getArea());
            parameter.addValue("OKATO", parcel.getOKATO());
            parameter.addValue("OKTMO", parcel.getOKTMO());
            parameter.addValue("KLADR", parcel.getKLADR());
            parameter.addValue("DISTRICT", parcel.getDISTRICT());
            parameter.addValue("TYPE_DISTRICT", parcel.getTYPE_DISTRICT());
            parameter.addValue("CITY", parcel.getCITY());
            parameter.addValue("TYPE_CITY", parcel.getTYPE_CITY());
            parameter.addValue("LOCALITY", parcel.getLocality());
            parameter.addValue("TYPE_LOCALITY", parcel.getTYPE_LOCALITY());
            parameter.addValue("SOVIET_VILLAGE", parcel.getSOVIET_VILLAGE());
            parameter.addValue("STREET", parcel.getSTREET());
            parameter.addValue("TYPE_STREET", parcel.getTYPE_STREET());
            parameter.addValue("OTHER", parcel.getOther());
            parameter.addValue("NOTE", parcel.getNote());
            parameter.addValue("APPROVAL_DOCUMENT_NAME", parcel.getApprovalDocumentName());
            parameter.addValue("CATEGORY", parcel.getCategory());
            parameter.addValue("UTILIZATION_LAND_USE", parcel.getUtilizationLandUse());
            parameter.addValue("UTILIZATION_BY_DOC", parcel.getUtilizationByDoc());
            parameter.addValue("UTILIZATION_PERMITTED_USE_TEXT", parcel.getUtilizationPermittedUseText());
            parameter.addValue("INNER_CADASTRAL_NUMBERS", parcel.getInnerCadastralNumbersString());
            parameter.addValue("USAGE_CODE", parcel.getUsageCode());

            parameters[count++] = parameter;
        }
        return parameters;
    }
}
