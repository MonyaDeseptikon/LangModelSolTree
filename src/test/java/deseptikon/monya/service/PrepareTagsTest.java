package deseptikon.monya.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrepareTagsTest {


    @Test
    void trimEnding() {
PrepareTags prepareTags = new PrepareTags() {
    @Override
    public String trimEnding(String tag) {
        return PrepareTags.super.trimEnding(tag);
    }
};
String toTrim = "сельскохозяйственный";
String trimmedString = prepareTags.trimEnding(toTrim);
        assertEquals( "сельскохозяйственн", trimmedString);
    }
}