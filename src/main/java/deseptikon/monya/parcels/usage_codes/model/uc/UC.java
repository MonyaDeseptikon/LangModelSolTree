package deseptikon.monya.parcels.usage_codes.model.uc;

import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.LmstQuery;
import deseptikon.monya.parcels.usage_codes.auxiliary.PrepareTags;
import deseptikon.monya.parcels.usage_codes.model.Conditions;

import java.sql.SQLException;
import java.util.List;

public abstract class UC implements PrepareTags {

    LmstQuery queryTemplate;
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

    //Список кодов расчета вида использования ОКСов (привязанных r земельному участку), наличие которых обязательно для подтверждения предсказанного кода расчета вида использования земельного участка
    private List<String> usageCodeBuildingsMustBe;

    protected abstract void assignmentCode(LmstQuery queryTemplate) throws SQLException;

}
