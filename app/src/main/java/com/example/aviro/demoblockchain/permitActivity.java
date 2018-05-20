package com.example.aviro.demoblockchain;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class permitActivity extends AppCompatActivity {

    Button accept,decline,back,submit;
    TextView newValue,u_name;

    String query,userName,value;
    int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permit);
        back=findViewById(R.id.back);
        accept=findViewById(R.id.accept);
        decline=findViewById(R.id.decline);
        newValue=findViewById(R.id.newValue);
        u_name=findViewById(R.id.u_name);
        Intent intent=getIntent();
        userName=intent.getStringExtra("name");

        u_name.setText(userName);
        query="SELECT asset from temp_table ORDER by Serial_no DESC LIMIT 1;";
        new ExecuteTask().execute(query);

    }
    public void accept(View view)
    {
        Toast.makeText(this, "You Accepted", Toast.LENGTH_SHORT).show();
        decline.setVisibility(View.GONE);
        counter ob=new counter();
        ob.acceptCount(userName);
        ob.userCount(value);

    }
    public void decline(View view)
    {
        Toast.makeText(this, "You Declined", Toast.LENGTH_SHORT).show();
        accept.setVisibility(View.GONE);
    }
    //--------------------------------------------------------------------------------------------------------------------------
    class ExecuteTask extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... parameter) {
            String res=PostData(parameter);
            return res;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Ye hai=======>>>>>"+result);
            fetch(result);
        }


    }
    //---------------------------------------------------------------------------------------------------------------------------

    private void fetch(String result) {

        try {
            JSONArray array = new JSONArray(result);
            length = array.length();
            System.out.println("Length--->>"+length);
            String[] s1 = new String[length];


            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);
                s1[i] = obj.getString("asset");

                System.out.println("name:" + s1[i]);




            }
            int l = s1.length;
            int j = 0;




                newValue.setText(s1[0]);
                value=s1[0];
                System.out.println("Jo apka value hai--->>>>>>>>>>>>>"+s1[0]);
            counter ob=new counter();





        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------

    private String PostData(String[] values) {

        String s = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.43.101:8181/sampleChain/latestElement.php");
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


//---------------------------------------------------------------------------------------------------------------------------------







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
    public void back(View view)
    {
        Intent intent =new Intent(permitActivity.this,dashBoard.class);
        startActivity(intent);
    }
    //-----------------------------------------------------------------------------------------------------------------------
}
