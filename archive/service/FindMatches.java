package deseptikon.monya.service;

public class FindMatches {
    public static Integer matchCodePosition(String checkingString, String groupCode) {
        if (checkingString.matches(".*" + groupCode + ".*")) {
            if (checkingString.matches(".*[0-9]" + groupCode + "[0-9].*")) return -1;
            else if (checkingString.matches(".*[0-9]\\." + groupCode + "\\.[0-9].*")) return -1;
            else if (checkingString.matches(".*[0-9]" + groupCode + ".*")) return -1;
            else if (checkingString.matches(".*" + groupCode + "[0-9].*")) return -1;
            else if (checkingString.matches(".*" + groupCode + "\\.[0-9].*")) return -1;
            else if (checkingString.matches(".*[0-9]\\." + groupCode + ".*")) return -1;
            else if (checkingString.matches(".*[жЖ].?" + groupCode + ".*")) return -1;

            else {
                return checkingString.indexOf(groupCode);
            }
        } else if (checkingString.equals(groupCode)) return 1;
        return -1;
    }

    public static Integer matchTagPositionConsecutive(String checkingString, String tag, int pos) {

        if (checkingString.matches(".*" + tag + ".*")) {
            return checkingString.indexOf(tag, pos);
        } else if (checkingString.equals(tag)) return 1;
        return -1;
    }

    public static Integer matchTagPositionArbitrary (String checkingString, String tag) {

        if (checkingString.matches(".*" + tag + ".*")) {
            return checkingString.indexOf(tag);
        } else if (checkingString.equals(tag)) return 1;
        return -1;
    }
}
