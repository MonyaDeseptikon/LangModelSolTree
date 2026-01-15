package deseptikon.monya.usage_codes;

import deseptikon.monya.db.list_real_estate.create_tables.CreateTables;
import deseptikon.monya.spring_jdbc_parcels.jdbc.QueryParcel;
import deseptikon.monya.spring_jdbc_parcels.model.Parcel;
import deseptikon.monya.usage_codes.model.Conditions;
import deseptikon.monya.usage_codes.servives.PrepareTags;

import java.sql.SQLException;
import java.util.*;

public class UC01_010 implements PrepareTags {

//Исключаемые тэги
    List<String> excludeTagsTemplate = List.of("животноводс", "скотоводс", "садоводс", "звероводс", "птицеводс", "пчеловодс", "свиноводс", "рыбоводс", "индивиду", "отдых",
            "жил", "дачн", "овощеводст", "личн", "строительст");

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
        CreateTables.erasePredictedUC();
        Set<Integer> idList = new HashSet<>();
        parcelList.forEach(p -> idList.add(p.getId()));
        queryTemplate.concatParcelsPredictedUsageCode(idList, usageCode);

        int codeCount = 0;
        for (Parcel parcel : parcelList) {
            System.out.println(parcel);
            codeCount++;
        }
        System.out.println(codeCount);
    }
}
