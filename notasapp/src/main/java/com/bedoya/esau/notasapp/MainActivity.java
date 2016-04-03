package com.bedoya.esau.notasapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eExpo, ePrac, eProy, eNota;
    Button bCalc, bClean;
    TextView tError;
    Double exp=30.0, pra=50.0, pro=20.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eExpo=(EditText)findViewById(R.id.eExpo);
        ePrac=(EditText)findViewById(R.id.ePrac);
        eProy=(EditText)findViewById(R.id.eProy);
        eNota=(EditText)findViewById(R.id.eNotaf);
        bCalc=(Button)findViewById(R.id.bCalcular);
        tError=(TextView)findViewById(R.id.tError);
        bClean=(Button)findViewById(R.id.bLimpiar);

        eExpo.setHint(exp.toString() + "%");
        ePrac.setHint(pra.toString() + "%");
        eProy.setHint(pro.toString() + "%");

        bCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nexpo, npra, npro, nota;

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(eProy.getWindowToken(), 0);

                if (eExpo.getText().toString().length() == 0 || eProy.getText().toString().length() == 0 || ePrac.getText().toString().length() == 0)
                    tError.setText("Faltan Datos");
                else {
                    nexpo = Double.parseDouble(eExpo.getText().toString());
                    npra = Double.parseDouble(ePrac.getText().toString());
                    npro = Double.parseDouble(eProy.getText().toString());
                    if (nexpo < 0 || nexpo > 5 || npra < 0 || npra > 5 || npro < 0 || npro > 5)
                        tError.setText("Algunos valores están fuera del rango.");
                    else {
                        nota = Math.rint(((nexpo * exp + npra * pra + npro * pro) / 100) * 10) / 10;
                        eNota.setText(Double.toString(nota));
                        tError.setText("");

                    }

                }

            }
        });

        bClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(eProy.getWindowToken(), 0);
                ePrac.setText("");
                eProy.setText("");
                eExpo.setText("");
                eNota.setText("");
                eExpo.setHint(exp.toString() + "%");
                ePrac.setHint(pra.toString() + "%");
                eProy.setHint(pro.toString() + "%");
                tError.setText("");
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
            intent.putExtra("pExpo",exp);
            intent.putExtra("pPrac",pra);
            intent.putExtra("pProy",pro);
            startActivityForResult(intent, 1234);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String sexp,spra,spro;

        if(requestCode==1234 && resultCode==RESULT_OK){
            sexp=data.getExtras().getString("npExp");
            spra=data.getExtras().getString("npPrac");
            spro=data.getExtras().getString("npProy");
            Toast.makeText(this, "Nuevos datos: Expo: "+sexp+" Prac: "+spra+" Proy: "+spro,Toast.LENGTH_SHORT).show();
            exp=Double.parseDouble(sexp);
            pra=Double.parseDouble(spra);
            pro=Double.parseDouble(spro);
            eExpo.setText("");
            eProy.setText("");
            ePrac.setText("");
            eExpo.setHint(sexp + "%");
            ePrac.setHint(spra+ "%");
            eProy.setHint(spro+ "%");
        }
    }
}
