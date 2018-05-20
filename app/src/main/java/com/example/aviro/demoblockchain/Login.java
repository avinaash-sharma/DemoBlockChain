package com.example.aviro.demoblockchain;

import android.content.Intent;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText name, password;
    Button login,back;
    String inputName, inputPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        login=findViewById(R.id.login);
        back=findViewById(R.id.back);
    }
    public void login(View v)
    {
        inputName=name.getText().toString();
        inputPass=password.getText().toString();
        System.out.println(inputName);
        new ExecuteTask().execute(inputName,inputPass);
    }
    class ExecuteTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... params) {
            String res=PostData(params);
            return res;
        }

        @Override
        protected void onPostExecute(String result)
        {
            Toast.makeText(getApplication(),result, Toast.LENGTH_LONG).show();
            if(result.trim().equals("success"))
            {

                Intent in=new Intent(getApplication(),dashBoard.class);
                in.putExtra("name",inputName);
                startActivity(in);
            }
            else
                Toast.makeText(Login.this, "not Success", Toast.LENGTH_SHORT).show();
        }
        public String PostData(String[] values)
        {
            String s=" ";
            try {
                HttpClient httpClient=new DefaultHttpClient();
                HttpPost httpPost=new HttpPost("http://192.168.43.101:8181/sampleChain/login.php");
                List<NameValuePair> list=new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("name",values[0]));
                list.add(new BasicNameValuePair("password",values[1]));
                httpPost.setEntity(new UrlEncodedFormEntity(list));
                HttpResponse httpResponse=httpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();
                s=readResponse(httpResponse);
            }
            catch (Exception e)
            {

            }
            return s;
        }
        public String readResponse(HttpResponse res)
        {
            InputStream is=null;
            String return_text=" ";
            try
            {
                is=res.getEntity().getContent();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
                String line=" ";
                StringBuffer sb=new StringBuffer();
                while ((line=bufferedReader.readLine())!=null)
                {
                    sb.append(line);
                }
                return_text=sb.toString();

            }
            catch (Exception e)
            {

            }
            return return_text;
        }
    }

    public void back(View view)
    {
        Intent intent =new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }
}
