package com.example.aviro.demoblockchain;

import android.os.AsyncTask;
import android.widget.Toast;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aviro on 5/12/2018.
 */



//this is a method to count no of votes...
public class countingVotes {

    int totalnumber,length,condition;
    String query="SELECT name from users where counter='1';";
    String value;

    public void AcceptedVotes(int length2,String newValue) {

        value=newValue;
        totalnumber=length2;    //here totalnumber is total no of uesrs present in the chain.
        System.out.println("The query for counting no of votes is as ---->>>" + query);
        new ExecuteTask().execute(query);



    }

    class ExecuteTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... parameter) {
            String process = PostData(parameter);
            return process;
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



//            System.out.println("No accepted::::::::::::::::::::::::::::::::>"+length);
            evaluation();
        }
        catch (Exception e){}
    }

    private void evaluation() {

        //length is the no of users accepted the chain.

        System.out.println("No Accepted: "+length);
        System.out.println("No of total users:::::>>>>"+totalnumber);
        condition=(totalnumber/2)+1;
        if(length>=condition)
        {
            creatingBlockChain chain=new creatingBlockChain();
            chain.AddBlock(value);
        }
        else {
            System.out.println("Not enough votes");

        }
    }

    private String PostData(String[] values) {

        String s = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.43.101:8181/sampleChain/noPositiveVoters.php");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("query", values[0]));

            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            s = readResponse(httpResponse);

        } catch (Exception e) {

        }
        return s;
    }

    private String readResponse(HttpResponse response) {

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