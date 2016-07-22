package prisa.com.surveys.mvp.presenter;

import prisa.com.surveys.mvp.model.Survey;
import prisa.com.surveys.mvp.view.SurveyItemFragment;
import prisa.com.surveys.mvp.viewAction.SurveyItemViewAction;

/**
 * Created by Admin on 7/20/2016 AD.
 */

public class SurveyItemFragmentPresenter {

    Survey survey;
    private SurveyItemViewAction viewAction;

    public SurveyItemFragmentPresenter(SurveyItemFragment itemFragment) {
        this.viewAction = itemFragment;
    }

    public void setItem() {
        viewAction.setBackground(survey.getCover_image_url());
        viewAction.setTitle(survey.getTitle());
        viewAction.setDescription(survey.getDescription());
        viewAction.setType(survey.getType());
    }

}
