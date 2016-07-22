package prisa.com.surveys.mvp.view;

import android.content.Intent;
import android.widget.TextView;

import butterknife.BindView;
import prisa.com.surveys.R;

/**
 * Created by Admin on 7/21/2016 AD.
 */

public class SurveyDetailActivity extends BaseActivity {

    static String ARG_TITLE = "arg_title";
    static String ARG_DESCRIPTION = "arg_description";
    static String ARG_TYPE = "arg_type";

    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvType) TextView tvType;

    private String title;
    private String description;
    private String type;

    @Override
    int getContentLayout() {
        return R.layout.activity_survey_detail;
    }

    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);
        title = intent.getStringExtra(ARG_TITLE);
        description = intent.getStringExtra(ARG_DESCRIPTION);
        type = intent.getStringExtra(ARG_TYPE);
    }

    @Override
    void setUp() {
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvType.setText(type);
    }

}
