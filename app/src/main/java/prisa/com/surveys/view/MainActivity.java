package prisa.com.surveys.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import prisa.com.surveys.R;
import prisa.com.surveys.presenter.SurveyPresenter;
import prisa.com.surveys.viewAction.SurveyViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class MainActivity extends BaseActivity implements SurveyViewAction {

    @BindView(R.id.vpSurvey) ViewPager vpSurvey;
    @BindView(R.id.ciSurvey) CirclePageIndicator ciSurvey;

    private SurveyPresenter presenter;
    private SurveyViewpagerAdapter viewPagerApdater;

    @Override
    int getContentLayout() { return R.layout.activity_survey; }

    @Override
    void setUpUI() {
        presenter = new SurveyPresenter(this, spiceManager,this);
        presenter.requestSurveys();
    }

    @Override
    public void refreshData() {
        viewPagerApdater = new SurveyViewpagerAdapter(this.getSupportFragmentManager());
        vpSurvey.setAdapter(viewPagerApdater);
        ciSurvey.setViewPager(vpSurvey);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    private class SurveyViewpagerAdapter extends FragmentPagerAdapter {

        SurveyViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return presenter.getFragments().get(position);
        }

        @Override
        public int getCount() {
            return presenter.getFragments().size();
        }
    }

}
