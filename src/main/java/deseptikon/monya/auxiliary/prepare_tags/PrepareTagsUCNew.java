package deseptikon.monya.auxiliary.prepare_tags;

import java.util.List;
import java.util.Optional;

public interface PrepareTagsUCNew {
    default String trimEnding(String tag) {
        int begin = 0;
        int trimEnd = 2;
        return tag.substring(begin, tag.length() - trimEnd);
    }

    default StringBuilder queryTags(List<String> tags) {
        StringBuilder queryTags = new StringBuilder();
        for (String tag : tags) {
            if (tag.matches(".*[0-9].*")) {
                tag = tag.replaceAll("\\.", "\\\\.");
                queryTags.append("(^|[^0-9\\.жЖоО\\-])").append(tag.trim()).append("($|[^0-9\\.])").append(".*");
            } else {
                tag = trimEnding(tag);
                queryTags.append("(^|[^а-яА-Я])").append(tag).append(".*");
            }
        }
        System.out.println(queryTags);
        return queryTags;

    }


    default StringBuilder queryExcludeTags(List<String> tags) {
        StringBuilder queryExcludeTags = new StringBuilder();
        if (!tags.isEmpty()) {
            for (String eTag : tags) {
                eTag = trimEnding(eTag);
                if (queryExcludeTags.isEmpty()) {
                    queryExcludeTags.append("(^|[^а-яА-Я])").append(eTag).append(".*");
                } else {
                    queryExcludeTags.append("|").append("(^|[^а-яА-Я])").append(eTag).append(".*");
                }
            }
        } else {
            queryExcludeTags.append("ИСКЛЮЧАЮЩИХ ТЭГОВ НЕТ");
        }
        System.out.println(queryExcludeTags);
        return queryExcludeTags;
    }
}
