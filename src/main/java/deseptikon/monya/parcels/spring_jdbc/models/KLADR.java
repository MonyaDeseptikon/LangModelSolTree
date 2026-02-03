package deseptikon.monya.parcels.spring_jdbc.models;

import java.util.List;

public class KLADR {
    private Integer id;
    private String KLADR;
    private String typeDistrict;
    private String district;
    private String typeCity;
    private String city;
    private String typeLocality;
    private String locality;
    private String typeStreet;
    private String Street;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKLADR() {
        return KLADR;
    }

    public void setKLADR(String KLADR) {
        this.KLADR = KLADR;
    }

    public String getTypeDistrict() {
        return typeDistrict;
    }

    public void setTypeDistrict(String typeDistrict) {
        this.typeDistrict = typeDistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTypeCity() {
        return typeCity;
    }

    public void setTypeCity(String typeCity) {
        this.typeCity = typeCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTypeLocality() {
        return typeLocality;
    }

    public void setTypeLocality(String typeLocality) {
        this.typeLocality = typeLocality;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getTypeStreet() {
        return typeStreet;
    }

    public void setTypeStreet(String typeStreet) {
        this.typeStreet = typeStreet;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }
}
