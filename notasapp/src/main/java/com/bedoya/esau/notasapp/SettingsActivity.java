package com.bedoya.esau.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    EditText epExpo, epPrac, epProy;
    Button bGuardar, bClean2;
    TextView tErrPor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        epExpo=(EditText)findViewById(R.id.epExpo);
        epPrac=(EditText)findViewById(R.id.epPrac);
        epProy=(EditText)findViewById(R.id.epProy);
        bGuardar=(Button)findViewById(R.id.bGuarad);
        tErrPor=(TextView)findViewById(R.id.tErPor);
        bClean2=(Button)findViewById(R.id.bLimPor);

        Bundle extras=getIntent().getExtras();

        epExpo.setText(String.valueOf(extras.getDouble("pExpo")));
        epProy.setText(String.valueOf(extras.getDouble("pProy")));
        epPrac.setText(String.valueOf(extras.getDouble("pPrac")));

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (epPrac.getText().toString().length() == 0 || epProy.getText().toString().length() == 0 || epExpo.getText().toString().length() == 0)
                    tErrPor.setText("Faltan Datos");
                else if ((Double.parseDouble(epExpo.getText().toString()) + Double.parseDouble(epPrac.getText().toString()) +
                        Double.parseDouble(epProy.getText().toString())) != 100)
                    tErrPor.setText("El porcentaje no es igual a 100%");
                else {
                    tErrPor.setText("");
                    Intent intent = new Intent();
                    intent.putExtra("npExp", epExpo.getText().toString());
                    intent.putExtra("npPrac", epPrac.getText().toString());
                    intent.putExtra("npProy", epProy.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();

                }

            }
        });
        bClean2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epPrac.setText("");
                epProy.setText("");
                epExpo.setText("");
                tErrPor.setText("");

            }
        });
    }
}
