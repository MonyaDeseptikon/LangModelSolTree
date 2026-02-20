package deseptikon.monya.auxiliary;

public interface ReplaceLatin {
     default String replaceLatinAndYoChar(String checkString) {
        String resultString;

        resultString = checkString;
        resultString = resultString.replace("'", " ");
        resultString = resultString.replace("C", "С");
        resultString = resultString.replace("c", "с");
        resultString = resultString.replace("E", "Е");
        resultString = resultString.replace("e", "е");
        resultString = resultString.replace("T", "Т");
        resultString = resultString.replace("O", "О");
        resultString = resultString.replace("o", "о");
        resultString = resultString.replace("p", "р");
        resultString = resultString.replace("P", "Р");
        resultString = resultString.replace("A", "А");
        resultString = resultString.replace("a", "а");
        resultString = resultString.replace("H", "Н");
        resultString = resultString.replace("K", "К");
        resultString = resultString.replace("k", "к");
        resultString = resultString.replace("X", "Х");
        resultString = resultString.replace("x", "х");
        resultString = resultString.replace("B", "В");
        resultString = resultString.replace("M", "М");
        resultString = resultString.replace("y", "у");
        resultString = resultString.replace("Y", "У");
        resultString = resultString.replace("Ё", "Е");
        resultString = resultString.replace("ё", "е");

        return resultString;
    }

     default boolean checkingLatin(String checkString) {
        return checkString.matches(".*[CcEeTOopPAaHKkXxBMYyЁё'].*");
    }
}
