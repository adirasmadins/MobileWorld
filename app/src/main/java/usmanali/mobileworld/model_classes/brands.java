package usmanali.mobileworld.model_classes;

/**
 * Created by SAJIDCOMPUTERS on 9/5/2017.
 */

public class brands {


    String brandname;
    int brandimg;

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public int getBrandimg() {
        return brandimg;
    }

    public void setBrandimg(int brandimg) {
        this.brandimg = brandimg;
    }
   public brands(){}
    public brands(String brandname, int brandimg) {
        this.brandname = brandname;
        this.brandimg = brandimg;
    }
}
