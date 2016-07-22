package prisa.com.surveys.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.OnClick;
import prisa.com.surveys.R;
import prisa.com.surveys.mvp.presenter.SurveyPresenter;
import prisa.com.surveys.mvp.viewAction.SurveyViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyActivity extends BaseActivity implements SurveyViewAction {

    @BindView(R.id.vpSurvey)  ViewPager vpSurvey;
    @BindView(R.id.ciSurvey)  CirclePageIndicator ciSurvey;
    @BindView(R.id.tbSurvey)  Toolbar tbSurvey;
    @BindView(R.id.tbTitle)  TextView tbTitle;

    private SurveyPresenter presenter;
    private SurveyViewpagerAdapter viewPagerAdapter;

    @Override
    int getContentLayout() {
        return R.layout.activity_survey;
    }

    @Override
    void setUp() {
        setupToolbar();
        presenter = new SurveyPresenter(this, spiceManager, this, getString(R.string.access_token));
        viewPagerAdapter = new SurveyViewpagerAdapter(this.getSupportFragmentManager());
        vpSurvey.setAdapter(viewPagerAdapter);
        ciSurvey.setViewPager(vpSurvey);
        presenter.requestSurveys();
    }

    //================================
    //Toolbar
    //================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.miHamburger: {
                //TODO: hamburger menu
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        tbSurvey.setTitle("");
        tbTitle.setText(R.string.tb_survey_title);
        setSupportActionBar(tbSurvey);
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

    @Override
    @OnClick(R.id.ivRefresh)
    public void refreshData() {
        viewPagerAdapter.notifyDataSetChanged();
    }

    //================================
    //Adapter
    //================================
    private class SurveyViewpagerAdapter extends FragmentPagerAdapter {

        SurveyViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return presenter.getItemFragments().get(position);
        }

        @Override
        public int getCount() {
            return presenter.getItemFragments().size();
        }

    }

}
