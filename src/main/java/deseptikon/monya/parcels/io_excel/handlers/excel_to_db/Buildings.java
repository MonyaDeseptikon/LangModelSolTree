package deseptikon.monya.parcels.io_excel.handlers.excel_to_db;

import deseptikon.monya.parcels.io_excel.transfer.IOExcelDB;
import deseptikon.monya.parcels.io_excel.transfer.BuildingsIOExcel;
import deseptikon.monya.parcels.spring_jdbc.jdbc.QueryBuilding;
import deseptikon.monya.parcels.spring_jdbc.models.Building;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Buildings {


    public static void main(String[] args) throws SQLException, IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        QueryBuilding queryBuilding = (QueryBuilding) context.getBean("dataSourceForJdbcTemplateBuilding");

        String innerCNTableName = "PARCEL_INNER_CN";

        BuildingsIOExcel buildingsIOExcel = new IOExcelDB();
        List<Building> buildingList = buildingsIOExcel.excelBuildingsDirectoryToInnerCNTable("\\\\Server20032\\каталог оценщиков\\ОЦЕНКА 2026\\Предварительный перечень\\Предварительный перечень ОКС\\Исходные\\1С_исходный\\Здания", 0);
        buildingList.addAll(buildingsIOExcel.excelConstructionsFileToInnerCNTable("\\\\Server20032\\каталог оценщиков\\ОЦЕНКА 2026\\Предварительный перечень\\Предварительный перечень ОКС\\Исходные\\1С_исходный\\Общий_ОКС_Сооружения_06.07.25143339.xlsx", 0));
//        for (Building b : buildingList) System.out.println(b);
        
        queryBuilding.insertInnerCN(buildingList, innerCNTableName);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 + " секунд");
    }


}
