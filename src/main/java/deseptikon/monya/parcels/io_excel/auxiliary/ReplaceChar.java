package deseptikon.monya.parcels.io_excel.auxiliary;

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
