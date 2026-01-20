package deseptikon.monya.parcels.usage_codes;

import deseptikon.monya.parcels.db.create_tables.CreateProvisionalList;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.QueryParcel;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.usage_codes.model.Conditions;
import deseptikon.monya.parcels.usage_codes.auxiliary.PrepareTags;
import deseptikon.monya.parcels.usage_codes.model.UC;

import java.sql.SQLException;
import java.util.*;

public class UC01_010 extends UC {

    //Исключаемые тэги
    List<String> excludeTagsTemplate = List.of("животноводство", "скотоводство", "садоводство", "звероводство", "птицеводство", "пчеловодство", "свиноводство", "рыбоводство", "индивидуальный", "отдых",
            "жилой", "дачный", "овощеводство", "личный", "строительство");

    //Поиск кода вида использования и условий
    //Точку внутри кода обязательно экранировать, - иначе воспринимает как любой символ
    Conditions codeOnly = new Conditions(List.of("[^\\d\\.]" + "1\\s*\\.\\s*1" + "[^\\.\\d]"),
            excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0.1F);

    //Поиск тэгов и условий
    List<Conditions> conditionsList = List.of(
            new Conditions(List.of("сельскохозяйственное", "использование"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0.1F),
            new Conditions(List.of("сельскохозяйственное", "производство"),
                    List.of(), 1000000F, Float.POSITIVE_INFINITY, 0.1F)
            ,
            new Conditions(List.of("сельскохозяйственная", "деятельность"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("сельскохозяйственные", "угодья"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("крестьянское", "хозяйство"),
                    excludeTagsTemplate, 100000F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("многолетние", "насаждения"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("фермерское", "хозяйство"),
                    excludeTagsTemplate, 100000F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("растениеводство"),
                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0.1F)
    );

    String usageCode = "01:010";

    @Override
    public void assignmentCode(QueryParcel queryTemplate) throws SQLException {
        Set<Parcel> parcelList = new HashSet<>();

        parcelList.addAll(queryTemplate.getListParcelsByTags(queryTagForCode(codeOnly.getTags()), queryExcludeTags(codeOnly.getExcludeTags()),
                codeOnly.getMoreThisArea(), codeOnly.getLessThisArea()));

        for (Conditions condition : conditionsList) {
            StringBuilder tags = new StringBuilder();
            tags = queryTags(condition.getTags());

            StringBuilder excludeTags = new StringBuilder();
            excludeTags = queryExcludeTags(condition.getExcludeTags());

            parcelList.addAll(queryTemplate.getListParcelsByTagsWithoutICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea()));
        }

        Set<Integer> idList = new HashSet<>();
        parcelList.forEach(p -> idList.add(p.getId()));
        queryTemplate.concatParcelsPredictedUsageCode(idList, usageCode);

//        int codeCount = 0;
//        for (Parcel parcel : parcelList) {
//            System.out.println(parcel);
//            codeCount++;
//        }
//        System.out.println(codeCount);
        System.out.println(parcelList.size());
    }
}
