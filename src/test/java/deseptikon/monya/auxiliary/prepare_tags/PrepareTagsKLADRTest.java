package deseptikon.monya.auxiliary.prepare_tags;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrepareTagsKLADRTest {

    @Test
    void commonTag() {
        String tags = "ижевск ул 10 я малиновая гора";
        StringBuilder readyTags = new StringBuilder();

        if (!tags.isBlank()) {
            for (String tag : tags.split(" ")) {
                readyTags.append(tag.trim().toLowerCase()).append(".*");
            }
        }

    }

    @Test
    void cityTag() {
        String tags = "воткинск";
        StringBuilder readyTags = new StringBuilder();

        if (!tags.isBlank()) {
            readyTags.append(tags.trim().toLowerCase()).append("[^а-яА-Я]").append(".*");
        }


    }

    @Test
    void streetTag() {
//        String tags = "ул 10 я малиновая гора";
        String test = "Ижевск переулок Малиновая Гора 10";
        String tags = "Ижевск ул малиновая гора 10";
        StringBuilder readyTags = new StringBuilder();

        for (String tag : tags.split(" ")) {

            if (tag.length() < 5) {
                //10-я Подлесная, 1-я Подлесная
                if (tag.matches("[0-9]+")){
                    readyTags.append("[^0-9]").append(tag.trim()).append("($|[^0-9])").append(".*");
                }
                //ул-> проулок, переулок
                else {
                    readyTags = tag.matches("ул") ? readyTags.append("[^а-яА-Я]").append(tag.trim().toLowerCase()).append(".*") : readyTags.append(tag.trim().toLowerCase()).append(".*");
                }

//                readyTags = tag.matches("[0-9]+") ? readyTags.append("[^0-9]").append(tag.trim()).append("($|[^0-9])").append(".*") : readyTags.append(tag.trim().toLowerCase()).append(".*");
//                readyTags = tag.matches("ул") ? readyTags.append("[^а-яА-Я]").append(tag.trim().toLowerCase()).append(".*") : readyTags.append(tag.trim().toLowerCase()).append(".*");
            } else {
                readyTags.append("[^а-яА-Я]").append(tag.toLowerCase().trim()).append(".*");
            }
        }

        System.out.println(readyTags);


        assertEquals( true, test.toLowerCase().matches(String.valueOf(readyTags)));
    }


}
