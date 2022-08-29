package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);
        textView=findViewById(R.id.response_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendRequestWithHttpUrlConnection();
                sendRequestWithOkhttp();
            }
        });
    }

    private void sendRequestWithHttpUrlConnection(){
        new Thread((new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection=null;
                BufferedReader bufferedReader=null;
                URL url=null;
                try {
                    try {
                        url=new URL("https://www.baidu.com");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setRequestMethod("GET");

                    DataOutputStream dataOutputStream=new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.writeBytes("username=admin&password=12345");

                    InputStream inputStream=httpURLConnection.getInputStream();
                    bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder=new StringBuilder();
                    String line;
                    while((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line);
                    }
                    show_response(stringBuilder.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(bufferedReader!=null){
                        try {
                            bufferedReader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(httpURLConnection!=null){
                        httpURLConnection.disconnect();
                    }
                }
            }
        })).start();
    }



    private void sendRequestWithOkhttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient=new OkHttpClient();
//                    Request request=new Request.Builder().url("http://192.168.130.213/get_data.xml").build();
                    Request request=new Request.Builder().url("http://192.168.130.213/get_data.json").build();
                    Response response=okHttpClient.newCall(request).execute();
                    String data=response.body().string();

//                    show_response(data);
//                    parseXMLWithPull(data);
//                    parseXMLWithSAX(data);
//                    parseJSONWithJSONObject(data);
                    parseJSONWithGson(data);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGson(String data) {
        Gson gson=new Gson();
        List<App>appList=gson.fromJson(data,new TypeToken <List<App>>(){}.getType());
        for(App app:appList){
            Log.d("MainActivity:","id:"+app.getId());
            Log.d("MainActivity:","name"+app.getName());
            Log.d("MainActivity:","version"+app.getVersion());
        }

    }


    private void parseJSONWithJSONObject(String data) {
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(data);
            for(int i=0;i<jsonArray.length();++i){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
                String version=jsonObject.getString("version");
                Log.d("MainActivity","id:"+id);
                Log.d("MainActivity","name:"+name);
                Log.d("MainActivity","version:"+version);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void parseXMLWithSAX(String data) {
        try {
            SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
            XMLReader xmlReader=saxParserFactory.newSAXParser().getXMLReader();
            ContentHandler contentHandler=new ContentHandler();
            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(new InputSource(new StringReader(data)));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void parseXMLWithPull(String data) {
        try {
            XmlPullParserFactory xmlPullParserFactory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(data));
            int eventType=xmlPullParser.getEventType();
            String id="";
            String name="";
            String version="";
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName=xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("id".equals(nodeName)){
                            id=xmlPullParser.nextText();
                        }else if("name".equals(nodeName)){
                            name=xmlPullParser.nextText();
                        }else if("version".equals(nodeName)){
                            version=xmlPullParser.nextText();
                        }
                        break;

                        case XmlPullParser.END_TAG:
                            if("app".equals(nodeName)){
                                Log.d("id:",id);
                                Log.d("name:",name);
                                Log.d("version:",version);
                            }
                            break;
                    default:
                        break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void show_response(String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(str);
            }
        });
    }
}