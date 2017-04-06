package wdwd.com.booklib.bean;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import wdwd.com.booklib.BR;


/**
 * Created by tomchen on 17/2/20.
 */

public class FriendBean extends BaseObservable{

    private String img;
    private String name;
    private String rest_time;
    private String place;

    @Bindable
    public String getImg() {
        return img;
    }

    public FriendBean setImg(String img) {
        this.img = img;
        notifyPropertyChanged(BR.img);
        return this;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public FriendBean setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
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
