package prisa.com.surveys;

import android.util.Log;

import com.octo.android.robospice.request.SpiceRequest;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public abstract class BaseRequest<T, R> extends SpiceRequest<T> {

    Call<T> call;
    Class<R> api;
    Class<T> resultClass;
    public R service;

    public abstract Call<T> callApi();

    public BaseRequest(Class<T> resultClass, Class<R> api) {
        super(resultClass);
        this.resultClass = resultClass;
        this.api = api;

    }

    @Override
    public T loadDataFromNetwork() {
        try {
            call = callApi();
            Response response = call.execute();
            if (response.isSuccess()) {
                return (T) response.body();
            }
        } catch (IOException e) {
            Log.d("-->", "loadDataFromNetwork: "+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cancel() {
        call.cancel();
        super.cancel();
    }

    public void setRetrofitService(R retrofitService) {
        service = retrofitService;
    }

    public Class<R> getApi() {
        return api;
    }

    public void setApi(Class<R> api) {
        this.api = api;
    }

    public Class<T> getResultClass() {
        return resultClass;
    }

    public void setResultClass(Class<T> resultClass) {
        this.resultClass = resultClass;
    }

}
