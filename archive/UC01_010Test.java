//package deseptikon.monya.usage_code_new.hadlers;
//
//import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
//import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
//import deseptikon.monya.usage_code_new.model.ConditionsNew;
//
//import java.util.List;
//import java.util.Set;
//
//class UC01_010Test extends ConditionsNew {
//
//    public UC01_010Test() {
//        super(usageCode, innerCNTableName, excludeTags, usageCodeBuildingsMustBe, conditionsNewList, shareAreaBuildings, parcelListCheckedArea);
//
//    }
//
//    private static String usageCode = "01:010";
//    private  static String innerCNTableName = "BUILDINGS.PARCEL_INNER_CN";
//    private  static List<String> excludeTags = List.of("животноводство", "скотоводство", "садоводство", "звероводство", "птицеводство", "пчеловодство", "свиноводство", "рыбоводство", "индивидуальный", "отдых",
//            "жилой", "дачный", "овощеводство", "личный", "строительство");
//
//    private  static List<InternalConditions> conditionsNewList = List.of(
//            new InternalConditions(List.of("1.1"),
//                    List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
//            new InternalConditions(List.of("растениеводство"),
//                    List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
//            new InternalConditions(List.of("пашня"),
//                    List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
//            new InternalConditions(List.of("пастбище"),
//                    List.of(), 0F, Float.POSITIVE_INFINITY, true, true),
//            new InternalConditions(List.of("1.0"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
//            new InternalConditions(List.of("сельскохозяйственное", "использование"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
//            new InternalConditions(List.of("ведение", "сельского", "хозяйства"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
//            new InternalConditions(List.of("сельскохозяйственное", "производство"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
//            new InternalConditions(List.of("крестьянское", "хозяйство"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
//            new InternalConditions(List.of("фермерское", "хозяйство"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
//            new InternalConditions(List.of("сельскохозяйственное", "использование"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
//            new InternalConditions(List.of("подсобное", "сельское", "хозяйство"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
//            new InternalConditions(List.of("размещение", "сельхозугодий"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false),
//            new InternalConditions(List.of("сельхозпроизводство"),
//                    List.of(), 200000F, Float.POSITIVE_INFINITY, true, false)
//    );
//
//    private Float shareAreaBuildings = 0.05F;
//    private Set<Parcel> parcelListCheckedArea;
//
//
//    private List<String> usageCodeBuildingsMustBe = List.of(
//            //только для сельхоз
//            "0801", "0902", "0904", "0905", "0907",
//            //линейные объекты
//            "1010", "1011", "1012", "1013", "1014", "1015", "1016", "1017", "1020", "1021", "1027", "1030", "1031",
//            //только для сельхоз
//            "1009");
//
//
//
//
//}
//
//
//
//
//
//
