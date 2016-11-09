package com.egco428.jsonparserhw;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonParser {

    private String display = "display";
    private String url = "url";
    private String humidity = "humidity";
    private String pressure = "pressure";
    private String urlString = null;

    public volatile boolean parsingComplete = true;

    public JsonParser(String url){
        this.urlString = url;
    }
    public String getDisplay(){
        return display;
    }
    public String getUrl(){
        return url;
    }
//    public String getHumidity(){
//        return humidity;
//    }
//    public String getPressure(){
//        return pressure;
//    }

    public void readAndParseJSON(String in){
        try {
            JSONArray array = new JSONArray(in);
            for (int i = 0;i<array.length();i++){
                url =url +(i+1)+". "+array.getJSONObject(i).getString("url")+"\n";
            }
            for (int i = 0;i<array.length();i++){
                display =display +(i+1)+". "+array.getJSONObject(i).getString("display")+"\n";
            }
//            JSONObject reader = new JSONObject(in);
//            JSONObject sys = reader.getJSONObject("sys"); //in string from Json "sys" is cover all of String data
//            country = sys.getString("country");
//            JSONObject main = reader.getJSONObject("main");
//            temperature = main.getString("temp");
//            pressure = main.getString("pressure");
//            humidity = main.getString("humidity");
            parsingComplete = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void fetchJSON(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    String data = convertStreamToString(stream);

                    readAndParseJSON(data);
                    stream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
    static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }




}
