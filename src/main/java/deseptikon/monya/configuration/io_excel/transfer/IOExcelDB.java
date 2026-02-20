
package deseptikon.monya.configuration.io_excel.transfer;

import deseptikon.monya.configuration.io_excel.auxiliary.ServiceForExcel;
import deseptikon.monya.configuration.io_excel.mapper.ExcelRowMapper;
import deseptikon.monya.configuration.io_excel.mapper.DBtoExcelRowMapper;
import deseptikon.monya.configuration.spring_jdbc.models.Building;
import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.*;

import java.io.*;

import java.sql.SQLException;
import java.util.*;


public class IOExcelDB implements ServiceForExcel, DBtoExcelRowMapper, ParcelIOExcel, BuildingsIOExcel, CodeKLADRIOExcel {
    ExcelRowMapper excelRowMapper = new ExcelRowMapper();


    public List<CodeKLADR> excelCodeKLADRToKLADR(String filePath, int worksheetIndex) throws IOException {
        File fileExcel = new File(filePath);
        if (!fileExcel.isFile())
            throw new RuntimeException("Переданный в метод excelConstructionsFileToInnerCNTable путь, не является файлом");
        List<CodeKLADR> codeKLADRList = new ArrayList<>();

        FileInputStream is = new FileInputStream(fileExcel);
        ReadableWorkbook wb = new ReadableWorkbook(is);
        Optional<Sheet> sheet = wb.getSheet(worksheetIndex);
        final List<Row> rowList = sheet.get().read();
        //Удаление заголовка
        rowList.removeFirst();

        for (Row row : rowList) {
            codeKLADRList.add(excelRowMapper.codeKLADRRow(row));
        }

        return codeKLADRList;
    }


    @Override
    public List<Parcel> excelParcelsDirectoryToProvisionalList(String directoryPath, int worksheetIndex, int cadastralNumberCell) throws IOException, SQLException {
        File directory = new File(directoryPath);
        if (!directory.isDirectory())
            throw new RuntimeException("Переданный в метод excelBuildingsDirectoryToInnerCNTable путь, не является папкой");
        List<Parcel> parcelList = new ArrayList<>();

        for (File fileExcel : Objects.requireNonNull(directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                return name.toLowerCase().contains(".xls");
            }
        }))) {
            System.out.println(fileExcel.toString());
            FileInputStream is = new FileInputStream(fileExcel);
            ReadableWorkbook wb = new ReadableWorkbook(is);
            Optional<Sheet> sheet = wb.getSheet(worksheetIndex);
            List<Row> rowListFull = sheet.get().read();

            //Из списка удаляется заголовок и строки с пустым КН
            rowListFull.removeFirst();
            List<Row> rowList = rowListFull.stream().filter(r -> r.hasCell(cadastralNumberCell)).toList();
            for (Row row : rowList) {
                parcelList.add(excelRowMapper.parcelsRow(row));
//                System.out.println(parcelList.toString());
            }
        }
        return parcelList;
    }


    public void readDBFillExcel(Worksheet ws, Set<Parcel> parcelList)  {
        int row = 0;
        parcelsHeads(ws, row);
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
        }
        return buildingList;
    }
}


