package me.hugomedina.vedrid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.hugomedina.vedrid.R;
import me.hugomedina.vedrid.entities.Daily;
import me.hugomedina.vedrid.entities.Datum;
import me.hugomedina.vedrid.entities.Forecast;

/**
 * Created by Hugo on 10/15/2016.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    public List<Datum> dataSet;
    String[] namesOfDays  = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

    public ForecastAdapter(List<Datum> dataSet)
    {
        this.dataSet = dataSet;
        //this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_list_item, parent, false);
        ForecastAdapter.ViewHolder vH = new ForecastAdapter.ViewHolder(view);

        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Log.d("Satan", String.valueOf(dataSet.get(position).getTime()));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dataSet.get(position).getTime());

        holder.day.setText(namesOfDays[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
        holder.calendarDay.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        holder.calendarMonth.setText(String.valueOf(calendar.get(Calendar.MONTH)));
        holder.summary.setText(dataSet.get(position).getSummary());
        holder.wind.setText(String.valueOf(dataSet.get(position).getWindSpeed()));
        holder.humidity.setText(String.valueOf(dataSet.get(position).getHumidity()));
        holder.precip.setText(String.valueOf(dataSet.get(position).getPrecipProbability()));
        holder.temp.setText(fToC(dataSet.get(position).getTemperatureMax()));

    }

    private String fToC(Double temp){
        Double res = (temp - 32) / 1.8;

        return String.valueOf(res.intValue());
    }

    @Override
    public int getItemCount() {
        if(dataSet.size() > 5)
            return 5;
        else
            return dataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView day, calendarDay, calendarMonth, summary, wind, humidity, precip, temp;

        public ViewHolder(View v) {
            super(v);
            day = (TextView) v.findViewById(R.id.tv_day);
            calendarDay = (TextView) v.findViewById(R.id.tv_calDay);
            calendarMonth = (TextView) v.findViewById(R.id.tv_calMonth);
            summary = (TextView) v.findViewById(R.id.tv_summary);
            wind = (TextView) v.findViewById(R.id.tv_wind);
            humidity = (TextView) v.findViewById(R.id.tv_humid);
            precip = (TextView) v.findViewById(R.id.tv_pres);
            temp = (TextView) v.findViewById(R.id.tv_temperature);

        }
    }

}
