package deseptikon.monya.io_excel.services;

public class ReplaceChar {
    public static String replaceChar(String checkString) {
        String resultString;

        if (checkString.matches(".*['].*")) {
            resultString = checkString;
            resultString = resultString.replace("'", " ");

            return resultString;
        }

        return checkString;
    }
}
