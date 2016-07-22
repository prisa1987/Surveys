package prisa.com.surveys.mvp.executor;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

import de.greenrobot.event.EventBus;
import io.realm.Realm;
import prisa.com.surveys.OnEventSpiceError;
import prisa.com.surveys.mvp.model.Survey;
import prisa.com.surveys.request.GetAllSurveysRequest;
import prisa.com.surveys.response.GetAllSurveyResponse;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyExecutor extends Executable<List<Survey>> {

    private Realm realm;
    private SpiceManager spiceManager;
    private String accessToken;

    public SurveyExecutor(Realm realm, SpiceManager spiceManager, String accessToken) {
        this.realm = realm;
        this.spiceManager = spiceManager;
        this.accessToken = accessToken;
    }

    @Override
    List<Survey> runWithRealm() {
        return realm.where(Survey.class).findAll();
    }

    @Override
    void runWithSpiceManager() {
        SurveysListener listener = new SurveysListener();
        GetAllSurveysRequest request = new GetAllSurveysRequest(accessToken);
        spiceManager.execute(request, listener);
    }

    // ============================================================================================
    // INNER CLASSES
    // ============================================================================================

    final class SurveysListener implements RequestListener<GetAllSurveyResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
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
