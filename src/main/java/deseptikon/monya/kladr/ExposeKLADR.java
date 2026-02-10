package deseptikon.monya.kladr;

import deseptikon.monya.auxiliary.prepare_tags.PrepareTagsKLADR;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.auxiliary_jdbc.AuxiliaryTableQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.GetParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.UpdateParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static deseptikon.monya.configuration.db.create_tables.parcel_tables.ParcelCreateProvisionalList.eraseKLADR;

public class ExposeKLADR implements PrepareTagsKLADR {
    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");

        AuxiliaryTableQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        GetParcelDAO queryTemplateParcel = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        UpdateParcelDAO updateParcel = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        ExposeKLADR exposeKLADR = new ExposeKLADR();
        eraseKLADR();


        List<CodeKLADR> codeKLADRList = exposeKLADR.getKLADRList(queryTemplate);
        List<Parcel> parcelList = exposeKLADR.iterateKLADRList(queryTemplateParcel, codeKLADRList);

        updateParcel.concatParcelsKLADRTags(parcelList);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }


    public List<CodeKLADR> getKLADRList(AuxiliaryTableQuery queryTemplate) {
        List<CodeKLADR> codeKLADRList = new ArrayList<>();
        codeKLADRList.addAll(queryTemplate.getListCodeKLADR());
        return codeKLADRList;
    }


    public List<Parcel> iterateKLADRList(GetParcelDAO queryTemplate, List<CodeKLADR> codeKLADRList) {
        List<Parcel> parcelList = new ArrayList<>();

        for (CodeKLADR code : codeKLADRList) {
            List<Parcel> parcelListTemp = new ArrayList<>();

            parcelListTemp.addAll(queryTemplate.getListParcelsByTagsKLADRNote(commonTag(code.getDistrict()), commonTag(code.getTypeCity()), cityTag(code.getCity()), commonTag(code.getTypeLocality()),
                    commonTag(code.getLocality()), streetTypeTag(code.getTypeStreet()), streetTag(code.getStreet())));

            parcelListTemp.forEach(p -> {
                p.setExpKLADR(code.getCodeKLADR());
                p.setREGEXP(String.valueOf(commonTag(code.getDistrict()).append(cityTag(code.getCity())).append(commonTag(code.getTypeLocality()))
                        .append(commonTag(code.getLocality())).append(streetTypeTag(code.getTypeStreet())).append(streetTag(code.getStreet()))));
            });
            parcelListTemp.forEach(p -> System.out.println(p));
            parcelList.addAll(parcelListTemp);

        }

        return parcelList;
    }

}
