package com.egco428.jsonparserhw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private String url1 = "http://www.w3schools.com/js/myTutorials.txt";
    //private String url2 = "&appid=a21221acd1aef50e41c78a401bba2413";
    private TextView display,url;
    private JsonParser obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView)findViewById(R.id.textView);
        url = (TextView)findViewById(R.id.textView2);

    }

    public void openWeathereMethod(View view){

        String finalUrl = url1 ;

        obj = new JsonParser(finalUrl);
        obj.fetchJSON();
        while (obj.parsingComplete);
        display.setText(obj.getDisplay());

        url.setText(obj.getUrl());
        //humidity.setText(obj.getHumidity());
        //pressure.setText(obj.getPressure());

    }
}
