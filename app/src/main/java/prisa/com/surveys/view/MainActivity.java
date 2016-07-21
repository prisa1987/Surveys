package prisa.com.surveys.view;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import prisa.com.surveys.R;
import prisa.com.surveys.model.Survey;
import prisa.com.surveys.presenter.SurveyPresenter;
import prisa.com.surveys.view.BaseActivity;
import prisa.com.surveys.viewAction.SurveyViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class MainActivity extends BaseActivity implements SurveyViewAction {

    @BindView(R.id.ivSurvey) ImageView ivSurvey;
    @BindView(R.id.btSurvey) Button btSurvey;
    @BindView(R.id.tvDescription) TextView tvDescription;

    private SurveyPresenter presenter;

    @Override
    int getContentLayout() { return R.layout.activity_main; }

    @Override
    void setUpUI() {
        presenter = new SurveyPresenter(this, spiceManager,this);
        presenter.requestSurveys();
    }

}
