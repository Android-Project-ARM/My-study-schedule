package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class NewSubject extends ActionBarActivity {

    final public static String NewAsigntura = "com.example.marcelba.mystudyschedule.NewAsigntura";
    final public static String NewAsignturaProfesor = "com.example.marcelba.mystudyschedule.NewAsignturaProfesor";
    final public static String NewAsignturaColor = "com.example.marcelba.mystudyschedule.NewAsignturaColor";
    final public static String NewAsignturaDia = "com.example.marcelba.mystudyschedule.NewAsignturaDia";
    final public static String NewAsignturaHoraIni = "com.example.marcelba.mystudyschedule.NewAsignturaHoraIni";
    final public static String NewAsignturaHoraFin = "com.example.marcelba.mystudyschedule.NewAsignturaHoraFin";

    private EditText editTextSubject;
    private EditText editTextTeacher;
    private Spinner spDia01;
    private Spinner spHoraInicio01;
    private Spinner spMinutosInicio01;
    private Spinner spHoraFinal01;
    private Spinner spMinutosFinal01;

    private CalendarController cal = Inicio.cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_asignatura);

        editTextSubject = (EditText) findViewById(R.id.EtAsignatura);
        editTextTeacher = (EditText) findViewById(R.id.EtProfesor);
        spDia01 = (Spinner) findViewById(R.id.SpDia01);
        spHoraInicio01 = (Spinner) findViewById(R.id.SpHoraInicio01);
        spMinutosInicio01 = (Spinner) findViewById(R.id.SpMinutosInicio01);
        spHoraFinal01 = (Spinner) findViewById(R.id.SpHoraFinal01);
        spMinutosFinal01 = (Spinner) findViewById(R.id.SpMinutosFinal01);

        loadSpinnerDias();
        loadSpinnerHoras();
        loadSpinnerMinutos();

        Bundle extras = getIntent().getExtras();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_asignatura, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            saveNewSubject(item.getActionView());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Populate the Spinner.
     */
    private void loadSpinnerDias() {

        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Dias, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spDia01.setAdapter(adapter);
    }

    private void loadSpinnerHoras() {

        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Horas, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spHoraInicio01.setAdapter(adapter);
        spHoraFinal01.setAdapter(adapter);
    }

    private void loadSpinnerMinutos() {

        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Minutos, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spMinutosInicio01.setAdapter(adapter);
        spMinutosFinal01.setAdapter(adapter);
    }


    /**
     * Save New Subject.*
     */
    private void saveNewSubject(View newAsignatura) {

        String AsignturaNombre = editTextSubject.getText().toString();

        String AsignaturaProfesor = editTextSubject.getText().toString();

        String AsignaturaDia = spDia01.getSelectedItem().toString();

        int AsignaturaHoraInicio = Integer.parseInt(spHoraInicio01.getSelectedItem().toString());

        int AsignaturaMinuotsInicio = Integer.parseInt(spMinutosInicio01.getSelectedItem().toString());

        int AsignaturaHoraFinal = Integer.parseInt(spHoraFinal01.getSelectedItem().toString());

        int AsignaturaMinuotsFinal = Integer.parseInt(spMinutosFinal01.getSelectedItem().toString());

        //Log.i("Asignatura",AsignturaNombre);
        //Log.i("Profesor",AsignaturaProfesor);
        //Log.i("Dia",AsignaturaDia);
        //Log.i("Hora Inicio",AsignaturaHoraInicio +":"+AsignaturaMinuotsInicio );
        //Log.i("Hora Final",AsignaturaHoraFinal +":"+AsignaturaMinuotsFinal );

        //may overflow
        int eventId = cal.IntroduceNewSubject(this, Utils.Month(AsignaturaDia), Utils.dayOfTheMonth(AsignaturaDia), AsignaturaHoraInicio, AsignaturaMinuotsInicio, AsignaturaHoraFinal, AsignaturaMinuotsFinal, AsignturaNombre, "Profesor: " + AsignaturaProfesor);
        Log.i("integerID", ""+eventId);

        /** Guardamos la assignatura en una mini BD para el spinner **/
       // Inicio.db.AddSubject(AsignturaNombre);


        Intent Subjects = new Intent(this, Subjects.class);
        startActivity(Subjects);


    }

}
