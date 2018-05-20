package com.example.aviro.demoblockchain;

import android.os.AsyncTask;

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

/**
 * Created by aviro on 5/12/2018.
 */

public class creatingBlockChain {

    String value;
    public void AddBlock(String newValue)
    {
        value=newValue;
        new ExecutionTask().execute(value);



    }
    class ExecutionTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... parameter) {
            String update=PostData(parameter);
            return update;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private String PostData(String[] values) {
        String s="";
        try{
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://192.168.43.101:8181/sampleChain/addBlock.php");
            List<NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("value",values[0]));
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse=httpClient.execute(httpPost);
            HttpEntity httpEntity=httpResponse.getEntity();
            s=readResponse(httpResponse);

        }
        catch (Exception e){

        }
        return s;
    }

    private String readResponse(HttpResponse httpResponse) {
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
