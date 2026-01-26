
package deseptikon.monya.parcels.io_excel.transfer;

import deseptikon.monya.parcels.db.FillDB;
import deseptikon.monya.parcels.io_excel.auxiliary.ServiceForExcel;
import deseptikon.monya.parcels.io_excel.mapper.FillRow;
import deseptikon.monya.parcels.spring_jdbc.models.Building;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.*;

import java.io.*;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;



public class IOExcelDB implements ServiceForExcel, FillRow, ParcelIOExcel, BuildingsIOExcel {


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
    public List<Building> excelBuildingsDirectoryToInnerCNTable(String directoryPath, int worksheetIndex) throws IOException, SQLException {
        File directory = new File(directoryPath);
        if (!directory.isDirectory())
            throw new RuntimeException("Переданный в метод excelBuildingsDirectoryToInnerCNTable путь, не является папкой");
        List<Building> buildingList = new ArrayList<>();

        for (File fileExcel : Objects.requireNonNull(directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {

                    return name.toLowerCase().contains(".xls");

            }
        }))) {

            FileInputStream is = new FileInputStream(fileExcel);
            ReadableWorkbook wb = new ReadableWorkbook(is);
            Optional<Sheet> sheet = wb.getSheet(worksheetIndex);
            final List<Row> rowList = sheet.get().read();
            //Удаление заголовка
            rowList.removeFirst();

            for (Row row : rowList) {
                Building building = new Building();
                building.setCadastral_number(row.getCellText(2));
                building.setObject_type(row.getCellText(6));
                building.setObject_name(row.getCellText(5));
                building.setObject_assignation(row.getCellText(7));
                building.setObject_permitted_uses(row.getCellText(200));
                building.setOKATO(row.getCellText(14));
                building.setOKTMO(row.getCellText(15));
                building.setArea(commaToDotCell(row.getCell(12)));
                building.setNote(row.getCellText(44));
                building.setUsage_code(row.getCellText(67));
                building.setParcel_cadastral_numbers(row.getCellText(202));
                buildingList.add(building);
//            System.out.println(building);
            }
        }
        return buildingList;
    }

    @Override
    public List<Building> excelConstructionsFileToInnerCNTable(String filePath, int worksheetIndex) throws IOException, SQLException {
        File fileExcel = new File(filePath);
        if (!fileExcel.isFile())
            throw new RuntimeException("Переданный в метод excelConstructionsFileToInnerCNTable путь, не является файлом");
        List<Building> buildingList = new ArrayList<>();

            FileInputStream is = new FileInputStream(fileExcel);
            ReadableWorkbook wb = new ReadableWorkbook(is);
            Optional<Sheet> sheet = wb.getSheet(worksheetIndex);
            final List<Row> rowList = sheet.get().read();
            //Удаление заголовка
            rowList.removeFirst();

            for (Row row : rowList) {
                Building building = new Building();
                building.setCadastral_number(row.getCellText(2));
                building.setObject_type(row.getCellText(6));
                building.setObject_name(row.getCellText(5));
                building.setObject_assignation(row.getCellText(7));
                building.setObject_permitted_uses(row.getCellText(189));
                building.setOKATO(row.getCellText(13));
                building.setOKTMO(row.getCellText(14));
                building.setArea(commaToDotCell(row.getCell(201)));
                building.setNote(row.getCellText(41));
                building.setUsage_code(row.getCellText(59));
                building.setParcel_cadastral_numbers(row.getCellText(191));
                buildingList.add(building);
//            System.out.println(building);
            }

        return buildingList;
    }
}


