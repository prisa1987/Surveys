package prisa.com.surveys.presenter;

import android.content.Context;

import com.octo.android.robospice.SpiceManager;

import java.util.List;

import de.greenrobot.event.Subscribe;
import io.realm.Realm;
import prisa.com.surveys.Response.GetAllSurveyResponse;
import prisa.com.surveys.executor.SurveyExecutor;
import prisa.com.surveys.model.Survey;
import prisa.com.surveys.viewAction.SurveyViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyPresenter extends BasePresenter implements SurveyViewAction {

    private SurveyViewAction viewAction;
    private List<Survey> surveys;

    public SurveyPresenter(Context context, SpiceManager spiceManager, SurveyViewAction viewAction) {
        super(context, spiceManager);
        this.viewAction = viewAction;
    }

    public void requestSurveys() {
        SurveyExecutor executor = new SurveyExecutor(Realm.getDefaultInstance(), getSpiceManager());
        surveys = executor.execute();
    }

    @Subscribe
    public void handle(GetAllSurveyResponse response) {
        surveys = (List<Survey>) response.surveys;
    }


}
