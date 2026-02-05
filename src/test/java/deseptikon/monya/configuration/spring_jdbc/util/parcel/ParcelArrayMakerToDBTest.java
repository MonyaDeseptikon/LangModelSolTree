package deseptikon.monya.configuration.spring_jdbc.util.parcel;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class ParcelArrayMakerToDBTest {

    @Test
    void excelParcelToDB() {
        File file = new File("checkLongCN");

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append("test").append('\n');
            writer.append("test1");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException( "Ошибка записа длинных КН в файл");

        }
    }
}