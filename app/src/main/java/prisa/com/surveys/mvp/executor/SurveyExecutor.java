package prisa.com.surveys.mvp.executor;

import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

import de.greenrobot.event.EventBus;
import io.realm.Realm;
import prisa.com.surveys.OnEventSpiceError;
import prisa.com.surveys.request.GetAllSurveysRequest;
import prisa.com.surveys.response.GetAllSurveyResponse;
import prisa.com.surveys.mvp.model.Survey;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyExecutor extends Executable<List<Survey>> {

    private Realm realm;
    private SpiceManager spiceManager;

    public SurveyExecutor(Realm realm, SpiceManager spiceManager) {
        this.realm = realm;
        this.spiceManager = spiceManager;
    }

    @Override
    List<Survey> runWithRealm() {
        return realm.where(Survey.class).findAll();
    }

    @Override
    void runWithSpiceManager() {
        SurveysListener listener = new SurveysListener();
        String accessToken = "6eebeac3dd1dc9c97a06985b6480471211a777b39aa4d0e03747ce6acc4a3369";
        GetAllSurveysRequest request = new GetAllSurveysRequest(accessToken);
        spiceManager.execute(request, listener);
    }

    // ============================================================================================
    // INNER CLASSES
    // ============================================================================================

    final class SurveysListener implements RequestListener<GetAllSurveyResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Log.d("-->", spiceException.getMessage());
            EventBus.getDefault().post(new OnEventSpiceError(spiceException));
        }

        @Override
        public void onRequestSuccess(final GetAllSurveyResponse response) {
            if (response != null) {
                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(response.surveys);
                    }
                });
                EventBus.getDefault().post(response);
            }
        }
    }

}
