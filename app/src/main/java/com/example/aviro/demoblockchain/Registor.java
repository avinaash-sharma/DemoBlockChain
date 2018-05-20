package com.example.aviro.demoblockchain;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class Registor extends AppCompatActivity {
    EditText name,password;
    Button done;
    String uname,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registor);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        done=findViewById(R.id.done);


    }
    public void done(View view) {
        uname = name.getText().toString();
        pass = password.getText().toString();
        new ExecutionTask().execute(uname, pass);
        Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();
    }
    class ExecutionTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... parameter) {
            String registor=PostData(parameter);

            return registor;
        }
        @Override
        protected void onPostExecute(String result)
        {

        }

        public String PostData(String[] values) {
            String s="";
            try{
                HttpClient httpClient=new DefaultHttpClient();
                HttpPost httpPost=new HttpPost("http://192.168.43.101:8181/sampleChain/register.php");
                List<NameValuePair> list=new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("name",values[0]));
                list.add(new BasicNameValuePair("password",values[1]));
                httpPost.setEntity(new UrlEncodedFormEntity(list));
                HttpResponse httpResponse=httpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();
                s=readResponse(httpResponse);

            }
            catch (Exception e){

            }
            return s;

        }
        public String readResponse(HttpResponse response)
        {
            InputStream inputStream=null;
            String text="";
            try{
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String line=" ";
                StringBuffer sb=new StringBuffer();
                while ((line=bufferedReader.readLine())!=null)
                {
                    sb.append(line);
                }
                text=sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return text;
        }
    }
}
