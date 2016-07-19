package prisa.com.surveys.model;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class Survey extends RealmObject {

    String id;
    String title;
    String cover_image_url;
    String type;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getCover_image_url() {
        return cover_image_url;
    }

}
