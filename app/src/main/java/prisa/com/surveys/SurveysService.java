package prisa.com.surveys;

import android.app.Application;
import android.util.Base64;
import android.util.Log;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import prisa.com.surveys.Request.BaseRequest;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveysService extends SpiceService {

    Map retrofitInterfaceToServiceMap = new HashMap();
    Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            retrofit = createRestAdapterBuilder().build();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager manager = new CacheManager();
        return manager;
    }

    public Retrofit.Builder createRestAdapterBuilder() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        Retrofit.Builder rb = new Retrofit.Builder();
        rb.client(createHttpsClient()).baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(SurveyGsonBuilder.getGsonBuilder().create()));
        return rb;
    }

    private Object getRetrofitService(Class tClass) {
        retrofitInterfaceToServiceMap.put(tClass, retrofit.create(tClass));
        return retrofit.create(tClass);
    }

    @Override
    public void addRequest(CachedSpiceRequest<?> request, Set<RequestListener<?>> listRequestListener) {

        BaseRequest baseRequest = (BaseRequest) request.getSpiceRequest();
        baseRequest.setRetrofitService(getRetrofitService(baseRequest.getApi()));
        super.addRequest(request, listRequestListener);
    }

    private OkHttpClient createHttpsClient() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.MINUTES);
        client.setReadTimeout(5, TimeUnit.MINUTES);
        client.interceptors().add(new Interceptor() {
                                      @Override
                                      public Response intercept(Interceptor.Chain chain) throws IOException {
                                          com.squareup.okhttp.Request request = chain.request();
                                          String userName = "usay";
                                          String password = "isc00l";
                                          com.squareup.okhttp.Request modifileRequest = request.newBuilder()
                                                  .addHeader("Authorization", Credentials.basic(userName, password))
                                                  .build();
                                          return chain.proceed(modifileRequest);
                                      }
                                  }
        );
        client.interceptors().add(new LoggingInterceptor());

        return client;
    }

    class LoggingInterceptor implements Interceptor {
        String TAG = "API";

        @Override
        public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.d(TAG, "Request --> " + request.urlString() + " Headers: " + request.headers());

            Request copiedRequest = request.newBuilder().build();
            if (copiedRequest.body() != null) {
                okio.Buffer buffer = new okio.Buffer();
                copiedRequest.body().writeTo(buffer);
            }

            com.squareup.okhttp.Response response = chain.proceed(request);
            ResponseBody responseBody = response.body();
            String responseBodyString = responseBody.string();
            Log.d(TAG, "Response Body<-- " + responseBodyString);

            return response.newBuilder().body(ResponseBody.create(responseBody.contentType(), responseBodyString.getBytes())).build();
        }
    }

}
