package wdwd.com.booklib.bean;

import io.reactivex.Observable;

/**
 * Created by tomchen on 17/1/23.
 */

public class SomeType {
    private String value ="";


    public SomeType setValue(String value) {
        this.value = value;
        return this;
    }

    public Observable<String> valueObservable(){
        return Observable.just(value);
    }
}
