package deseptikon.monya.configuration.spring_jdbc.jdbc.parcel;

import deseptikon.monya.configuration.spring_jdbc.models.Parcel;

import java.util.List;

public interface GetParcelDAO {

    List<Parcel> getListParcels();

    List<Parcel> getListParcelsForReplaceLatin(List<String> columnsName);

    List<Parcel> getListParcelsByCode(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, Boolean isSearchInDistrict, Boolean isSearchInCity);

    List<Parcel> getListParcelsByTagsNewWhitCNInnerJoin(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, Boolean isSearchInDistrict, Boolean isSearchInCity,
                                                        List<String> usageCodeBuildingsMustBe, Float moreThisShareAreaBuildings, Float lessThisShareAreaBuildings);

    List<Parcel> getListParcelsByTagsNewWhitoutCN(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, Boolean isSearchInDistrict, Boolean isSearchInCity);

    List<Parcel> getListParcelsByTagsNew(StringBuilder tags, StringBuilder excludeTags, Float moreArea, Float lessArea, Boolean isSearchInDistrict, Boolean isSearchInCity);

    List<Parcel> getListParcelsByTagsKLADRNote(StringBuilder district, StringBuilder typeCity, StringBuilder city, StringBuilder typeLocality, StringBuilder locality, StringBuilder typeStreet, StringBuilder street);
    List<Parcel> getListParcelsView(String viewName);

}
