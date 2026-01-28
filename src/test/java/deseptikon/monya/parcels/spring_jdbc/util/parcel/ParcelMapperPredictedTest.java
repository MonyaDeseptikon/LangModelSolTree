package deseptikon.monya.parcels.spring_jdbc.util.parcel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParcelMapperPredictedTest {

    @Test
    void mapRow() {
        String test = "18:25:084001:397";
        String test2 = "18:25:084001:397_x000D_" +
                "18:25:084001:398_x000D_" +
                "18:25:084001:516";
        List<String> testListFromString = List.of(test.split("_x000D_"));
        List<String> testListFromString2 = List.of(test2.split("_x000D_"));

        List<String> testList = List.of("18:25:084001:397");
        List<String> testList2 = List.of("18:25:084001:397", "18:25:084001:398", "18:25:084001:516");

        assertEquals(testList2, testListFromString2);
        assertEquals(testList, testListFromString);
    }
}