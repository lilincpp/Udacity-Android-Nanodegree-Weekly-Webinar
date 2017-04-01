package com.cpp.lilin.httpdemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: " + isConnected());
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    new MyAsynaTask().execute("http://www.baidu.com/");
                } else {
                    Toast.makeText(MainActivity.this, "没有网络!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Asynctask

    class MyAsynaTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Log.e(TAG, "doInBackground: " + Thread.currentThread().getName());
            return request1(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //返回的结果，并且在UI线程中的
            Log.e(TAG, "onPostExecute: " + Thread.currentThread().getName());
            Log.e(TAG, "onPostExecute: response = " + s);
        }
    }

    //HTTPUrlConnection实例
    private String request(String url) {
        String response = null;

        try {
            URL newUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) newUrl.openConnection();
            //设置连接超时的时间
            conn.setConnectTimeout(10000);
            //设置读取超时时间
            conn.setReadTimeout(15000);
            //构建HTTP报文
            conn.setRequestMethod("GET");
            //头部
            conn.setRequestProperty("Connection", "Keep-alive");
            conn.setRequestProperty("Accept", "text/html");

            //发起链接
            conn.connect();

            InputStream is = conn.getInputStream();
            //解析
            response = parse(is);

            Log.e(TAG, "requet: " + response);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private static final String TAG = "MainActivity";

    private String parse(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    private boolean isConnected() {
        ConnectivityManager manager =
                (ConnectivityManager) this.
                        getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    //HTTPClient实例
    private String request1(String url) {
        Log.e(TAG, "request1: " + url);
        String response = null;
        HttpClient client = new DefaultHttpClient(dafaultParams());
        HttpGet httpGet = new HttpGet(url);

        //设置HTTP报文的头部
        httpGet.addHeader("Connection", "Keep-Alive");

        try {
            HttpResponse httpResponse = client.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            Log.e(TAG, "request1: " + (entity == null));
            if (entity != null) {
                InputStream is = entity.getContent();
                response = parse(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //设置请求参数
    private HttpParams dafaultParams() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);
        HttpConnectionParams.setSoTimeout(params, 20000);
        HttpConnectionParams.setTcpNoDelay(params, true);
        //协议参数
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(params, true);
        return params;
    }
}
