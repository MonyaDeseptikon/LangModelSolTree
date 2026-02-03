package deseptikon.monya.parcels.io_excel.transfer;

import deseptikon.monya.parcels.io_excel.mapper.ExcelRowMapper;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import org.dhatim.fastexcel.reader.CellType;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IOExcelDBTest {

    @Test
    void readExcelFillDBParcelsProvisionalList() throws IOException {
        ExcelRowMapper excelRowMapper = new ExcelRowMapper();
        String fileExcel = "\\\\192.168.0.118\\каталог оценщиков\\ОЦЕНКА 2026\\Предварительный перечень\\Предварительный перечень ЗУ\\На загрузку в АИС_ЗУ\\1C_для обработки\\Алнашский_ОКС_ЗемУчасток_16.04.25092543.xlsx";
        int worksheetIndex = 0;
        List<Parcel> parcelList = new ArrayList<>();

        System.out.println(fileExcel);
        FileInputStream is = new FileInputStream(fileExcel);
        ReadableWorkbook wb = new ReadableWorkbook(is);
        Optional<Sheet> sheet = wb.getSheet(worksheetIndex);

        //Из списка удаляется заголовок и строки с пустым КН
        int cadastralNumberCell = 2;
        List<Row> rowListFull = sheet.get().read();
        rowListFull.removeFirst();
        List<Row> rowList = rowListFull.stream().filter(r -> r.hasCell(cadastralNumberCell)).toList();

//.filter(r -> r.getCell(areaCell).getType().equals(CellType.NUMBER))
        for (Row row : rowList) {
            parcelList.add(excelRowMapper.parcelsRow(row));
            System.out.println(parcelList.toString());
//            System.out.println(parcel);
        }


    }
}