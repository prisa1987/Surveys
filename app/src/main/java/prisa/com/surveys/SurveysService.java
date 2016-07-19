package prisa.com.surveys;

import android.app.Application;
import android.util.Base64;
import android.util.Log;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;
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

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveysService extends SpiceService {

    Map retrofitInterfaceToServiceMap = new HashMap();
    Retrofit retrofit;

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager manager = new CacheManager();
        return manager;
    }

    public Retrofit.Builder createRestAdapterBuilder() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit.Builder rb = new Retrofit.Builder();
        rb.client(createHttpsClient()).baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create());
        return rb;
    }

    public Object getRetrofitService(Class tClass) {
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
        client.interceptors().add(new Interceptor() {
                                      @Override
                                      public Response intercept(Interceptor.Chain chain) throws IOException {
                                          com.squareup.okhttp.Request request = chain.request();
//                                          if(!seeksterPreference.getAccessToken().isEmpty()) {
//                                              request.newBuilder().header("Authorization", "Bearer "+seeksterPreference.getAccessToken()).build();
//                                              return chain.proceed(request);
//                                          } else {
//                                              return chain.proceed(request);
//                                          }
                                          String username = "9b5ddd4f-a1b5-4232-8da9-fb554024ddf5";
                                          String credentials = username + ":" + "TuzvXa/ZQqtA3PcI8SX0KOMCAR3Sg7U6zSud3HOeu6JBLkiOfw5fzasO+nAiZQQ+sBCG3Zt6DRBfLd2JdhVO4w==";
                                          String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                                          Request modifiedRequest = request.newBuilder().addHeader("Authorization", basic).build();
                                          return chain.proceed(modifiedRequest);
                                      }
                                  }
//
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
