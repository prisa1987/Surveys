package prisa.com.surveys.presenter;

import android.content.Context;

import com.octo.android.robospice.SpiceManager;

import prisa.com.surveys.viewAction.MainViewAction;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class MainPresenter extends BasePresenter implements MainViewAction {

    MainViewAction viewAction;

    public MainPresenter(Context context, SpiceManager spiceManager, MainViewAction viewAction) {
        super(context, spiceManager);
        this.viewAction = viewAction;
    }

    public  void reqestSurveys() {

    }


}
