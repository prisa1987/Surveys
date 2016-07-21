package prisa.com.surveys.presenter;

import android.content.Context;

import com.octo.android.robospice.SpiceManager;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import io.realm.Realm;
import prisa.com.surveys.Response.GetAllSurveyResponse;
import prisa.com.surveys.executor.SurveyExecutor;
import prisa.com.surveys.model.Survey;
import prisa.com.surveys.view.SurveyItemFragment;
import prisa.com.surveys.viewAction.SurveyViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyPresenter extends BasePresenter {

    private SurveyViewAction viewAction;
    private List<Survey> surveys;
    List<SurveyItemFragment> itemFragments = new ArrayList<>();

    public SurveyPresenter(Context context, SpiceManager spiceManager, SurveyViewAction viewAction) {
        super(context, spiceManager);
        this.viewAction = viewAction;
    }

    public void requestSurveys() {
        SurveyExecutor executor = new SurveyExecutor(Realm.getDefaultInstance(), getSpiceManager());
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

    SurveyItemFragmentPresenter buildItemPresenter(SurveyItemFragment itemFragment) {
        return new SurveyItemFragmentPresenter(itemFragment);
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    void buildFragmentList() {
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
