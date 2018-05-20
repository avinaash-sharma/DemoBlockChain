package com.example.aviro.demoblockchain;

import android.content.Intent;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class userActivity extends AppCompatActivity {


    int length;
    LinearLayout linearLayout;
    TextView textView,u_name;
    String query,userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        linearLayout = findViewById(R.id.linearLayout);
        u_name=findViewById(R.id.u_name);
        Intent intent=getIntent();
        userName=intent.getStringExtra("name");

        u_name.setText(userName);

        query = "SELECT name FROM users;";
        try {
            new ExecutionTask().execute(query);
        }
        catch(Exception e){}
    }

    class ExecutionTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String res = PostData(strings);
            return res;
        }

//        @Override
//        protected void onPreExecute(String result) {
//            fetch(result);
//        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Results----------------> "+result);
            fetch(result);

        }
    }

    private void fetch(String result) {

        try {
            JSONArray array = new JSONArray(result);
            length = array.length();
            String[] s1 = new String[length];
            counter ob=new counter();


            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);
                s1[i] = obj.getString("name");


            }
            int l=length;
            int j=0;


          for (int i = 0; i < l; i++) {

                textView = new TextView(this);
                textView.setId(i);
                textView.setText(s1[j]);

                System.out.println(i + "-->" + s1[j]);
                j++;
                linearLayout.addView(textView);
            }


            s1 = null;

        } catch (JSONException e) {
        }

    }

    private String PostData(String[] values) {

        String s = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.43.101:8181/sampleChain/user.php");
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
