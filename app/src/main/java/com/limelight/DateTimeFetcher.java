package com.limelight;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFetcher extends AsyncTask<Void, Void, String> {

    private DateTimeFetchListener listener;

    public DateTimeFetcher(DateTimeFetchListener listener, PcView pcView) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String apiUrl = "https://worldtimeapi.org/api/timezone/Asia/Manila";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get response
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }

            // Close connections
            bufferedReader.close();
            inputStream.close();
            connection.disconnect();

            return response.toString();
        } catch (IOException e) {
            Log.e("DateTimeFetcher", "Error fetching data: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                String dateTime = jsonObject.getString("datetime");

                String formattedDate = getFormattedDate(dateTime);
                listener.onDateTimeFetched(formattedDate);
            } catch (JSONException e) {
                Log.e("DateTimeFetcher", "Error parsing JSON: " + e.getMessage());
            }
        } else {
            Log.e("DateTimeFetcher", "No data received from API");
        }
    }

    public static String getFormattedDate(String dateTimeString) {
        String formattedDate = "";
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
            Date date = sdfInput.parse(dateTimeString);

            SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyyMMdd");
            formattedDate = sdfOutput.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public interface DateTimeFetchListener {
        void onDateTimeFetched(String dateTime);
    }
}
