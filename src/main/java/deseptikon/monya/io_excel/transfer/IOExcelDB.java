
package deseptikon.monya.io_excel.transfer;

import deseptikon.monya.db.list_real_estate.FillDB;
import deseptikon.monya.io_excel.services.ServiceForExcel;
import deseptikon.monya.io_excel.mapper.FillRow;
import deseptikon.monya.spring_jdbc_parcels.model.Parcel;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//import static deseptikon.monya.parce.ServiceParce.commaToDotCell;

public class IOExcelDB implements ServiceForExcel, ExcelDAO, FillRow {


    public void readExcelFillDBParcelsProvisionalList(String filePath, int worksheetIndex) throws FileNotFoundException, SQLException {
        FileInputStream is = new FileInputStream(filePath);
        FillDB fillDB = new FillDB();
        int cellIndexFinished = 14;

        try {
            ReadableWorkbook wb = new ReadableWorkbook(is);
            Optional<Sheet> sheet = wb.getSheet(worksheetIndex);
            List<Row> rowList = sheet.get().read();

            AtomicInteger i = new AtomicInteger();
            for (Row r : rowList) {
                i.getAndIncrement();
                if (i.get() > 1 && !Objects.equals(r.getCellText(0), "")) {

                    fillDB.fillProvisionalList(r.getCellText(0), ServiceForExcel.commaToDotCell(r.getCell(1)), r.getCellText(2), r.getCellText(3),
                            r.getCellText(4), r.getCellText(5), r.getCellText(6),
                            r.getCellText(7), r.getCellText(8), r.getCellText(9), r.getCellText(10),
                            r.getCellText(11), r.getCellText(12), r.getCellText(13));
                    System.out.println(i.get() + " " + r.getCell(0));
                }
//                    if (i.get() == 65000) {
//                        throw new RuntimeException("Достигнута предельная строка");
//                    }

            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readDBFillExcel(Worksheet ws, Set<Parcel> parcelList) throws FileNotFoundException, SQLException {
        int row = 0;
        Iterator<Parcel> parcelIterator = parcelList.iterator();
        while (parcelIterator.hasNext()) {
            Parcel parcel = parcelIterator.next();
            parcelsFull(ws, row, parcel);

            row++;
        }

    }


}

