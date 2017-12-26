package com.clkasd.trieandroidexample.Util;

import android.app.Activity;
import android.os.AsyncTask;

import com.clkasd.trieandroidexample.Model.City;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by aykutcelik on 21.12.2017.
 */

public class CitiesFactory {

    /**
     * @param activity
     * @return json string which is read from file.
     */
    private String loadJSONFromAsset(Activity activity) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open("cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void getCities(Activity activity) {
        new ReadJsonFileTask().execute(activity);
    }

    /**
     * ReadJsonFileTask is implemented as AsyncTask because it is too large.
     */
    private class ReadJsonFileTask extends AsyncTask<Activity, Void, List<City>> {
        @Override
        protected List<City> doInBackground(Activity... activities) {
            Activity activity = activities[0];
            String json = loadJSONFromAsset(activity);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<City>>() {
            }.getType();
            return gson.fromJson(json, listType);
        }

        @Override
        protected void onPostExecute(List<City> cities) {
            if (jsonReadListener != null)
                jsonReadListener.onFileRead(cities);
        }
    }

    JsonReadListener jsonReadListener;

    public void setJsonReadListener(JsonReadListener listener) {
        jsonReadListener = listener;
    }

    public interface JsonReadListener {
        void onFileRead(List<City> cities);
    }
}
