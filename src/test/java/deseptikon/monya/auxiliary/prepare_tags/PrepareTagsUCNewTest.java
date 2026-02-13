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
                queryTags.append("(^|[^0-9]|[^0-9][^\\\\.])").append(tag.trim()).append("($|[^0-9])").append(".*");
            } else {

                queryTags.append("(^|[^а-яА-Я])").append(tag).append(".*");
            }
        }
        System.out.println(queryTags);

    }

    @Test
    void queryExcludeTags() {
    }
}