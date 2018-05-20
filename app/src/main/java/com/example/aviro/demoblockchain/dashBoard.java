package com.example.aviro.demoblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dashBoard extends AppCompatActivity {
    Button back,addBlock,viewBlock,permit;
    TextView u_name;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        back=findViewById(R.id.but);
        addBlock=findViewById(R.id.addBlock);
        viewBlock=findViewById(R.id.viewBlock);
        permit=findViewById(R.id.permit);
        u_name=findViewById(R.id.u_name);
        Intent intent=getIntent();
        userName=intent.getStringExtra("name");
//        System.out.println("NAAAAAAAAAAAAAAME===========>>>>>>"+userName);
        u_name.setText("Welcome "+userName);
    }
    public void viewBlock(View view)
    {
        Intent intent=new Intent(dashBoard.this,Blocks.class);
        intent.putExtra("name",userName);
        startActivity(intent);
    }
    public void addBlock(View view)
    {
        Intent intent=new Intent(dashBoard.this,addBlockActivity.class);
        intent.putExtra("name",userName);
        startActivity(intent);
    }
    public void permit(View view)
    {
        Intent intent=new Intent(dashBoard.this,permitActivity.class);
        intent.putExtra("name",userName);
        startActivity(intent);
    }
    public void viewUsers(View view)
    {
        Intent intent=new Intent(dashBoard.this,userActivity.class);
        intent.putExtra("name",userName);
        startActivity(intent);
    }

    public void back(View view)
    {
        Intent intent =new Intent(dashBoard.this,Login.class);
        startActivity(intent);
    }
}
