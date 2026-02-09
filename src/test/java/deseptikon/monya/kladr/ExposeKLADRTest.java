package deseptikon.monya.kladr;

import deseptikon.monya.auxiliary.prepare_tags.PrepareTagsKLADR;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.auxiliary_jdbc.AuxiliaryTableQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.GetParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExposeKLADRTest implements PrepareTagsKLADR {

    @Test
    public void main() throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        AuxiliaryTableQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        GetParcelDAO queryTemplateParcel = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        ExposeKLADR exposeKLADR = new ExposeKLADR();

        List<CodeKLADR> codeKLADRList = exposeKLADR.getKLADRList(queryTemplate);
        List<Parcel> parcelList = exposeKLADR.iterateKLADRList(queryTemplateParcel, codeKLADRList);
        parcelList.forEach(p -> System.out.println(p));

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

        for (CodeKLADR code : codeKLADRList.subList(0, 10)) {
            List<Parcel> parcelListTemp = new ArrayList<>();
            System.out.println(commonTag(code.getDistrict()));
            System.out.println(cityTag(code.getCity()));
            System.out.println(commonTag(code.getTypeLocality()));
            System.out.println(commonTag(code.getLocality()));
            System.out.println(commonTag(code.getTypeStreet()));
            System.out.println(commonTag(code.getStreet()));

            parcelListTemp.addAll(queryTemplate.getListParcelsByTagsKLADRNote(commonTag(code.getDistrict()), cityTag(code.getCity()), commonTag(code.getTypeLocality()),
                    commonTag(code.getLocality()), commonTag(code.getTypeStreet()), commonTag(code.getStreet())));

            parcelListTemp.forEach(p -> p.setExpKLADR(code.getCodeKLADR()));
            parcelListTemp.forEach(p -> System.out.println(p));
            parcelList.addAll(parcelListTemp);

        }

        return parcelList;
    }

}