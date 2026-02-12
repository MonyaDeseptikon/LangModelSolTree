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

    // !!!!! \\b не работает для кириллицы, поэтому его можно использовать только для цифр
    @Test
    void streetTag() {
//        String tags = "ул 10 я малиновая гора";
//        String test = "Ижевск переулок Малиновая Гора 10";
        String test = "Удмуртская Республика, Балезинский район, п. Балезино, ул. 8 Марта, 23";
        String tags = " 8 Марта";

        StringBuilder readyTags = new StringBuilder();
        if (!tags.isBlank()) {
            for (String tag : tags.split(" ")) {
                if (tag.length() < 5) {
                    //10-я Подлесная, 1-я Подлесная
                    readyTags = tag.matches("[0-9]+") ? readyTags.append("\\b").append(tag.trim()).append("\\b").append(".*") : readyTags.append(tag.trim().toLowerCase()).append(".*");

                } else {
                    readyTags.append("([^а-яА-Я]|\\b)").append(tag.toLowerCase().trim()).append(".*");
                }
            }
        }


//        StringBuilder readyTags = new StringBuilder();
//        if (!tags.isBlank()) {
//            String regexpTailForDigit = "";
//            for (String tag : tags.split(" ")) {
//
//                if (tag.length() < 5) {
//                    //10-я Подлесная, 1-я Подлесная
//                    if (tag.matches("[0-9]+")) {
//                        readyTags.append("[^0-9]").append(tag.trim()).append("($|[^0-9])").append(".*");
//                        regexpTailForDigit = "0-9";
//                    } else {
//                        readyTags.append(tag.trim().toLowerCase()).append(".*");
//                    }
//
////                    readyTags = tag.matches("[0-9]+") ? readyTags.append("[^0-9]").append(tag.trim()).append("($|[^0-9])").append(".*") : readyTags.append(tag.trim().toLowerCase()).append(".*");
//
//                } else {
//                    readyTags.append("[^").append("а-яА-Я").append(regexpTailForDigit).append("]").append(tag.toLowerCase().trim()).append(".*");
//                    regexpTailForDigit = "";
//                }
//            }
//        }

        System.out.println(readyTags);


        assertEquals(true, test.toLowerCase().matches(String.valueOf(readyTags)));
    }

    @Test
    void streetTag2() {
        String test = "город Ижевск,ул.Ангарная";
                String tags = ".*г.*ижевск(\\W).*([^а-яА-Я])ул.*([^а-яА-Я]|\\\\b)ангарная.*";
//.*([^а-яА-Я])ул.*([^а-яА-Я]|\\b)ангарная.*"
//        String tags = ".*балезинский.*п.*балезино.*[^а-яА-Я]ул.*[^0-9]8($|[^0-9]).*[^а-яА-Я]марта.*";
//        String test = "Удмуртская Республика, Балезинский район, п. Балезино, ул. 8Марта, 23";
//        String tags = ".*балезинский.*п.*балезино.*[^а-яА-Я]ул.*[^0-9]8($|[^0-9]).*[^а-яА-Я]марта.*";
//        String tags = ".*балезинский.*п.*балезино.*[^а-яА-Я]ул.*\\b8\\b.*(\\b|[^а-яА-Я])марта.*";
//        String tags = ".*балезинский.*п.*балезино.*[^а-яА-Я]ул.*[^0-9]8($|[^0-9]|[^а-яА-Я]|' '|)марта.*";

//        String tags = ".*балезинский.*п.*балезино.*[^а-яА-Я]ул.*[^0-9]8($|).*марта.*";
//        String tags = ".*балезинский.*п.*балезино.*[^а-яА-Я]ул.*[^0-9]8($|[^0-9]).*марта.*";
//        String tags = ".*балезинский.*п.*балезино.*[^а-яА-Я]ул.*[^0-9]8($|[^0-9]){0,}.*[^а-яА-Я]{0,}марта.*";

//        String tags = ".*балезинский.*п.*балезино.*[^а-яА-Я]ул.*[^0-9]8($|[^0-9]).*[^а-яА-Я]{1,}марта.*";
//        String tags = ".*балезинский.*п.*балезино.*[^а-яА-Я]ул.*[^0-9]8([^0-9]|[^а-яА-Я])марта.*";


        assertEquals(true, test.toLowerCase().matches(tags));
    }


}
