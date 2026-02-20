package deseptikon.monya.kladr;

import deseptikon.monya.auxiliary.prepare_tags.PrepareTagsKLADR;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.auxiliary_jdbc.AuxiliaryTableQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.CombineMethodsParcel;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.GetParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.UpdateParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static deseptikon.monya.configuration.db.create_tables.parcel_tables.ParcelCreateProvisionalList.eraseKLADR;

public class ExposeKLADRWithInnerJoin implements PrepareTagsKLADR {
    public static void main(String[] args) throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");

        UpdateParcelDAO updateParcel = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        CombineMethodsParcel combineQuery = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
//        eraseKLADR();
//        VIEW_KLADR_TEST
//        VIEW_KLADR
//        VIEW_KLADR_TEST_CN
        String parcelTableName = "VIEW_KLADR";
        List<Parcel> parcelList = combineQuery.getListParcelsByTagsKLADRInnerJoin(parcelTableName);
        updateParcel.concatParcelsKLADRTags(parcelList);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 /60 + " минут");
    }
}
