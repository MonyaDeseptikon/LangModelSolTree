package deseptikon.monya.parcels.spring_jdbc.models;

public class Building {
    Integer id;
    String cadastral_number;
    String building_name;
    Float area;
    String note;
    String usage_code;
    String parcel_cadastral_numbers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCadastral_number() {
        return cadastral_number;
    }

    public void setCadastral_number(String cadastral_number) {
        this.cadastral_number = cadastral_number;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUsage_code() {
        return usage_code;
    }

    public void setUsage_code(String usage_code) {
        this.usage_code = usage_code;
    }

    public String getParcel_cadastral_numbers() {
        return parcel_cadastral_numbers;
    }

    public void setParcel_cadastral_numbers(String parcel_cadastral_numbers) {
        this.parcel_cadastral_numbers = parcel_cadastral_numbers;
    }
}
