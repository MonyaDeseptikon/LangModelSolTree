package deseptikon.monya.auxiliary.prepare_tags;

import java.util.Optional;

public interface PrepareTagsKLADR {
    default StringBuilder commonTag(String tags) {
        StringBuilder readyTags = new StringBuilder();

        if (!tags.isBlank()) {
            for (String tag : tags.split(" ")) {
                readyTags.append(tag.trim().toLowerCase()).append(".*");
            }
        }
        return readyTags;
    }

    default StringBuilder cityTag(String tags) {
        StringBuilder readyTags = new StringBuilder();

        if (!tags.isBlank()) {
            readyTags.append(tags.trim().toLowerCase()).append("[^а-яА-Я]").append(".*");
        }
        return readyTags;
    }

    default StringBuilder streetTag(String tags) {
        StringBuilder readyTags = new StringBuilder();
        if (!tags.isBlank()) {
            for (String tag : tags.split(" ")) {
                if (tag.length() < 5) {
                    //10-я Подлесная, 1-я Подлесная
                    readyTags = tag.matches("[0-9]+") ? readyTags.append("[^0-9]").append(tag.trim()).append("($|[^0-9])").append(".*") : readyTags.append(tag.trim().toLowerCase()).append(".*");

                } else {
                    readyTags.append("[^а-яА-Я]").append(tag.toLowerCase().trim()).append(".*");
                }
            }
        }
        return readyTags;
    }


    default StringBuilder streetTypeTag(String tags) {
        StringBuilder readyTags = new StringBuilder();

        if (!tags.isBlank()) {
            for (String tag : tags.split(" ")) {
                //Чтобы не нашлась ул в переулке
                readyTags = (tag.equals("ул")) ? readyTags.append("[^а-яА-Я]").append(tag.trim().toLowerCase()).append(".*") : readyTags.append(tag.trim().toLowerCase()).append(".*");
            }
        }
        return readyTags;
    }
}
