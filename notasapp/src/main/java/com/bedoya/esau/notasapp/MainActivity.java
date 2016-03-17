package com.bedoya.esau.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eExpo, ePrac, eProy, eNota;
    Button bCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eExpo=(EditText)findViewById(R.id.eExpo);
        ePrac=(EditText)findViewById(R.id.ePrac);
        eProy=(EditText)findViewById(R.id.eProy);
        eNota=(EditText)findViewById(R.id.eNotaf);
        bCalc=(Button)findViewById(R.id.bCalcular);

        bCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nota;

                nota=Double.parseDouble(eExpo.getText().toString())*0.15+
                        Double.parseDouble(ePrac.getText().toString())*0.5+
                        Double.parseDouble(eProy.getText().toString())*0.35;
                eNota.setText(String.valueOf(nota));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.mConfigurar) {
            // Toast.makeText(this,"Presionó configurar", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("pExpo",15);
            intent.putExtra("pPrac",50);
            intent.putExtra("pProy",35);
            startActivityForResult(intent, 1234);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String exp, pra, pro;
        if(requestCode==1234 && resultCode==RESULT_OK){
            exp=data.getExtras().getString("npExp");
            pra=data.getExtras().getString("npPrac");
            pro=data.getExtras().getString("npProy");
            Toast.makeText(this, "Nuevos datos: Expo: "+exp+" Prac: "+pra+" Proy: "+pro,Toast.LENGTH_SHORT).show();
        }
    }
}
