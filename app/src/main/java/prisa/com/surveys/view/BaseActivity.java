package prisa.com.surveys.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.octo.android.robospice.SpiceManager;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import prisa.com.surveys.OnBackPressedEvent;
import prisa.com.surveys.SurveysService;

/**
 * Created by Admin on 7/19/2016 AD.
 */

abstract class BaseActivity extends AppCompatActivity {

    abstract int getContentLayout();
    abstract void setUpUI();
    final String REALM_DATABASE = "surveys.realm";

    private SpiceManager spiceManager = new SpiceManager(SurveysService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRealmConfiguration();
//        Realm.getDefaultInstance();
        setContentView(getContentLayout());
        ButterKnife.bind(this);
        handleIntent(getIntent());
        setUpUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        spiceManager.start(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        spiceManager.shouldStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new OnBackPressedEvent());
    }

    @Subscribe
    public void handle(Object event) {

    }

    public void handleIntent(Intent intent) {

    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

    public void setRealmConfiguration() {
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this).name(REALM_DATABASE).
                deleteRealmIfMigrationNeeded().build());
    }
}