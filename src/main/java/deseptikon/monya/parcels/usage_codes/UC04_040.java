package deseptikon.monya.parcels.usage_codes;

import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.QueryParcel;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.usage_codes.model.Conditions;
import deseptikon.monya.parcels.usage_codes.model.UC;
import deseptikon.monya.parcels.usage_codes.model.UCBuilder;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UC04_040 extends UC implements UCBuilder {


    @Override
    public void assignmentCode(QueryParcel queryTemplate) throws SQLException {
        Set<Parcel> parcelList = new HashSet<>();



        parcelList.addAll(queryTemplate.getListParcelsByTags(queryTagForCode(setCodeOnly().getTags()), queryExcludeTags(setCodeOnly().getExcludeTags()),
                setCodeOnly().getMoreThisArea(), setCodeOnly().getLessThisArea()));

        for (Conditions condition : setConditionsList()) {
            StringBuilder tags;
            tags = queryTags(condition.getTags());

            StringBuilder excludeTags;
            excludeTags = queryExcludeTags(condition.getExcludeTags());

            parcelList.addAll(queryTemplate.getListParcelsByTagsWithoutICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea()));
            parcelList.addAll(queryTemplate.getListParcelsByTagsJoinICN(tags, excludeTags, condition.getMoreThisArea(), condition.getLessThisArea(), setInnerCNTableName()));
        }

        Set<Integer> idList = new HashSet<>();
        parcelList.forEach(p -> idList.add(p.getId()));
        queryTemplate.concatParcelsPredictedUsageCode(idList, setUsageCode());

        System.out.println(parcelList.size());
    }





    @Override
    public List<String> setExcludeTagsTemplate() {
        return List.of();
    }

    @Override
    public Conditions setCodeOnly() {
        return new Conditions(List.of("[^\\d\\.]" + "4\\s*\\.\\s*4" + "[^\\.\\d]"),
                setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F);
    }


    @Override
    public List<Conditions> setConditionsList() {
       return  List.of(
//            new Conditions(List.of("магазин"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("продажа", "товаров"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("размещение", "киоска"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("торговая", "палатка"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("коммерческая", "деятельность"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("торговый", "павильон"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("автосалон"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("аптека"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("остановочный", "комплекс"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("оптика"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F),
//            new Conditions(List.of("рекламная", "конструкция"),
//                    excludeTagsTemplate, 0F, Float.POSITIVE_INFINITY, 0F)

                new Conditions(List.of("магазин"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("продажа"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("киоска"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("палатка"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("коммерческая"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("торговый"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("автосалон"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("аптека"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("остановочный", "комплекс"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("оптика"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("рекламная", "конструкция"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F),
                new Conditions(List.of("павильон"),
                        setExcludeTagsTemplate(), 0F, Float.POSITIVE_INFINITY, 0F)
        );
    }

    @Override
    public String setUsageCode() {
        return "04:040";
    }

    @Override
    public boolean setIsEmptyInnerCN() {
        return false;
    }

    @Override
    public String setInnerCNTableName() {
        return "PARCELS.INNER_CN_04";
    }
}
