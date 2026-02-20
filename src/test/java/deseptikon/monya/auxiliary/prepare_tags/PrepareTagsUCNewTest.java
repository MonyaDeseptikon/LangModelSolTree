package deseptikon.monya.auxiliary.prepare_tags;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrepareTagsUCNewTest {

    @Test
    void trimEnding() {
    }

    @Test
    void queryTags() {
        StringBuilder queryTags = new StringBuilder();
        List<String> tags = List.of("1.1");
        for (String tag : tags) {
            if (tag.matches(".*[0-9].*")) {
                tag = tag.replaceAll("\\.", "\\\\.");
                queryTags.append(".*(^|[^0-9\\.жЖоО\\-])").append(tag.trim()).append("($|[^0-9\\.])").append(".*");
//                queryTags.append(".*[^ж]\\s").append(tag.trim()).append("($|[^0-9\\.])").append(".*");
//                queryTags.append(tags.getFirst());
            } else {
                queryTags.append("(^|[^а-яА-Я])").append(tag).append(".*");
            }
        }
        System.out.println(queryTags);
        String test = "дона почта п 1.1";
        System.out.println(test);
        assertTrue(test.toLowerCase().matches(String.valueOf(queryTags)));

    }

    @Test
    void queryExcludeTags() {
        StringBuilder queryTags = new StringBuilder();
        List<String> tags = List.of("1.1");
        for (String tag : tags) {
            if (tag.matches(".*[0-9].*")) {
                tag = tag.replaceAll("\\.", "\\\\.");
                queryTags.append(".*(^|[^0-9\\.жЖоО\\-])").append(tag.trim()).append("($|[^0-9\\.])").append(".*");
//                queryTags.append(".*[^ж]\\s").append(tag.trim()).append("($|[^0-9\\.])").append(".*");
//                queryTags.append(tags.getFirst());
            } else {
                queryTags.append("(^|[^а-яА-Я])").append(tag).append(".*");
            }
        }
        System.out.println(queryTags);
        String test = "дона почта п 1.1";
        System.out.println(test);
        assertTrue(test.toLowerCase().matches(String.valueOf(queryTags)));
    }
}