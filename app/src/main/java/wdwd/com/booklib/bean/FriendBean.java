package wdwd.com.booklib.bean;

/**
 * Created by tomchen on 17/2/20.
 */

public class FriendBean {

    private String img;
    private String name;
    private String rest_time;
    private String place;

    public String getImg() {
        return img;
    }

    public FriendBean setImg(String img) {
        this.img = img;
        return this;
    }

    public String getName() {
        return name;
    }

    public FriendBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getRest_time() {
        return rest_time;
    }

    public FriendBean setRest_time(String rest_time) {
        this.rest_time = rest_time;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public FriendBean setPlace(String place) {
        this.place = place;
        return this;
    }
}
