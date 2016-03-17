package com.bedoya.esau.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    EditText epExpo, epPrac, epProy;
    Button bGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        epExpo=(EditText)findViewById(R.id.epExpo);
        epPrac=(EditText)findViewById(R.id.epPrac);
        epProy=(EditText)findViewById(R.id.epProy);
        bGuardar=(Button)findViewById(R.id.bGuarad);

        Bundle extras=getIntent().getExtras();

        epExpo.setText(String.valueOf(extras.getInt("pExpo")));
        epProy.setText(String.valueOf(extras.getInt("pProy")));
        epPrac.setText(String.valueOf(extras.getInt("pPrac")));

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("npExp",epExpo.getText().toString());
                intent.putExtra("npPrac",epPrac.getText().toString());
                intent.putExtra("npProy",epProy.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
