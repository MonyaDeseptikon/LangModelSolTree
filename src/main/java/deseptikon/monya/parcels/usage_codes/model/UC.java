package deseptikon.monya.parcels.usage_codes.model;

import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.QueryParcel;
import deseptikon.monya.parcels.usage_codes.auxiliary.PrepareTags;

import java.sql.SQLException;
import java.util.List;

public abstract class UC implements PrepareTags {
    //Исключаемые тэги
    private List<String> excludeTagsTemplate;

    //Поиск кода вида использования и условий
    //Точку внутри кода обязательно экранировать, - иначе воспринимает как любой символ

    private Conditions codeOnly;

    //Поиск тэгов и условий
    private List<Conditions> conditionsList;

    //Код расчета вида использования
    private String usageCode;

    //Ячейка с внутренним кадастровым номером пуста?
    private Boolean isEmptyInnerCN;

    //Имя таблицы с внутренними кадастровыми номерами. Возможно нужно будет добавить список с таблицами
    String innerCNTableName;

    protected abstract void assignmentCode(QueryParcel queryTemplate) throws SQLException;
}
