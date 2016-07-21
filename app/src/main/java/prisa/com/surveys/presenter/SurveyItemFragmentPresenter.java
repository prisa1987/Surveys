package prisa.com.surveys.presenter;

import prisa.com.surveys.model.Survey;
import prisa.com.surveys.view.SurveyItemFragment;
import prisa.com.surveys.viewAction.SurveyItemViewAction;

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
    }

}
