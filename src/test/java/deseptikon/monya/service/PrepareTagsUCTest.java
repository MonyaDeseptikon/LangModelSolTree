package deseptikon.monya.service;

import deseptikon.monya.auxiliary.prepare_tags.PrepareTagsUC;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrepareTagsUCTest {


    @Test
    void trimEnding() {
PrepareTagsUC prepareTagsUC = new PrepareTagsUC() {
    @Override
    public String trimEnding(String tag) {
        return PrepareTagsUC.super.trimEnding(tag);
    }
};
String toTrim = "сельскохозяйственный";
String trimmedString = prepareTagsUC.trimEnding(toTrim);
        assertEquals( "сельскохозяйственн", trimmedString);
    }
}