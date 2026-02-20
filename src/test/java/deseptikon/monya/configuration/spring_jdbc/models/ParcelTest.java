package deseptikon.monya.configuration.spring_jdbc.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelTest {

    @Test
    void setKLADR() {
        String kladr = "1802500005000030013";
        System.out.println(kladr.length());
        kladr = kladr.substring(0,17);
        System.out.println(kladr.length());
        System.out.println(kladr);
    }
}