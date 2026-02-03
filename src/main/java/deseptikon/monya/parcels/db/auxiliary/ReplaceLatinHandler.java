package deseptikon.monya.parcels.db.auxiliary;


import deseptikon.monya.auxiliary.ReplaceLatin;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.LmstQuery;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReplaceLatinHandler implements ReplaceLatin {


    public static void main(String[] args) throws SQLException {


        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");

        List<Parcel> parcelList = queryTemplate.getListParcelsForReplaceLatin(List.of(
                "id",
                "utilization_by_doc",
                "category",
                "note",
                "locality",
                "utilization_permitted_use_text",
                "utilization_land_use",
                "other",
                "TYPE_STREET",
                "STREET",
                "TYPE_LOCALITY",
                "locality",
                "TYPE_CITY",
                "CITY",
                "TYPE_DISTRICT",
                "DISTRICT"));


//        int codeCount = 0;
//        for (Parcel parcel : parcelList) {
//            System.out.printf("%s, %s, %s, %s \n",parcel.getId(), parcel.getCategory(), parcel.getUtilizationByDoc(), parcel.getNote(), parcel.getLocality());
//            codeCount++;
//        }
//        System.out.println(codeCount);
        List<Parcel> parcelWithLatin = new ReplaceLatinHandler().replaceLatinRow(parcelList);
        int codeCount = 0;
        for (Parcel parcel : parcelWithLatin) {
            System.out.printf("%s, %s, %s, %s, %s\n", parcel.getId(), parcel.getCategory(), parcel.getUtilizationByDoc(), parcel.getNote(), parcel.getLocality());
            codeCount++;
        }
        System.out.println(codeCount);

        queryTemplate.updateParcelsForReplaceLatin(parcelWithLatin);

    }

    private List<Parcel> replaceLatinRow(List<Parcel> parcelList) {
        List<Parcel> parcelListChanged = new ArrayList<>();
        int count = 0;
        for (Parcel parcel : parcelList) {
            if (checkingLatin(parcel.getCategory()) || checkingLatin(parcel.getNote()) || checkingLatin(parcel.getLocality()) || checkingLatin(parcel.getUtilizationByDoc())
                    || checkingLatin(parcel.getUtilizationPermittedUseText()) || checkingLatin(parcel.getUtilizationLandUse()) || checkingLatin(parcel.getOther()) || checkingLatin(parcel.getTYPE_STREET())
                    || checkingLatin(parcel.getSTREET()) || checkingLatin(parcel.getTYPE_LOCALITY()) || checkingLatin(parcel.getTYPE_CITY()) || checkingLatin(parcel.getCITY())
                    || checkingLatin(parcel.getTYPE_DISTRICT()) || checkingLatin(parcel.getDISTRICT())) {

                parcel.setCategory(replaceLatinAndYoChar(parcel.getCategory()));
                parcel.setNote(replaceLatinAndYoChar(parcel.getNote()));
                parcel.setLocality(replaceLatinAndYoChar(parcel.getLocality()));
                parcel.setUtilizationByDoc(replaceLatinAndYoChar(parcel.getUtilizationByDoc()));
                parcel.setUtilizationPermittedUseText(replaceLatinAndYoChar(parcel.getUtilizationPermittedUseText()));
                parcel.setUtilizationLandUse(replaceLatinAndYoChar(parcel.getUtilizationLandUse()));
                parcel.setOther(replaceLatinAndYoChar(parcel.getOther()));
                parcel.setTYPE_STREET(replaceLatinAndYoChar(parcel.getTYPE_STREET()));
                parcel.setSTREET(replaceLatinAndYoChar(parcel.getSTREET()));
                parcel.setTYPE_LOCALITY(replaceLatinAndYoChar(parcel.getTYPE_LOCALITY()));
                parcel.setTYPE_CITY(replaceLatinAndYoChar(parcel.getTYPE_CITY()));
                parcel.setCITY(replaceLatinAndYoChar(parcel.getCITY()));
                parcel.setTYPE_DISTRICT(replaceLatinAndYoChar(parcel.getTYPE_DISTRICT()));
                parcel.setDISTRICT(replaceLatinAndYoChar(parcel.getDISTRICT()));

                parcelListChanged.add(parcel);
                count++;
            }
        }

        return parcelListChanged;
    }

}
