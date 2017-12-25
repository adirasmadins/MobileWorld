package usmanali.mobileworld.model_classes;

/**
 * Created by SAJIDCOMPUTERS on 9/2/2017.
 */

public class comment {
    public String getNameofcommenter() {
        return nameofcommenter;
    }

    public void setNameofcommenter(String nameofcommenter) {
        this.nameofcommenter = nameofcommenter;
    }

    public String getUsernameofcommenter() {
        return usernameofcommenter;
    }

    public void setUsernameofcommenter(String usernameofcommenter) {
        this.usernameofcommenter = usernameofcommenter;
    }

    public String getComment_body() {
        return comment_body;
    }

    public void setComment_body(String comment_body) {
        this.comment_body = comment_body;
    }

    public String getPost_unique_id() {
        return post_unique_id;
    }

    public void setPost_unique_id(String post_unique_id) {
        this.post_unique_id = post_unique_id;
    }

    public String getTimeofcommennt() {
        return timeofcommennt;
    }

    public void setTimeofcommennt(String timeofcommennt) {
        this.timeofcommennt = timeofcommennt;
    }

    String nameofcommenter,usernameofcommenter,comment_body,post_unique_id,timeofcommennt;
}
