package prisa.com.surveys.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octo.android.robospice.SpiceManager;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import prisa.com.surveys.OnBackPressedEvent;
import prisa.com.surveys.SurveysService;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public abstract class BaseFragment extends Fragment {

    public View rootView;
    private SpiceManager spiceManager = new SpiceManager(SurveysService.class);

    public abstract void setUpUI(View rootView);
    public abstract int getContentLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getContentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        setUpUI(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        spiceManager.start(getActivity());
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        spiceManager.shouldStop();
        EventBus.getDefault().unregister(this);
    }

    public void onBackPressed() {
        EventBus.getDefault().post(new OnBackPressedEvent());
    }

    @Subscribe
    public void handle(Object event) {

    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

}
