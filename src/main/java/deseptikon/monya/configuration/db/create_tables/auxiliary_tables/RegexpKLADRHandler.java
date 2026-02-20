package deseptikon.monya.configuration.db.create_tables.auxiliary_tables;

import deseptikon.monya.auxiliary.prepare_tags.PrepareTagsKLADR;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.auxiliary_jdbc.AuxiliaryTableQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.GetParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class RegexpKLADRHandler implements PrepareTagsKLADR {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        AuxiliaryTableQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");
        RegexpKLADRHandler regexpKLADRHandler = new RegexpKLADRHandler();

        List<CodeKLADR> codeKLADRList = queryTemplate.getListCodeKLADR();
        regexpKLADRHandler.fillingRegexp(codeKLADRList);
        queryTemplate.fillRegexpKLADRList(codeKLADRList);

    }

    private List<CodeKLADR> fillingRegexp(List<CodeKLADR> codeKLADRList) {
        codeKLADRList.forEach(cK ->
                cK.setREGEXP(String.valueOf(commonTag(cK.getDistrict()).append(commonTag(cK.getTypeCity())).append(cityTag(cK.getCity())).append(commonTag(cK.getTypeLocality()))
                        .append(cityTag(cK.getLocality())).append(streetTypeTag(cK.getTypeStreet())).append(streetTag(cK.getStreet()))))
        );
        return codeKLADRList;
    }

}
