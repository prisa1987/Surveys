package prisa.com.surveys.mvp.presenter;

import android.content.Context;

import com.octo.android.robospice.SpiceManager;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import io.realm.Realm;
import prisa.com.surveys.response.GetAllSurveyResponse;
import prisa.com.surveys.mvp.executor.SurveyExecutor;
import prisa.com.surveys.mvp.model.Survey;
import prisa.com.surveys.mvp.view.SurveyItemFragment;
import prisa.com.surveys.mvp.viewAction.SurveyViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyPresenter extends BasePresenter {

    private SurveyViewAction viewAction;
    private List<Survey> surveys;
    private List<SurveyItemFragment> itemFragments = new ArrayList<>();
    private String accessToken;

    public SurveyPresenter(Context context, SpiceManager spiceManager, SurveyViewAction viewAction, String accessToken) {
        super(context, spiceManager);
        this.viewAction = viewAction;
        this.accessToken = accessToken;
    }

    public void requestSurveys() {
        SurveyExecutor executor = new SurveyExecutor(Realm.getDefaultInstance(), getSpiceManager(), accessToken);
        surveys = executor.execute();
        buildFragmentList();
        viewAction.refreshData();
    }

    @Subscribe
    public void handle(GetAllSurveyResponse response) {
        surveys = (List<Survey>) response.surveys;
        buildFragmentList();
        viewAction.refreshData();
    }

    private SurveyItemFragmentPresenter buildItemPresenter(SurveyItemFragment itemFragment) {
        return new SurveyItemFragmentPresenter(itemFragment);
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    private void buildFragmentList() {
        for (Survey survey : surveys) {
            SurveyItemFragment fragment = new SurveyItemFragment();
            SurveyItemFragmentPresenter presenter = buildItemPresenter(fragment);
            fragment.setPresenter(presenter);
            presenter.survey = survey;
            itemFragments.add(fragment);
        }
    }

    public List<SurveyItemFragment> getItemFragments() {
        return itemFragments;
    }

}
