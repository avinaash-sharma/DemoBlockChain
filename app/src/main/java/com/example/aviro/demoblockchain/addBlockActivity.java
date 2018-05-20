package com.example.aviro.demoblockchain;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class addBlockActivity extends AppCompatActivity {

    Button submit, back;
    EditText textField;
    TextView u_name;
    String userName,query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_block);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);
        textField = findViewById(R.id.newValue);
        u_name=findViewById(R.id.u_name);
        Intent intent=getIntent();
        userName=intent.getStringExtra("name");
        query="update users set counter = 0";
        u_name.setText(userName);

    }

    public void submit(View view) {
        String asset = textField.getText().toString();
        new ExecutionTask().execute(asset);
        new ExecutionTask2().execute(query);
    }

    class ExecutionTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... parameter) {

            String registor = PostData(parameter);
            return registor;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(addBlockActivity.this, "done..", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(String result) {


        }

        public String PostData(String[] values) {
            String s = "";
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.43.101:8181/sampleChain/addNew.php");
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("value", values[0]));
//                list.add(new BasicNameValuePair("password", values[1]));
                httpPost.setEntity(new UrlEncodedFormEntity(list));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                s = readResponse(httpResponse);

            } catch (Exception e) {

            }
            return s;
        }

        public String readResponse(HttpResponse response) {
            InputStream inputStream = null;
            String text = "";
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = " ";
                StringBuffer sb = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                text = sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return text;
        }
    }
    class ExecutionTask2 extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... parameter) {
            String setZero=PostData2(parameter);
            return setZero;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }

    private String PostData2(String[] values) {

        String s = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.43.101:8181/sampleChain/setZero.php");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("query", values[0]));
//                list.add(new BasicNameValuePair("password", values[1]));
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            s = readResponse(httpResponse);

        } catch (Exception e) {

        }
        return s;
    }

    private String readResponse(HttpResponse httpResponse) {

        InputStream inputStream = null;
        String text = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = " ";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            text = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public void back(View view)
    {
        Intent intent=new Intent(addBlockActivity.this,dashBoard.class);
        startActivity(intent);
    }
}
