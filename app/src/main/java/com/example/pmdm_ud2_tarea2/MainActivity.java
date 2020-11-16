package com.example.pmdm_ud2_tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private View view;
    private ToggleButton toggleButton;
    private EditText textName;
    private EditText textDate;
    private RadioButton radioMan;
    private RadioButton radioWoman;
    private RadioGroup radioSex;
    private TextView textMostrar;
    private ImageButton img;
    private TextView textViewName;
    private TextView textViewDate;
    private TextView textViewSex;

    private Calendar calendario = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        view = (View) findViewById(R.id.view);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        textName = (EditText) findViewById(R.id.editTextName);
        textDate = (EditText) findViewById(R.id.editTextDate);
        radioMan = (RadioButton) findViewById(R.id.radioButtonMan);
        radioWoman = (RadioButton) findViewById(R.id.radioButtonWoman);
        radioSex = (RadioGroup) findViewById(R.id.radioSex);
        textMostrar = (TextView) findViewById(R.id.textViewResult);
        img = (ImageButton) findViewById(R.id.imageButton);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewSex = (TextView) findViewById(R.id.textViewSex);

        //Evento que sucede al pulsar en el toggleButton
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    view.setBackgroundColor(Color.rgb(66, 66, 66));
                    textViewName.setTextColor(WHITE);
                    textViewDate.setTextColor(WHITE);
                    textViewSex.setTextColor(WHITE);
                    textName.setTextColor(WHITE);
                    textDate.setTextColor(WHITE);
                    textMostrar.setTextColor(WHITE);
                    radioMan.setTextColor(WHITE);
                    radioWoman.setTextColor(WHITE);
                } else {
                    view.setBackgroundColor(Color.TRANSPARENT);
                    textViewName.setTextColor(BLACK);
                    textViewDate.setTextColor(BLACK);
                    textViewSex.setTextColor(BLACK);
                    textName.setTextColor(BLACK);
                    textDate.setTextColor(BLACK);
                    textMostrar.setTextColor(BLACK);
                    radioMan.setTextColor(BLACK);
                    radioWoman.setTextColor(BLACK);
                }
            }
        });

        //Evento que sudece al pulsar en el campo de texto de la fecha de nacimiento.
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //Evento que sucede al pulsar el boton
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textName.getText().toString().isEmpty()  && !textDate.getText().toString().isEmpty()) {
                    textMostrar.setText("Hola " + textName.getText() + ", tienes " + calcularEdad(calendario) + " años y eres " + radioButtonChecked() + ".");
                    img.setImageResource(R.drawable.goku);
                } else {
                    textMostrar.setText("Debes intruducir tu nombre y fecha de nacimiento");
                }
            }
        });

        //Evento al pusar la imagen.
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textMostrar.setText(null);
                textName.setText(null);
                textDate.setText(null);
                radioMan.setChecked(false);
                radioWoman.setChecked(false);
                img.setImageResource(0);
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };

    //Método para calcular la edad
    private int calcularEdad(Calendar fechaNacimiento) {
        Calendar today = Calendar.getInstance();

        int diffYear = today.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int diffMonth = today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int diffDay = today.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);

        if(diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
            diffYear = diffYear -1;
        }
        return diffYear;
    }

    //Método que actualiza el campo de texto de fecha de nacimiento.
    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        textDate.setText(sdf.format(calendario.getTime()));
    }

    //Método que retorna texto según el radioButton checkeado.
    private String radioButtonChecked() {
        if(radioMan.isChecked()) {
            String text = "hombre";
            return text;
        } else if (radioWoman.isChecked()) {
            String text = "mujer";
            return text;
        } else {
         return "...";
        }
    }
}