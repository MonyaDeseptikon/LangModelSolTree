package deseptikon.monya.service;

import java.util.List;
import java.util.Optional;

public interface PrepareTags {
    default String trimEnding(String tag) {
        int begin = 0;
        int trimEnd = 2;
        return tag.substring(begin, tag.length() - trimEnd);
    }

    default StringBuilder queryTags(List<String> tags) {
        StringBuilder queryTags = new StringBuilder();
        for (String tag : tags) {
            tag = trimEnding(tag);
            if (queryTags.isEmpty()) {
                queryTags.append(".*").append(tag).append(".*");
            } else {
                queryTags.append(tag).append(".*");
            }
        }
        System.out.println(queryTags);
        return queryTags;
    }

    default StringBuilder queryTagForCode(List<String> tags) {
        StringBuilder queryTags = new StringBuilder();
        queryTags.append(".*").append(tags.getFirst()).append(".*");
        System.out.println(queryTags);
        return queryTags;
    }

    default StringBuilder queryExcludeTags(List<String> tags) {
        StringBuilder queryExcludeTags = new StringBuilder();
        if (!tags.isEmpty()) {
            for (String eTag : tags) {
                if (queryExcludeTags.isEmpty()) {
                    queryExcludeTags.append(".*").append(eTag).append(".*");
                } else {
                    queryExcludeTags.append("|").append(".*").append(eTag).append(".*");
                }
            }
        } else {
            queryExcludeTags.append(Optional.empty());
        }
        System.out.println(queryExcludeTags);
        return queryExcludeTags;
    }
}
