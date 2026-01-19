package deseptikon.monya.parcels.io_excel.auxiliary;

import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.CellType;

import java.math.BigDecimal;

public interface ServiceForExcel {

    default Float commaToDotCell(Cell cell){
        BigDecimal areaCheck;
        if( cell.getType().equals(CellType.NUMBER)){
            areaCheck = cell.asNumber();
        } else {
//            System.out.println(i.get());
//            System.out.println(r.getCell(1).asString());
            String cellNorm =  cell.getText();
            if (cellNorm.contains(",")){
                cellNorm= cellNorm.replace(',', '.');
            }
            if (cellNorm.isEmpty()) cellNorm="0";
            areaCheck = new BigDecimal(cellNorm);
        }
        return areaCheck;
    }

     default String replaceChar(String checkString) {
        String resultString;

        if (checkString.matches(".*['].*")) {
            resultString = checkString;
            resultString = resultString.replace("'", " ");

            return resultString;
        }

        return checkString;
    }
}
