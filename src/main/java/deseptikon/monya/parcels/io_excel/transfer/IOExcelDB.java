
package deseptikon.monya.parcels.io_excel.transfer;

import deseptikon.monya.parcels.db.FillDB;
import deseptikon.monya.parcels.io_excel.auxiliary.ServiceForExcel;
import deseptikon.monya.parcels.io_excel.mapper.FillRow;
import deseptikon.monya.parcels.spring_jdbc.models.InnerCN;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//import static deseptikon.monya.parce.ServiceParce.commaToDotCell;

public class IOExcelDB implements ServiceForExcel, FillRow, ParcelIOExcel, InnerCNIOExcel {


    public void readExcelFillDBParcelsProvisionalList(String filePath, int worksheetIndex) throws IOException, SQLException {
        FileInputStream is = new FileInputStream(filePath);
        FillDB fillDB = new FillDB();
        int cellIndexFinished = 14;

        ReadableWorkbook wb = new ReadableWorkbook(is);
        Optional<Sheet> sheet = wb.getSheet(worksheetIndex);
        List<Row> rowList = sheet.get().read();

        AtomicInteger i = new AtomicInteger();
        for (Row r : rowList) {
            i.getAndIncrement();
            if (i.get() > 1 && !Objects.equals(r.getCellText(0), "")) {

                fillDB.fillProvisionalList(r.getCellText(0), commaToDotCell(r.getCell(1)), r.getCellText(2), r.getCellText(3),
                        r.getCellText(4), r.getCellText(5), r.getCellText(6),
                        r.getCellText(7), r.getCellText(8), r.getCellText(9), r.getCellText(10),
                        r.getCellText(11), r.getCellText(12), r.getCellText(13));
                System.out.println(i.get() + " " + r.getCell(0));
            }
//                    if (i.get() == 65000) {
//                        throw new RuntimeException("Достигнута предельная строка");
//                    }

        }
    }


    public void readDBFillExcel(Worksheet ws, Set<Parcel> parcelList, List<String> colHeads) throws FileNotFoundException, SQLException {
        int row = 0;
        parcelsHeads(ws, row, colHeads);
        row++;
        Iterator<Parcel> parcelIterator = parcelList.iterator();
        while (parcelIterator.hasNext()) {
            Parcel parcel = parcelIterator.next();
            parcelsFill(ws, row, parcel);
            row++;
        }

    }

    @Override
    public List<InnerCN> excelToInnerCNTable(String filePath, int worksheetIndex) throws IOException, SQLException {
        FileInputStream is = new FileInputStream(filePath);

        ReadableWorkbook wb = new ReadableWorkbook(is);
        Optional<Sheet> sheet = wb.getSheet(worksheetIndex);
        final List<Row> rowList = sheet.get().read();
        //Удаление заголовка
        rowList.removeFirst();

        List<InnerCN> innerCNList = new ArrayList<>();
        for (Row row : rowList) {
            InnerCN innerCN = new InnerCN();
            innerCN.setCadastral_number(row.getCellText(0));
            innerCN.setBuilding_name(row.getCellText(1));
            innerCN.setArea(commaToDotCell(row.getCell(2)));
            innerCN.setNote(row.getCellText(3));
            innerCN.setUsage_code(row.getCellText(4));
            innerCN.setParcel_cadastral_numbers(row.getCellText(5));
            innerCNList.add(innerCN);
            System.out.println(innerCN);
        }
        return innerCNList;
    }
}


