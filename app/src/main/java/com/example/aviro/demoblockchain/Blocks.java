package com.example.aviro.demoblockchain;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Blocks extends AppCompatActivity {

    LinearLayout prehash, value, currhash, timeStamp;
    String query;
    TextView textView;
    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocks);

        query = "SELECT * FROM `block_chain`";

        prehash = findViewById(R.id.prehash);
        value = findViewById(R.id.value);
        currhash = findViewById(R.id.currhash);
        timeStamp = findViewById(R.id.timeStamp);

        new ExcecutionTask().execute(query);


    }

    class ExcecutionTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... parameter) {
            String res = PostData(parameter);
            return res;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            fetch(result);
        }
    }

    private void fetch(String result) {

        try {
            JSONArray array = new JSONArray(result);
            length = array.length();
            String[] s1 = new String[length];
            String[] s2=new String[length];
            String[] s3=new String[length];
            String[] s4=new String[length];


            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);
                s1[i] = obj.getString("pre_hash");
                s2[i] = obj.getString("asset");
                s3[i]=obj.getString("post_hash");
                s4[i]=obj.getString("time_stamp");
                System.out.println("pre_hash:" + s1[i]);
                System.out.println("value:" + s2[i]);
                System.out.println("post_hash:" + s2[i]);
                System.out.println("time_stamp:" + s2[i]);
            }
            int l = s1.length;
            int j = 0;
            for (int i = 0; i < l; i++) {

                textView = new TextView(this);
                textView.setId(i);
                textView.setText(s1[j]);

                System.out.println(i + "-->" + s1[j]);
                j++;
                prehash.addView(textView);
                textView.setTextSize(20);
            }
            int k=0;
            for (int i = 0; i < l; i++) {

                textView = new TextView(this);
                textView.setId(i);
                textView.setText(s2[k]);
                System.out.println(textView.getText().toString());

                System.out.println(i + "-->" + s2[k]);
                k++;
                value.addView(textView);
                textView.setTextSize(20);
            }
            int m=0;
            for (int i = 0; i < l; i++) {

                textView = new TextView(this);
                textView.setId(i);
                textView.setText(s3[m]);
                System.out.println(i + "-->" + s3[m]);
                m++;
                currhash.addView(textView);
                textView.setTextSize(20);
            }
            int n=0;
            for (int i = 0; i < l; i++) {

                textView = new TextView(this);
                textView.setId(i);
                textView.setText(s4[n]);

                System.out.println(i + "-->" + s4[n]);
                n++;
                timeStamp.addView(textView);
                textView.setTextSize(20);
            }
        } catch (Exception e) {
        }
    }

    private String PostData(String[] values) {

        String s = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.43.101:8181/sampleChain/viewChain.php");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("query", values[0]));

            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            s = readdata(httpResponse);
            System.out.println("List=" + list);

        } catch (Exception exception) {
        }
        return s;
    }

    private String readdata(HttpResponse response) {
        InputStream is = null;
        String return_text = "";
        try {
            is = response.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while (null != (line = bufferedReader.readLine())) {
                sb.append(line);
            }
            return_text = sb.toString();
        } catch (Exception e) {

        }
        return return_text;
    }
}

