package deseptikon.monya.configuration.spring_jdbc.models;

public class Building {
    Integer id;
    String cadastral_number;
    String object_type;
    String object_name;
    String object_assignation;
    String object_permitted_uses;
    String OKATO;
    String OKTMO;
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

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
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

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public String getObject_assignation() {
        return object_assignation;
    }

    public void setObject_assignation(String object_assignation) {
        this.object_assignation = object_assignation;
    }

    public String getObject_permitted_uses() {
        return object_permitted_uses;
    }

    public void setObject_permitted_uses(String object_permitted_uses) {
        this.object_permitted_uses = object_permitted_uses;
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




}

