package deseptikon.monya.configuration.usage_codes;

import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.models.Building;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import deseptikon.monya.auxiliary.prepare_tags.PrepareTagsUC;
import deseptikon.monya.usage_code_new.usage_codes_old.model.Conditions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

class UC01_150Test  implements PrepareTagsUC {

    //    JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) context.getBean("dataSourceConnection"));
//    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
    LmstQuery queryTemplate = (LmstQuery) context.getBean("dataSourceForJdbcTemplateLMST");

    @Test
    void assignmentCode() {
        Set<Parcel> parcelListAll = new HashSet<>();
        Set<Parcel> parcelListToCheckArea = new HashSet<>();
        Set<Building> buildingListToCheckArea = new HashSet<>();

        Conditions condition = new Conditions(List.of("сельскохозяйственное", "производство"),
                List.of(), 0F, 200000F, 0.1F);

            StringBuilder tags;
            tags = queryTags(condition.getTags());

            StringBuilder excludeTags;
            excludeTags = queryExcludeTags(condition.getExcludeTags());

            parcelListAll.addAll(queryTemplate.getListParcelsByTagsWithoutICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea()));

            parcelListToCheckArea.addAll(queryTemplate.getListParcelsByTagsJoinListICN(tags, excludeTags, "BUILDINGS.PARCEL_INNER_CN",
                    List.of("0701", "0704")));

        //Формирования списка кадастровых номеров ОКС для запроса площади в БД
        Set<String> innerCNListBuildings = new HashSet<>();
        parcelListToCheckArea.forEach(p -> innerCNListBuildings.addAll(p.getInnerCadastralNumbers()));
        buildingListToCheckArea.addAll(queryTemplate.getListAreaBuildings(innerCNListBuildings));
        buildingListToCheckArea.forEach(b-> System.out.println(b.getCadastral_number()+" "+b.getArea()));

        //Формирование Мапы для проверки кадастровых номеров ОКС, расположенных на ЗУ
        Map<String, Float> buildingCNArea = new HashMap<>();
        buildingListToCheckArea.forEach(b -> buildingCNArea.put(b.getCadastral_number(), b.getArea()));


        //Исключение из списка Участков, участков у которых площадь ОКС менее 5% или 10%
        System.out.println("Записи с ОКС, пересекающиеся по шифрам со таблицей ОКС, всего  = "+parcelListToCheckArea.size());
        Iterator<Parcel> iteratorParcel = parcelListToCheckArea.iterator();
        while (iteratorParcel.hasNext()) {
            Parcel parcelTemp = iteratorParcel.next();
            float sumAreaBuildings = 0F;
            for (String innerCN : parcelTemp.getInnerCadastralNumbers()) {
                sumAreaBuildings = sumAreaBuildings + Optional.ofNullable(buildingCNArea.get(innerCN)).orElse(0F);
            }
            if (sumAreaBuildings / parcelTemp.getArea() < 0.05F) iteratorParcel.remove();
        }
        System.out.println("Записи с ОКС, пересекающиеся по шифрам со таблицей ОКС, после удаления записей по площади ОКС  = "+parcelListToCheckArea.size());

        parcelListAll.addAll(parcelListToCheckArea);

        Set<Integer> idList = new HashSet<>();
        parcelListAll.forEach(p -> idList.add(p.getId()));
//        queryTemplate.concatParcelsPredictedUsageCode(idList, "01:150");

        System.out.println("Весь список = "+parcelListAll.size());

        System.out.println("Только уникальные ИД из всего списка = "+idList.size());

        ///Также протестированы разделение строки. LineSeparator не работает.
        /*
                parcel.setInnerCadastralNumbers(List.of(rs.getString("inner_cadastral_numbers").split("_x000D_\\n")));
//        parcel.setInnerCadastralNumbers(List.of(rs.getString("inner_cadastral_numbers").split(System.lineSeparator())));
         */


    }
}