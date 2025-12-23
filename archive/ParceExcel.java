
package deseptikon.monya.parce;

import deseptikon.monya.db.FillDB;
import org.apache.commons.lang3.time.StopWatch;
import org.dhatim.fastexcel.reader.*;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static deseptikon.monya.parce.Service.castToBD;

public class ParceExcel {

    public static void main(String[] args) throws IOException, SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ParceExcel test = new ParceExcel();
        test.IO();

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken/1000 + " секунд");
    }



    //    FileInputStream is = new FileInputStream("E:\\ЦКО БТИ УР\\1C\\Алнашский_ОКС_ЗемУчасток_16.04.25092543_Абрамова_ОМС.xlsx");
    FileInputStream is = new FileInputStream("E:\\ЦКО БТИ УР\\Языковая модель\\ЗУ 2022 allUseLogicTree.xlsm");
    FillDB fillDB = new FillDB();

    public ParceExcel() throws IOException, SQLException {

    }

    public void IO() {
        int cellIndexFinished = 14;
        try {
            ReadableWorkbook wb = new ReadableWorkbook(is);
            Optional<Sheet> sheet = wb.getSheet(1);
            List<Row> rowList = sheet.get().read();

                AtomicInteger i = new AtomicInteger();
                for(Row r: rowList)  {
                    i.getAndIncrement();
                    if (i.get() > 1 && !Objects.equals(r.getCellText(0), "")) {

                            fillDB.fillRow(r.getCellText(0), castToBD(r.getCell(1)), r.getCellText(2), r.getCellText(3),
                                    r.getCellText(4), r.getCellText(5), r.getCellText(6),
                                    r.getCellText(7), r.getCellText(8), r.getCellText(9), r.getCellText(10),
                                    r.getCellText(11), r.getCellText(12), r.getCellText(13));
                            System.out.println(i.get() + " "+ r.getCell(0));
                    }
//                    if (i.get() == 65000) {
//                        throw new RuntimeException("Достигнута предельная строка");
//                    }

                }






//            try (Stream<Row> rows = sheet.get().openStream()) {
//                AtomicInteger i = new AtomicInteger();
//
//                rows.forEach(r -> {
//                    i.getAndIncrement();
////                    for (Cell cell : r.getCellTexts(0,cellIndexFinished)) {
////                        System.out.print(cell);
////                    }
////                    System.out.print('\n');
////                    System.out.println(r.toString());
////|| r.getCell(1) !=null
//                    if (i.get() > 1 & r.hasCell(0)) {
//                        try {
//
//                            fillDB.fillRow(r.getCellText(0), castToBD(r.getCell(1)), r.getCellText(2), r.getCellText(3),
//                                    r.getCellText(4), r.getCellText(5), r.getCellText(6),
//                                    r.getCellText(7), r.getCellText(8), r.getCellText(9), r.getCellText(10),
//                                    r.getCellText(11), r.getCellText(12), r.getCellText(13));
//                            System.out.println(i.get() + " "+ r.getOptionalCell(0));
//                        } catch (SQLException e) {
//                            throw new RuntimeException(e + String.valueOf(i.get()));
//                        }
//                    }
//
////                    System.out.println(i.get());
////                    if (i.get() == 65000) {
////                        throw new RuntimeException("Достигнута предельная строка");
////                    }
//
//                });
//
//            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

