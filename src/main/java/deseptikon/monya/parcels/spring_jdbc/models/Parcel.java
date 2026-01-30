package deseptikon.monya.parcels.spring_jdbc.models;

import java.util.List;

public class Parcel {

    private Integer id;

    private String cadastralNumber;

    private Float area;

    private String OKATO;

    private String OKTMO;

    private String locality;

    private String other;

    private String note;

    private String approvalDocumentName;

    private String category;

    private String utilizationLandUse;

    private String utilizationByDoc;

    private String utilizationPermittedUseText;

    private List<String> innerCadastralNumbers;

    private String usageCode;

    private String predicatedUsageCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public void setCadastralNumber(String cadastralNumber) {
        this.cadastralNumber = cadastralNumber;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getOKATO() {
        return OKATO;
    }

    public void setOKATO(String OKATO) {
        this.OKATO = OKATO;
    }

    public String getOKTMO() {
        return OKTMO;
    }

    public void setOKTMO(String OKTMO) {
        this.OKTMO = OKTMO;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getApprovalDocumentName() {
        return approvalDocumentName;
    }

    public void setApprovalDocumentName(String approvalDocumentName) {
        this.approvalDocumentName = approvalDocumentName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUtilizationLandUse() {
        return utilizationLandUse;
    }

    public void setUtilizationLandUse(String utilizationLandUse) {
        this.utilizationLandUse = utilizationLandUse;
    }

    public String getUtilizationByDoc() {
        return utilizationByDoc;
    }

    public void setUtilizationByDoc(String utilizationByDoc) {
        this.utilizationByDoc = utilizationByDoc;
    }

    public String getUtilizationPermittedUseText() {
        return utilizationPermittedUseText;
    }

    public void setUtilizationPermittedUseText(String utilizationPermittedUseText) {
        this.utilizationPermittedUseText = utilizationPermittedUseText;
    }

    public List<String> getInnerCadastralNumbers() {
        return innerCadastralNumbers;
    }

    public void setInnerCadastralNumbers(List<String> innerCadastralNumbers) {
        this.innerCadastralNumbers = innerCadastralNumbers;
    }

    public String getInnerCadastralNumbersString() {
        StringBuilder sb = new StringBuilder();
        for (String item : innerCadastralNumbers) {
            if (sb.isEmpty()) {
                sb.append(item);
            } else {
                sb.append("_x000D_").append(item);
            }
        }


        return String.valueOf(sb);
    }

    public String getUsageCode() {
        return usageCode;
    }

    public void setUsageCode(String usageCode) {
        this.usageCode = usageCode;
    }

    public String getPredictedUsageCode() {
        return predicatedUsageCode;
    }

    public void setPredictedUsageCode(String predicatedUsageCode) {
        this.predicatedUsageCode = predicatedUsageCode;
    }


}
