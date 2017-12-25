package usmanali.mobileworld.model_classes;

/**
 * Created by SAJIDCOMPUTERS on 6/10/2017.
 */

public class posts {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTimeinmillis() {
        return timeinmillis;
    }

    public void setTimeinmillis(String timeinmillis) {
        this.timeinmillis = timeinmillis;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    String name,username,post,timeinmillis;
    int post_id;


}
