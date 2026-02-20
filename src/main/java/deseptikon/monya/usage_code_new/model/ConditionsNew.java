package deseptikon.monya.usage_code_new.model;

import deseptikon.monya.auxiliary.prepare_tags.PrepareTagsUCNew;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConditionsNew implements PrepareTagsUCNew {

    private String usageCode;
    private String innerCNTableName;
    private List<String> excludeTags;

    private List<String> usageCodeBuildingsMustBe;
    private List<InternalConditions> conditionsNewList;

    private Float moreThisShareAreaBuildings;
    private Float lessThisShareAreaBuildings;


    private Set<Parcel> parcelListCheckedArea;

    //Без проверки ОКСов
    public void assignmentCode(LmstQuery queryTemplate) {
        Set<Parcel> parcelListAll = new HashSet<>();
        for (ConditionsNew.InternalConditions condition : conditionsNewList) {
            parcelListAll.addAll(queryTemplate.getListParcelsByTagsNew(queryTags(condition.getTags()), queryExcludeTags(this.getExcludeTags(), condition.getExcludeTagsInternal(), condition.getTags()),
                    condition.getMoreThisArea(), condition.getLessThisArea(), condition.isSearchInDistrict(), condition.isSearchInCity()));
        }
        parcelListAll.forEach(p->p.setPredictedUsageCode(usageCode));
        queryTemplate.concatParcelsPredictedUsageCode(parcelListAll.stream().toList());
//        System.out.println(parcelListAll.size());
    }

    //С проверкой ОКС и с учетом площади ОКС по отношению к площади ЗУ
    public void assignmentCodeInnerCNCheck(LmstQuery queryTemplate) {

        Set<Parcel> parcelListAll = new HashSet<>();
        for (ConditionsNew.InternalConditions condition : conditionsNewList) {
            parcelListAll.addAll(queryTemplate.getListParcelsByTagsNewWhitoutCN(queryTags(condition.getTags()), queryExcludeTags(this.getExcludeTags(), condition.getExcludeTagsInternal(), condition.getTags()),
                    condition.getMoreThisArea(), condition.getLessThisArea(), condition.isSearchInDistrict(), condition.isSearchInCity()));
            parcelListAll.addAll(queryTemplate.getListParcelsByTagsNewWhitCNInnerJoin(queryTags(condition.getTags()), queryExcludeTags(this.getExcludeTags(), condition.getExcludeTagsInternal(), condition.getTags()),
                    condition.getMoreThisArea(), condition.getLessThisArea(), condition.isSearchInDistrict(), condition.isSearchInCity(), usageCodeBuildingsMustBe, moreThisShareAreaBuildings, lessThisShareAreaBuildings));
        }
        parcelListAll.forEach(p->p.setPredictedUsageCode(usageCode));
        queryTemplate.concatParcelsPredictedUsageCode(parcelListAll.stream().toList());
//        System.out.println(parcelListAll.size());
    }

    public class InternalConditions {
        private List<String> tags;
        private List<String> excludeTagsInternal;
        private Float moreThisArea;
        private Float lessThisArea;
        boolean searchInDistrict;
        boolean searchInCity;
        String excludeOneTagInternal;

        public InternalConditions(List<String> tags, List<String> excludeTagsInternal, Float moreThisArea, Float lessThisArea, boolean searchInDistrict, boolean searchInCity) {
            this.tags = tags;
            this.excludeTagsInternal = excludeTagsInternal;
            this.moreThisArea = moreThisArea;
            this.lessThisArea = lessThisArea;
            this.searchInDistrict = searchInDistrict;
            this.searchInCity = searchInCity;
        }

        public InternalConditions(List<String> tags, String excludeOneTagInternal, Float moreThisArea, Float lessThisArea, boolean searchInDistrict, boolean searchInCity) {
            this.tags = tags;
            this.excludeOneTagInternal = excludeOneTagInternal;
            this.moreThisArea = moreThisArea;
            this.lessThisArea = lessThisArea;
            this.searchInDistrict = searchInDistrict;
            this.searchInCity = searchInCity;
        }



        public List<String> getExcludeTagsInternal() {
            return excludeTagsInternal;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {

            this.tags = tags;
        }

        public void setExcludeTagsInternal(List<String> excludeTagsInternal) {
            this.excludeTagsInternal = excludeTagsInternal;
        }

        public Float getMoreThisArea() {
            return moreThisArea;
        }

        public void setMoreThisArea(Float moreThisArea) {
            this.moreThisArea = moreThisArea;
        }

        public Float getLessThisArea() {
            return lessThisArea;
        }

        public void setLessThisArea(Float lessThisArea) {
            this.lessThisArea = lessThisArea;
        }

        public boolean isSearchInDistrict() {
            return searchInDistrict;
        }

        public void setSearchInDistrict(boolean searchInDistrict) {
            this.searchInDistrict = searchInDistrict;
        }

        public boolean isSearchInCity() {
            return searchInCity;
        }

        public void setSearchInCity(boolean searchInCity) {
            this.searchInCity = searchInCity;
        }
    }

    public Float getLessThisShareAreaBuildings() {
        return lessThisShareAreaBuildings;
    }

    public void setLessThisShareAreaBuildings(Float lessThisShareAreaBuildings) {
        this.lessThisShareAreaBuildings = lessThisShareAreaBuildings;
    }
    public String getUsageCode() {
        return usageCode;
    }

    public void setUsageCode(String usageCode) {
        this.usageCode = usageCode;
    }

    public String getInnerCNTableName() {
        return innerCNTableName;
    }

    public void setInnerCNTableName(String innerCNTableName) {
        this.innerCNTableName = innerCNTableName;
    }

    public List<String> getExcludeTags() {
        return excludeTags;
    }

    public void setExcludeTags(List<String> excludeTags) {
        this.excludeTags = excludeTags;
    }

    public List<String> getUsageCodeBuildingsMustBe() {
        return usageCodeBuildingsMustBe;
    }

    public void setUsageCodeBuildingsMustBe(List<String> usageCodeBuildingsMustBe) {
        this.usageCodeBuildingsMustBe = usageCodeBuildingsMustBe;
    }

    public List<InternalConditions> getConditionsNewList() {
        return conditionsNewList;
    }

    public void setConditionsNewList(List<InternalConditions> conditionsNewList) {
        this.conditionsNewList = conditionsNewList;
    }

    public Float getMoreThisShareAreaBuildings() {
        return moreThisShareAreaBuildings;
    }

    public void setMoreThisShareAreaBuildings(Float moreThisShareAreaBuildings) {
        this.moreThisShareAreaBuildings = moreThisShareAreaBuildings;
    }

    public Set<Parcel> getParcelListCheckedArea() {
        return parcelListCheckedArea;
    }

    public void setParcelListCheckedArea(Set<Parcel> parcelListCheckedArea) {
        this.parcelListCheckedArea = parcelListCheckedArea;
    }

}

