package com.lucarin91.beginandroidtutorial.Helper;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by luca on 4/22/15.
 */
public class HTTP {

    final static String BASE = "http://android-tutorial.sfcoding.com";

    public static void asyncDownload (final String url, final CallBack callback){
        new AsyncTask<Void, Void, String>() {
            IOException error;

            @Override
            protected void onPreExecute() {
                callback.onPreExecution();
            }

            @Override
            protected String doInBackground(Void... params) {
                try {
                    return getUrl(new URL(BASE + url));
                } catch (IOException e) {
                    e.printStackTrace();
                    error = e;
                    return null;

                }
            }

            @Override
            protected void onPostExecute(String data) {
                if(data != null)
                    callback.onPostExecution(data);
                else
                    callback.onError(error);

            }
        }.execute(null, null, null);
    };

    private static String getUrl (URL url) throws IOException {
        InputStream is = null;

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //conn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4" );
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            //start
            conn.connect();
            int response = conn.getResponseCode();
            Log.i("The response is: ", "" + response);
            is = conn.getInputStream();
            return readIt(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private static String readIt(InputStream stream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        String ris=total.toString();
        return ris;
    }

    public interface CallBack {
        public void onPreExecution();
        public void onPostExecution(String data);
        void onError(IOException e);
    }
}
