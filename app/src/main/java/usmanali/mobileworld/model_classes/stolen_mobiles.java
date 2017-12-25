package usmanali.mobileworld.model_classes;

/**
 * Created by SAJIDCOMPUTERS on 8/30/2017.
 */

public class stolen_mobiles {
    String personname;
    String stolen_date;
    String buyer_reciept;
    String imei;
    String mobile_name;

    public String getMobile_name() {
        return mobile_name;
    }

    public void setMobile_name(String mobile_name) {
        this.mobile_name = mobile_name;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getStolen_date() {
        return stolen_date;
    }

    public void setStolen_date(String stolen_date) {
        this.stolen_date = stolen_date;
    }

    public String getBuyer_reciept() {
        return buyer_reciept;
    }

    public void setBuyer_reciept(String buyer_reciept) {
        this.buyer_reciept = buyer_reciept;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
