package com.igordubrovin.parsehtml;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        MyAsync m = new MyAsync();
        m.execute();
    }

    class MyAsync extends AsyncTask <Void, Void, Void>{
        Elements title;
        Document doc;
        URL url;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String str = "https://t.rasp.yandex.ru/search/suburban/?toName=Одинцово&fromName=Москва";

                doc = Jsoup.connect(str)
                        .userAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
                        .referrer("http://www.google.com")
                        .timeout(5000)
                        .get();

            } catch (IOException e) {
                e.printStackTrace();
            }
            title = doc.select(".b-routers__time_type_departure");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvInfo.setText(title.text());
        }
    }
}
