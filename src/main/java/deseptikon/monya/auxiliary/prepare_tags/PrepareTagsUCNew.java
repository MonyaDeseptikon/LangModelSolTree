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

        return queryTags;

    }

    default StringBuilder queryExcludeTags(List<String> commonExcludeTags, List<String> internalExcludeTags, List<String> tags) {
        StringBuilder queryExcludeTags = new StringBuilder();
        if (!commonExcludeTags.isEmpty()) {
            for (String eTag : commonExcludeTags) {
                eTag = trimEnding(eTag);
                if (queryExcludeTags.isEmpty()) {
                    queryExcludeTags.append("(^|[^а-яА-Я])").append(eTag).append(".*");
                } else {
                    queryExcludeTags.append("|").append("(^|[^а-яА-Я])").append(eTag).append(".*");
                }
            }
        }
        if (!internalExcludeTags.isEmpty()) {
            for (String eTag : internalExcludeTags) {
                eTag = trimEnding(eTag);

                for (String tag : tags) {
                    StringBuilder queryTag = new StringBuilder();
                    if (tag.matches(".*[0-9].*")) {
                        tag = tag.replaceAll("\\.", "\\\\.");
                        queryTag.append("[^0-9\\.]").append(tag.trim()).append("($|[^0-9\\.])").append(".*");
                    } else {
                        tag = trimEnding(tag);
                        queryTag.append("[^а-яА-Я]").append(tag).append(".*");
                    }

                    if (queryExcludeTags.isEmpty()) {
                        queryExcludeTags.append("(^|[^а-яА-Я])").append(eTag).append(".*").append(queryTag);
                    } else {
                        queryExcludeTags.append("|").append("(^|[^а-яА-Я])").append(eTag).append(".*").append(queryTag);
                    }
                }


            }
        }


        return queryExcludeTags;
    }
}
