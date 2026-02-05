package deseptikon.monya.configuration.io_excel.auxiliary;

import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.CellType;

public interface ServiceForExcel {

    default Float commaToDotCell(Cell cell) {
        Float areaCheck;
        if(cell == null){
            System.out.println("My message CELL is null");
        }
        if (cell.getType().equals(CellType.NUMBER)) {
            areaCheck = cell.asNumber().floatValue();
        } else {
            String cellNorm = cell.getText();
            if (cellNorm.contains(",")) {
                cellNorm = cellNorm.replace(',', '.');
            }
            if (cellNorm.isEmpty()) cellNorm = "0";
            areaCheck = Float.valueOf(cellNorm);
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
