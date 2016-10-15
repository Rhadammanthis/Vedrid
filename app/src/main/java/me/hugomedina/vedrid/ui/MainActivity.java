package me.hugomedina.vedrid.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import me.hugomedina.vedrid.R;
import me.hugomedina.vedrid.adapters.ForecastAdapter;
import me.hugomedina.vedrid.client.DataFetcher;
import me.hugomedina.vedrid.entities.Forecast;
import me.hugomedina.vedrid.interfaces.DataFetchedListener;

public class MainActivity extends Activity implements DataFetchedListener{

    private RecyclerView recyclerView;
    private TextView tvName, tvSummary, tvTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        DataFetcher dataFetcher = new DataFetcher("lol",this);
        dataFetcher.fetchData();

    }

    private void initComponents() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_forecast);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        tvName = (TextView) findViewById(R.id.tv_c_city);
        tvSummary = (TextView) findViewById(R.id.tv_c_summary);
        tvTemp = (TextView) findViewById(R.id.tv_c_temperature);
    }

    @Override
    public void onDataFetched(String data) {
        if(!data.isEmpty()) {
            Gson gson = new Gson();
            Forecast forecast = null;

            try {
                forecast = gson.fromJson(data, Forecast.class);
            }
            catch (JsonSyntaxException e){
                e.printStackTrace();
            }


//            Toast.makeText(MainActivity.this, String.valueOf(forecast.getDaily().getData().size()), Toast.LENGTH_SHORT).show();

            ForecastAdapter adapter = new ForecastAdapter(forecast.getDaily().getData());
            recyclerView.setAdapter(adapter);

            tvName.setText("Aguascalientes");
            tvSummary.setText(forecast.getCurrently().getSummary());
            tvTemp.setText(String.valueOf(forecast.getCurrently().getTemperature()));
        }

    }
}
