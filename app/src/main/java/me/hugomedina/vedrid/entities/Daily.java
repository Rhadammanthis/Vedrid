
package me.hugomedina.vedrid.entities;


import java.util.ArrayList;
import java.util.List;

public class Daily {

    public String summary;
    public String icon;
    public List<Datum> data = new ArrayList<Datum>();

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
