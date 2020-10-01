package com.example.misconciertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

   private static final String tag ="MainAtivity";
   private TextView fecha, estados;
   private DatePickerDialog.OnDateSetListener onDateSetListener;
   private Spinner spinnerGeneros, spinnerCalificacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fecha = (TextView) findViewById(R.id.IdFecha);

        //fecha
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                int mes = cal.get(Calendar.MONTH);
                int ano = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,dia,mes,ano);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                mes = mes+1;
                Log.d(tag,dia +"/"+ mes +"/"+ano );

                String fechaConcierto = dia +"/"+ mes +"/"+ano;
                fecha.setText(fechaConcierto);

            }
        };

        //end fecha

        //Spinner Generos
        //estados = (TextView) findViewById(R.id.IdGeneroSpinner);
        spinnerGeneros = findViewById(R.id.IdGeneroSpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.spinnerGeneros,android.R.layout.simple_spinner_item);
        spinnerGeneros.setAdapter(adapter);

        //fin Spinner Generos

        //spinner calificacion
        spinnerCalificacion = findViewById(R.id.IdCalificacionSpinner);
        ArrayAdapter<CharSequence> adapterC=ArrayAdapter.createFromResource(this, R.array.spinnerCalificacion,android.R.layout.simple_spinner_item);
        spinnerCalificacion.setAdapter(adapterC);


        //fin spinner calificacion












    }
}