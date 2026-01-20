package deseptikon.monya.parcels.db.auxiliary;


import deseptikon.monya.auxiliary.ReplaceLatin;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.QueryParcel;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReplaceLatinHandler implements ReplaceLatin {


    public static void main(String[] args) throws SQLException {


        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        QueryParcel queryTemplate = (QueryParcel) context.getBean("dataSourceForJdbcTemplateParcelDaoImpl");

        List<Parcel> parcelList = queryTemplate.getListParcelsForReplaceLatin(List.of(
                "id",
                "utilization_by_doc",
                "category",
                "note",
                "locality"));

//        int codeCount = 0;
//        for (Parcel parcel : parcelList) {
//            System.out.printf("%s, %s, %s, %s \n",parcel.getId(), parcel.getCategory(), parcel.getUtilizationByDoc(), parcel.getNote(), parcel.getLocality());
//            codeCount++;
//        }
//        System.out.println(codeCount);
        List <Parcel> parcelWithLatin = new ReplaceLatinHandler().replaceLatinRow(parcelList);
                int codeCount = 0;
        for (Parcel parcel : parcelWithLatin) {
            System.out.printf("%s, %s, %s, %s, %s\n",parcel.getId(), parcel.getCategory(), parcel.getUtilizationByDoc(), parcel.getNote(), parcel.getLocality());
            codeCount++;
        }
        System.out.println(codeCount);

        queryTemplate.updateParcelsForReplaceLatin(parcelWithLatin);

    }

    private List<Parcel> replaceLatinRow(List<Parcel> parcelList) {
        List<Parcel> parcelListChanged = new ArrayList<>();
        int count = 0;
        for (Parcel parcel : parcelList) {
            if (checkingLatin(parcel.getCategory()) || checkingLatin(parcel.getNote()) || checkingLatin(parcel.getLocality()) || checkingLatin(parcel.getUtilizationByDoc())) {
                parcel.setCategory(replaceLatinChar(parcel.getCategory()));
                parcel.setNote(replaceLatinChar(parcel.getNote()));
                parcel.setLocality(replaceLatinChar(parcel.getLocality()));
                parcel.setUtilizationByDoc(replaceLatinChar(parcel.getUtilizationByDoc()));
                parcelListChanged.add(parcel);
                System.out.println(parcel.getId());
                count++;
            }
        }
        System.out.println(count);
        return parcelListChanged;
    }

}
