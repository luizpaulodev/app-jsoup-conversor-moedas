package com.example.luizpaulo.jsoupconversaomoedas;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String dolarStr;
    private String euroStr;
    private String bitcoinStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetValueBitcoin().execute();
    }

    private class GetValueBitcoin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect("http://dolarhoje.com/bitcoin-hoje/").get();
                bitcoinStr = document.getElementById("nacional").val();

                document = Jsoup.connect("http://dolarhoje.com/euro/").get();
                euroStr = document.getElementById("nacional").val();

                document = Jsoup.connect("http://dolarhoje.com/").get();
                dolarStr = document.getElementById("nacional").val();

                Log.i("Moeda", "Dolar: " + dolarStr);
                Log.i("Moeda", "Euro: " + euroStr);
                Log.i("Moeda", "Bitcoin: " + bitcoinStr);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
