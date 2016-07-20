package prisa.com.surveys.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import prisa.com.surveys.R;
import prisa.com.surveys.presenter.SurveyItemFragmentPresenter;
import prisa.com.surveys.viewAction.SurveyItemViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyItemFragment extends BaseFragment implements SurveyItemViewAction {

    @BindView(R.id.ivSurvey) ImageView ivSurvey;
    @BindView(R.id.btSurvey) Button btSurvey;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvTitle) TextView tvTitle;

    SurveyItemFragmentPresenter presenter;

    public void setPresenter(SurveyItemFragmentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public int getContentLayout() {
        return R.layout.item_survey;
    }

    @Override
    public void setUpUI(View rootView) {
        presenter.setItem();
    }

    @Override
    public void setBackground(String url) {
        Picasso.with(rootView.getContext()).load(url + "l").fit().into(ivSurvey);
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        tvDescription.setText(description);
    }

}
