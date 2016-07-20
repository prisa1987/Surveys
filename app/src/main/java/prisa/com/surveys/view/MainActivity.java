package prisa.com.surveys.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import prisa.com.surveys.R;
import prisa.com.surveys.presenter.SurveyPresenter;
import prisa.com.surveys.viewAction.SurveyViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class MainActivity extends BaseActivity implements SurveyViewAction {

//    @BindView(R.id.rvSurvey) RecyclerView rvSurvey;
    @BindView(R.id.vpSurvey) ViewPager vpSurvey;
    @BindView(R.id.ciSurvey)
    CirclePageIndicator ciSurvey;
    SurveyPresenter presenter;
    SurveyViewpagerAdapter viewPagerApdater;
//    SurveyViewAdapter adapter;

    @Override
    int getContentLayout() { return R.layout.activity_survey; }

    @Override
    void setUpUI() {
        presenter = new SurveyPresenter(this, spiceManager,this);
        presenter.requestSurveys();


//        adapter = new SurveyViewAdapter();
    }

//    public void setUpList() {
//        rvSurvey.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rvSurvey.setAdapter(adapter);
//    }

    @Override
    public void refreshData() {
        viewPagerApdater = new SurveyViewpagerAdapter(this.getSupportFragmentManager());
        vpSurvey.setAdapter(viewPagerApdater);
        ciSurvey.setViewPager(vpSurvey);
//        viewPagerApdater.registerDataSetObserver(ciSurvey.getDataSetObserver());
//        adapter.notifyDataSetChanged();
    }

    class SurveyViewpagerAdapter extends FragmentPagerAdapter {

        public SurveyViewpagerAdapter(FragmentManager fm) {
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

//    class SurveyViewAdapter extends RecyclerView.Adapter<SurveyViewHolder> {
//
//        @Override
//        public SurveyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = layoutInflater.inflate(R.layout.item_survey, parent, false);
//            return new SurveyViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(SurveyViewHolder holder, int position) {
//            holder.setSurveyItemPresenter(presenter.buildItemPrensenter(holder));
//            holder.getSurveyItemPresenter().survey = presenter.getSurveys().get(position);
//            holder.getSurveyItemPresenter().setItem();
//        }
//
//        @Override
//        public int getItemCount() {
//            return presenter.getSurveys().size();
//        }
//
//    }

}
