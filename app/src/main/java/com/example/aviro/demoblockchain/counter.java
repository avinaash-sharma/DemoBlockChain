package com.example.aviro.demoblockchain;

import android.os.AsyncTask;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class counter {
    int length2,numberAccpeted, length,condition;
    String query,query2,value;

    public void userCount(String newValue) {

        value=newValue;
        //this method "userCount" is to count no of users who accepted the block.
        query2 = "SELECT name FROM users;";
        System.out.println("This is it");
        new ExecutionTaskCount().execute(query2);
        System.out.println("This is it 2");
        System.out.println(length);



    }
    //this part is ment to count the length of users registered.
    class ExecutionTaskCount extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            System.out.println("This is it 3");
            String res = PostDataCount(strings);
            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            fetchCount(result);
        }
    }

    private void fetchCount(String result) {

        try {
            JSONArray array = new JSONArray(result);
            length = array.length();
            String[] s1 = new String[length];


            System.out.println("The length is"+length);


            //-----------------------------------------------------------------------------------------------------
            countingVotes ob1=new countingVotes();
            ob1.AcceptedVotes(length,value);
            //-----------------------------------------------------------------------------------------------------

        } catch (Exception e) {
        }
    }

    private String PostDataCount(String[] values) {

        String s = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.43.101:8181/sampleChain/user.php");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("query", values[0]));

            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            s = readdataCount(httpResponse);
            System.out.println("List=" + list);

        } catch (Exception exception) {

        }
        return s;
    }

    private String readdataCount(HttpResponse response) {

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

    //------------------------------------------------------------------------------------------------------------------------------------------
//this part is ment to update the users table with those who accepted the block.
    public void acceptCount(String name) {
        query = "UPDATE users SET counter='1' WHERE name='" + name + "';";
        new ExecutionTask().execute(query);
    }

    class ExecutionTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... parameter) {
            String update = PostData(parameter);
            return update;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String result) {


        }
    }



    private String PostData(String[] values) {
        String s = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.43.101:8181/sampleChain/replaceValue.php");
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
}