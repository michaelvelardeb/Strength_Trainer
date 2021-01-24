package com.example.strength;

import android.os.Bundle;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

 // TODO: 1/23/2021 add proper thread to handle network traffic

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


//TODO:  add file interaction system to save incoming response data (PNGs)

        final String BASE_URL = "https://wger.de/media/exercise-images/4/Crunches-1.png";

        URL url = null;
        try {
            url = new URL(BASE_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = null;
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String a = readStream(in);

            BufferedWriter writer = null;
            File trial = new File("testFile");
            try {
                boolean test = trial.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            trial.canWrite();
            trial.canRead();

            try {
                writer = new BufferedWriter(new FileWriter(trial));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.write(a);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String b = "";

        } finally {
            urlConnection.disconnect();
        }



    }

    private String readStream(InputStream in) {
        String result = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = new BufferedReader(new InputStreamReader(in))
                    .lines().collect(Collectors.joining("\n"));
        }
        return result;
    }
}

