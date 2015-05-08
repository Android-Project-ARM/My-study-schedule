package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class NewSubject extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    final public static String NewAsigntura = "com.example.marcelba.mystudyschedule.NewAsigntura";
    final public static String NewAsignturaProfesor = "com.example.marcelba.mystudyschedule.NewAsignturaProfesor";
    final public static String NewAsignturaColor = "com.example.marcelba.mystudyschedule.NewAsignturaColor";
    final public static String NewAsignturaDia = "com.example.marcelba.mystudyschedule.NewAsignturaDia";
    final public static String NewAsignturaHoraIni = "com.example.marcelba.mystudyschedule.NewAsignturaHoraIni";
    final public static String NewAsignturaHoraFin = "com.example.marcelba.mystudyschedule.NewAsignturaHoraFin";

    private Spinner spDia01;
    private Spinner spHoraInicio01;
    private Spinner spMinutosInicio01;
    private Spinner spHoraFinal01;
    private Spinner spMinutosFinal01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_asignatura);


        this.spDia01 = (Spinner) findViewById(R.id.SpDia01);
        this.spHoraInicio01 = (Spinner) findViewById(R.id.SpHoraInicio01);
        this.spMinutosInicio01 = (Spinner) findViewById(R.id.SpMinutosInicio01);
        this.spHoraFinal01 = (Spinner) findViewById(R.id.SpHoraFinal01);
        this.spMinutosFinal01 = (Spinner) findViewById(R.id.SpMinutosFinal01);

        loadSpinnerDias();
        loadSpinnerHoras();
        loadSpinnerMinutos();


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
        this.spDia01.setAdapter(adapter);
    }

    private void loadSpinnerHoras() {

        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Horas, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.spHoraInicio01.setAdapter(adapter);
        this.spHoraFinal01.setAdapter(adapter);
    }

    private void loadSpinnerMinutos() {

        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Minutos, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.spMinutosInicio01.setAdapter(adapter);
        this.spMinutosFinal01.setAdapter(adapter);
    }


    /**
     * Save New Subject.*
     */
    public void saveNewSubject(View newAsignatura) {

        EditText Asignatura = (EditText) findViewById(R.id.EtAsignatura);
        String AsignturaNombre = Asignatura.getText().toString();

        EditText Profesor = (EditText) findViewById(R.id.EtProfesor);
        String AsignaturaProfesor = Profesor.getText().toString();

        Spinner Dia = (Spinner) findViewById(R.id.SpDia01);
        String AsignaturaDia = Dia.getSelectedItem().toString();


        Spinner HoraInicio = (Spinner) findViewById(R.id.SpHoraInicio01);
        int AsignaturaHoraInicio = Integer.parseInt(HoraInicio.getSelectedItem().toString());

        Spinner MinutosInicio = (Spinner) findViewById(R.id.SpMinutosInicio01);
        int AsignaturaMinuotsInicio = Integer.parseInt(MinutosInicio.getSelectedItem().toString());


        Spinner HoraFinal = (Spinner) findViewById(R.id.SpHoraFinal01);
        int AsignaturaHoraFinal = Integer.parseInt(HoraFinal.getSelectedItem().toString());

        Spinner MinutosFinal = (Spinner) findViewById(R.id.SpMinutosFinal01);
        int AsignaturaMinuotsFinal = Integer.parseInt(MinutosFinal.getSelectedItem().toString());

        //Log.i("Asignatura",AsignturaNombre);
        //Log.i("Profesor",AsignaturaProfesor);
        //Log.i("Dia",AsignaturaDia);
        //Log.i("Hora Inicio",AsignaturaHoraInicio +":"+AsignaturaMinuotsInicio );
        //Log.i("Hora Final",AsignaturaHoraFinal +":"+AsignaturaMinuotsFinal );

        Inicio.cal.IntroduceNewSubject(this, Utils.Month(AsignaturaDia), Utils.dayOfTheMonth(AsignaturaDia), AsignaturaHoraInicio, AsignaturaMinuotsInicio, AsignaturaHoraFinal, AsignaturaMinuotsFinal, AsignturaNombre, "Profesor: " + AsignaturaProfesor);

        /** Guardamos la assignatura en una mini BD para el spinner **/

        Inicio.db.AddSubject(AsignturaNombre);


        Intent Subjects = new Intent(this, Subjects.class);
        startActivity(Subjects);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
