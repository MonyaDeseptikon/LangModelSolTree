package deseptikon.monya.configuration.io_xml;

import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.UpdateParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class unMarshallerParcelTest {

    @Test
    void main() {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        UpdateParcelDAO updateParcelsKLADR = (UpdateParcelDAO) context.getBean("dataSourceForJdbcTemplateLMST");


        Set<Parcel> parcels = new HashSet<>();

        Parcel parcel = new Parcel();
        parcel.setKLADR("1800900012100");
        parcel.setCadastralNumber("18:08:000000:7066");
        parcels.add(parcel);


        System.out.println(parcels.size());
        updateParcelsKLADR.updateParcelsKLADR (parcels.stream().toList());

    }
}