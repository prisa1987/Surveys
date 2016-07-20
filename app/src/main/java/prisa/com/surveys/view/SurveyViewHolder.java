package prisa.com.surveys.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import prisa.com.surveys.R;
import prisa.com.surveys.presenter.SurveyItemFragmentPresenter;
import prisa.com.surveys.viewAction.SurveyItemViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyViewHolder extends RecyclerView.ViewHolder implements SurveyItemViewAction {

    @BindView(R.id.ivSurvey) ImageView ivSurvey;
    @BindView(R.id.btSurvey) Button btSurvey;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvTitle) TextView tvTitle;

     SurveyItemFragmentPresenter surveyItemPresenter;

    SurveyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public SurveyItemFragmentPresenter getSurveyItemPresenter() {
        return surveyItemPresenter;
    }

    public void setSurveyItemPresenter(SurveyItemFragmentPresenter surveyItemPresenter) {
        this.surveyItemPresenter = surveyItemPresenter;
    }

    @Override
    public void setBackground(String url) {
        Picasso.with(itemView.getContext()).load(url).fit().into(ivSurvey);
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
