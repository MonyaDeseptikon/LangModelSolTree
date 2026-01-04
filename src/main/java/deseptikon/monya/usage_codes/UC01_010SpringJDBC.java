package deseptikon.monya.usage_codes;

import deseptikon.monya.spring_jdbc.jdbc.QueryParcel;
import deseptikon.monya.spring_jdbc.model.Parcel;

import java.sql.SQLException;
import java.util.*;

public class UC01_010SpringJDBC {
    List<String> excludeTagsTemplate = List.of("животноводс", "скотоводс", "садоводс", "звероводс", "птицеводс", "пчеловодс", "свиноводс", "рыбоводс", "индивиду", "отдых",
            "жило", "дачн", "овощеводст", "личн");
    List<Conditions> conditionsList = List.of(
            new Conditions(List.of("[^\\d\\.]" + "1\\s*\\.\\s*1" + "[^\\.\\d]"),
                    excludeTagsTemplate, "", 0F, Float.POSITIVE_INFINITY, 0.1F),
            new Conditions(List.of("сельскохозяйствен", "использова"),
                    excludeTagsTemplate, "", 0F, Float.POSITIVE_INFINITY, 0.1F),
            new Conditions(List.of("сельскохозяйствен", "произв"),
                    List.of(), "", 1000000F, Float.POSITIVE_INFINITY, 0.1F)
            ,
            new Conditions(List.of("сельскохозяйствен", "деятельн"),
                    excludeTagsTemplate, "", 0F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("сельскохозяйствен", "угод"),
                    excludeTagsTemplate, "", 0F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("крестьянск", "хозяйс"),
                    excludeTagsTemplate, "", 100000F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("многолет", "насажде"),
                    excludeTagsTemplate, "", 0F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("фермерск", "хозяйс"),
                    excludeTagsTemplate, "", 100000F, Float.POSITIVE_INFINITY, 0.1F),

            new Conditions(List.of("растениеводс"),
                    excludeTagsTemplate, "", 0F, Float.POSITIVE_INFINITY, 0.1F)
    );

    String usageCode = "01:010";

    public void assignmentCode(QueryParcel queryTemplate) throws SQLException {
        Set<Parcel> parcelList = new HashSet<>();



        for (Conditions condition : conditionsList) {

            StringBuilder tags = new StringBuilder();
            for (String tag : condition.getTags()) {
                if (tags.isEmpty()) {
                    tags.append(".*").append(tag).append(".*");
                } else {
                    tags.append(tag).append(".*");
                }
            }
            System.out.println(tags);
            StringBuilder excludeTags = new StringBuilder();
            if (!condition.getExcludeTags().isEmpty()) {

                for (String eTag : condition.getExcludeTags()) {
                    if (excludeTags.isEmpty()) {
                        excludeTags.append(".*").append(eTag).append(".*");
                    } else {
                        excludeTags.append("|").append(".*").append(eTag).append(".*");
                    }
                }
                System.out.println(excludeTags);
            }
            parcelList.addAll(queryTemplate.getListParcelsByTags(tags, excludeTags, condition.getInnerCadastralNumbers(), condition.getMoreThisArea(), condition.getLessThisArea()));
        }

        Set<Integer> idList = new HashSet<>();
        parcelList.forEach(p -> idList.add(p.getId()));
        queryTemplate.updateParcels(idList, usageCode);

        int codeCount = 0;
        for (Parcel parcel : parcelList) {
            System.out.println(parcel);
            codeCount++;
        }
        System.out.println(codeCount);

    }


}
