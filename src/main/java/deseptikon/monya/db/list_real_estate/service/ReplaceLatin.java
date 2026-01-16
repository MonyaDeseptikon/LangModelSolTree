package deseptikon.monya.db.list_real_estate.service;




import deseptikon.monya.spring_jdbc_parcels.jdbc.QueryParcel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;



public class ReplaceLatin {



// не доделан - нет связи с БД

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring _config.xml");
        QueryParcel queryTemplate = (QueryParcel) context.getBean("dataSourceForJdbcTemplateParcelDaoImpl");

    }

    private static String replaceLatinChar(String checkString) {
        String resultString;

        if (checkString.matches(".*[CcEeTOopPAaHKkXxBMYy].*")) {
            resultString = checkString;
            resultString = resultString.replace("'", " ");
            resultString = resultString.replace("C", "С");
            resultString = resultString.replace("c", "с");
            resultString = resultString.replace("E", "Е");
            resultString = resultString.replace("e", "е");
            resultString = resultString.replace("T", "Т");
            resultString = resultString.replace("O", "О");
            resultString = resultString.replace("o", "о");
            resultString = resultString.replace("p", "р");
            resultString = resultString.replace("P", "Р");
            resultString = resultString.replace("A", "А");
            resultString = resultString.replace("a", "а");
            resultString = resultString.replace("H", "Н");
            resultString = resultString.replace("K", "К");
            resultString = resultString.replace("k", "к");
            resultString = resultString.replace("X", "Х");
            resultString = resultString.replace("x", "х");
            resultString = resultString.replace("B", "В");
            resultString = resultString.replace("M", "М");
            resultString = resultString.replace("y", "у");
            resultString = resultString.replace("Y", "У");
            return resultString;
        }

        return checkString;
    }

}
