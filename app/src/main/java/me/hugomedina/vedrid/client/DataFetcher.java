package me.hugomedina.vedrid.client;


import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.Callable;

import me.hugomedina.vedrid.interfaces.DataFetchedListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hugo on 10/15/2016.
 */
public class DataFetcher {

    private String query;
    protected String fixedQuery = "https://api.darksky.net/forecast/30c567dc4bb37689a2d93495b3db53f4/21.861636,-102.270077?exclude=[minutely,hourly,alerts,flags]";
    private DataFetchedListener dataFetchedListener;

    public DataFetcher(String query, DataFetchedListener dataFetchedListener)
    {
        this.query = query;
        this.dataFetchedListener = dataFetchedListener;
    }

    public void fetchData(){
        Observable<String> tvShowObservable = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws IOException {
                return queryCall();
            }
        });

        Subscription sub = tvShowObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onCompleted() {
//                                Toast.makeText(this.A, "Compete", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
//                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(String forecast) {
//                                Toast.makeText(MainActivity.this, "Next", Toast.LENGTH_SHORT).show();
                                dataFetchedListener.onDataFetched(forecast);
                            }
                        });
    }

    public String queryCall(){

        String pageInfo = null;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(fixedQuery)
                .get()
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            pageInfo = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(pageInfo != null)
            return pageInfo;
        else
            return "";

    }

}
