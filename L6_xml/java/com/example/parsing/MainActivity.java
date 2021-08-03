package com.example.parsing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.parseXML).setOnClickListener(v -> {
            try {
                XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = parserFactory.newPullParser();
                InputStream is = getAssets().open("data.xml");
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(is, null);

                for (int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = parser
                        .next())
                    if (eventType == XmlPullParser.START_TAG) {
                        String eltName = parser.getName();

                        if ("City_Name".equals(eltName))
                            ((TextView) findViewById(R.id.XML_city_name)).setText(parser.nextText());
                        else if ("Latitude".equals(eltName))
                            ((TextView) findViewById(R.id.XML_latitude)).setText(parser.nextText());
                        else if ("Longitude".equals(eltName))
                            ((TextView) findViewById(R.id.XML_longitude)).setText(parser.nextText());
                        else if ("Temperature".equals(eltName))
                            ((TextView) findViewById(R.id.XML_temperature)).setText(parser.nextText());
                        else if ("Humidity".equals(eltName))
                            ((TextView) findViewById(R.id.XML_humidity)).setText(parser.nextText());
                    }

                findViewById(R.id.parseXML).setVisibility(View.GONE);
                findViewById(R.id.resultXML).setVisibility(View.VISIBLE);
                is.close();
            } catch (XmlPullParserException | IOException ignored) {
            }
        });

        findViewById(R.id.parseJSON).setOnClickListener(v -> {
            try {
                InputStream is = getAssets().open("data.json");

                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();

                JSONArray obj = new JSONArray(new String(buffer, StandardCharsets.UTF_8));
                ((TextView) findViewById(R.id.JSON_city_name)).setText(obj.getJSONObject(0).getString("City_Name"));
                ((TextView) findViewById(R.id.JSON_latitude)).setText(obj.getJSONObject(0).getString("Latitude"));
                ((TextView) findViewById(R.id.JSON_longitude)).setText(obj.getJSONObject(0).getString("Longitude"));
                ((TextView) findViewById(R.id.JSON_temperature)).setText(obj.getJSONObject(0).getString("Temperature"));
                ((TextView) findViewById(R.id.JSON_humidity)).setText(obj.getJSONObject(0).getString("Humidity"));

                findViewById(R.id.parseJSON).setVisibility(View.GONE);
                findViewById(R.id.resultJSON).setVisibility(View.VISIBLE);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
    }
}