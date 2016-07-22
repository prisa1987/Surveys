package prisa.com.surveys.mvp.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import prisa.com.surveys.R;
import prisa.com.surveys.mvp.presenter.SurveyItemFragmentPresenter;
import prisa.com.surveys.mvp.viewAction.SurveyItemViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyItemFragment extends BaseFragment implements SurveyItemViewAction {

    @BindView(R.id.ivSurvey) ImageView ivSurvey;
    @BindView(R.id.btSurvey) Button btSurvey;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvTitle) TextView tvTitle;

    private SurveyItemFragmentPresenter presenter;
    private String title;
    private String type;
    private String description;

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
        this.title = title;
        tvTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
        tvDescription.setText(description);
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @OnClick(R.id.btSurvey)
    void setClick() {
        Intent intent = new Intent(getActivity(), SurveyDetailActivity.class);
        intent.putExtra(SurveyDetailActivity.ARG_TITLE, title);
        intent.putExtra(SurveyDetailActivity.ARG_DESCRIPTION, description);
        intent.putExtra(SurveyDetailActivity.ARG_TYPE, type);
        startActivity(intent);
    }

}
