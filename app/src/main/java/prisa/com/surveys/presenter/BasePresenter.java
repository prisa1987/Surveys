package prisa.com.surveys.presenter;

import android.content.Context;

import com.octo.android.robospice.SpiceManager;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import io.realm.Realm;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public abstract class BasePresenter {

    Context context;
    SpiceManager spiceManager;
    Realm realm;

    public BasePresenter(Context context, SpiceManager spiceManager) {
        this.realm = Realm.getDefaultInstance();
        this.context = context;
        this.spiceManager = spiceManager;
    }

    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    public void onResume() {

    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void handle(Object event) {

    }

    public Realm getRealm() {
        return realm;
    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

}
