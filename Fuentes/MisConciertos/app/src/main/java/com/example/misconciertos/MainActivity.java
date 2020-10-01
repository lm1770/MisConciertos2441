package com.example.misconciertos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misconciertos.dto.Conciertos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private static final String tag ="MainAtivity";
   private EditText artistaTxt, valorEntradaTxt;
   private Button agregarBtn;
   private TextView fecha, estados;
   private Spinner spinnerGeneros, spinnerCalificacion;
   private ListView conciertosLv;
   private DatePickerDialog.OnDateSetListener onDateSetListener;
   private List<Conciertos> MisConciertos = new ArrayList<>();
   private ArrayAdapter<Conciertos> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fecha = (TextView) findViewById(R.id.IdFecha);
        this.artistaTxt = findViewById(R.id.IdArtistaTxt);
        this.valorEntradaTxt = findViewById(R.id.IdValorTxt);
        this.agregarBtn= findViewById(R.id.agregarBtn);
        this.conciertosLv = findViewById(R.id.IdConciertosLv);
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MisConciertos);
        this.conciertosLv.setAdapter(adapter);


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
        final ArrayAdapter<CharSequence> adapterG=ArrayAdapter.createFromResource(this, R.array.spinnerGeneros,android.R.layout.simple_spinner_item);
        spinnerGeneros.setAdapter(adapterG);



        //fin Spinner Generos

        //spinner calificacion
        spinnerCalificacion = findViewById(R.id.IdCalificacionSpinner);
        ArrayAdapter<CharSequence> adapterC=ArrayAdapter.createFromResource(this, R.array.spinnerCalificacion,android.R.layout.simple_spinner_item);
        spinnerCalificacion.setAdapter(adapterC);



        //fin spinner calificacion

        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hola", Toast.LENGTH_SHORT).show();
                List<String> errores = new ArrayList<>();
                String artistaS  = artistaTxt.getText().toString().trim();
                String fecS = fecha.getText().toString().trim();
                String generoS = spinnerGeneros.getSelectedItem().toString().trim();
                String valorS = valorEntradaTxt.getText().toString().trim();
                String calificacionS = spinnerCalificacion.getSelectedItem().toString().trim();
                int val=0, cal=0;

                //Toast.makeText(MainActivity.this," "+ artistaS+" "+ fecS+" " + generoS+" " +valorS+" "+calificacionS, Toast.LENGTH_SHORT).show();

                if (artistaS.equals("")){
                    errores.add("Agregue un artista");
                }
                if (fecS.equals("")){
                    errores.add("Agregue una fecha");
                }

                if (generoS.equals("Seleccione")){
                    errores.add("Seleccione un genero Musical");
                }

                try {
                    val = Integer.parseInt(valorS);

                }catch (NumberFormatException ex){
                    errores.add("Agregue un valor de entrada");

                }

                try {
                    cal= Integer.parseInt(calificacionS);


                }catch (NumberFormatException ex){
                    errores.add("Seleccione una calificacion");

                }

                if(errores.isEmpty()){
                    //Toast.makeText(MainActivity.this," "+ artistaS+" "+ fecS+" " + generoS+" " +valorS+" "+calificacionS, Toast.LENGTH_SHORT).show();
                    Conciertos con = new Conciertos();
                    con.setArtista(artistaS);
                    con.setFecha(fecS);
                    con.setGenero(generoS);
                    con.setValor(Integer.parseInt(valorS));
                    con.setCalificacion(Integer.parseInt(calificacionS));
                    MisConciertos.add(con);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this,"Agregado exitosamente", Toast.LENGTH_SHORT).show();

                }else{
                    mostrarErrores(errores);
                }


            }
        });




    }

    private void mostrarErrores(List<String> errores){
        String mensaje ="";
        for(String e: errores){
            mensaje+=  "-" + e +"\n";
        }
        //mostrar mensaje de alerta
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error de validacion").setMessage(mensaje).setPositiveButton("Aceptar",null).create().show();

    }


}