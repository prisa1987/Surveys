package prisa.com.surveys.executor;

import android.util.Log;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

import de.greenrobot.event.EventBus;
import io.realm.Realm;
import prisa.com.surveys.Response.GetAllSurveyResponse;
import prisa.com.surveys.model.Survey;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class MainExecutor extends Executable<List<Survey>> {

    Realm realm;

    @Override
    List<Survey> runWithRealm() {
        return realm.where(Survey.class).findAll();
    }

    @Override
    void runWithSpiceManager() {
        //        CategoryListener listener = new CategoryListener();
//        GetAllCategoryRequest request = new GetAllCategoryRequest();
//        spiceManager.execute(request, listener);
    }

    // ============================================================================================
    // INNER CLASSES
    // ============================================================================================

    public final class SurveyListListener implements RequestListener<GetAllSurveyResponse> {

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
