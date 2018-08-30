package com.example.a14024780.mobileapps;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

    final EditText userInput = (EditText)findViewById(R.id.motorValue);

        final Button b1 = (Button) findViewById(R.id.LoadBtn);
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
              sensorData data = new sensorData("Danny", "Slider");
                try {
                    getSensorData(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button b2 = (Button) findViewById(R.id.SendBtn);
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String value= userInput.getText().toString();
               // int finalValue=Integer.parseInt(value);
                sensorData data = new sensorData("Danny", "motor", value);
                try {
                    sendToServer(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String sensorServerURL = "http://10.0.2.2:8080/PhidgetServer/sensorToDB";

    private String getSensorData(sensorData data) throws UnsupportedEncodingException {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;

        TextView sensorValueView = (TextView)findViewById(R.id.sensorValue);

        // Create a url to invoke servlet that will get sensor data based on sensorname supplied
        Gson gson = new Gson();
        String jsonString = gson.toJson(data);

        String fullURL = sensorServerURL + "?getdata=true&sensordata=" + URLEncoder.encode(jsonString, "UTF-8");
        System.out.println("Sending data to: "+fullURL);
        String line;
        String result = "";

        try {url = new URL(fullURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;

            }

            rd.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        sensorValueView.setText(result);
        return result;

    }

    public String sendToServer(sensorData data) throws UnsupportedEncodingException {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        Gson gson = new Gson();
        String jsonString = gson.toJson(data);

        String fullURL = sensorServerURL + "?getdata=true&sensordata=" + URLEncoder.encode(jsonString, "UTF-8");
        System.out.println("Sending data to: "+fullURL);
        String line;
        String result = "";
        try {
            url = new URL(fullURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}
	
    private static sensorData createJsonObject(){

        sensorData data1 = new sensorData("motor", "120", "Danny");

        return data1;
    }

}
