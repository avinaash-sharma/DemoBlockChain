package com.example.aviro.demoblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);

    }
    public void login(View view)
    {

        Intent intent=new Intent(MainActivity.this,Login.class);
        {
            startActivity(intent);

        }
    }
    public void Registor(View view)
    {
        Intent intent=new Intent(MainActivity.this,Registor.class);
        {
            startActivity(intent);
        }
    }

}
