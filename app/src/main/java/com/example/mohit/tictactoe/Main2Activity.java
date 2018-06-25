package com.example.mohit.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main2Activity extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    Button button;

    RadioGroup radioGroup;
    String sizeGrid=null;
    RadioButton radioButton1,radioButton2,radioButton3;

    SharedPreferences sharedPreferences;
    public  final String RADIO_KEY="radio_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button=findViewById(R.id.button);
        button.setOnClickListener(this);

        radioGroup=findViewById(R.id.radgrp);
        radioGroup.setOnCheckedChangeListener(this);

        radioButton1=findViewById(R.id.sizeid3);
        radioButton2=findViewById(R.id.sizeid4);
        radioButton3=findViewById(R.id.sizeid5);

        sharedPreferences=getSharedPreferences("my_Shared_file",MODE_PRIVATE);
        sizeGrid=sharedPreferences.getString(RADIO_KEY,null);
        if(sizeGrid!=null)
        {
            if(sizeGrid.equals("size3"))
            {
                radioButton1.setChecked(true);
            }
            else if(sizeGrid.equals("size4"))
            {
                radioButton2.setChecked(true);
            }
            else if(sizeGrid.equals("size5"))
            {
                radioButton3.setChecked(true);
            }
        }

    }

    @Override
    public void onClick(View view)
    {
        if(sizeGrid!=null)
        {
            Intent intent= new Intent(this,MainActivity.class);
            intent.putExtra("size",sizeGrid);

            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString(RADIO_KEY,sizeGrid);
            editor.commit();

            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i==R.id.sizeid3)
        {
            sizeGrid="size3";
        }
        else if(i==R.id.sizeid4)
        {
            sizeGrid="size4";
        }
        else if(i==R.id.sizeid5)
        {
            sizeGrid="size5";
        }
    }
}