package deseptikon.monya.parcels.usage_codes.model;

import deseptikon.monya.parcels.db.create_tables.CreateProvisionalList;
import deseptikon.monya.parcels.spring_jdbc.jdbc.parcel.QueryParcel;
import deseptikon.monya.parcels.spring_jdbc.models.Parcel;
import deseptikon.monya.parcels.usage_codes.auxiliary.PrepareTags;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class UC implements PrepareTags {
    //Исключаемые тэги
    protected List<String> excludeTagsTemplate = List.of();


    //Поиск кода вида использования и условий
    //Точку внутри кода обязательно экранировать, - иначе воспринимает как любой символ

    protected Conditions codeOnly = new Conditions();

    //Поиск тэгов и условий
    protected List<Conditions> conditionsList = List.of();

    String usageCode;

    protected abstract void assignmentCode(QueryParcel queryTemplate) throws SQLException;
}
