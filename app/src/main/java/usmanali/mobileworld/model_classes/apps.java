package usmanali.mobileworld.model_classes;

/**
 * Created by SAJIDCOMPUTERS on 9/4/2017.
 */

public class apps {
    String app_name;
    String app_pic_url;

    public String getApp_pic_url() {
        return app_pic_url;
    }

    public void setApp_pic_url(String app_pic_url) {
        this.app_pic_url = app_pic_url;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getApp_url() {
        return app_url;
    }

    public void setApp_url(String app_url) {
        this.app_url = app_url;
    }

    String app_version;
    String app_url;
}
