package com.example.marcelba.mystudyschedule;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class NewSubject extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spDia01;
    private Spinner spHoraInicio01;
    private Spinner spMinutosInicio01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_asignatura);


        this.spDia01 = (Spinner) findViewById(R.id.spDia01);
        this.spHoraInicio01 = (Spinner) findViewById(R.id.spHoraInicio01);
        this.spMinutosInicio01 = (Spinner) findViewById(R.id.spMinutosInicio01);

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
        private void loadSpinnerDias()  {

            // Create an ArrayAdapter using the string array and a default spinner
            // layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Dias, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
           this.spDia01.setAdapter(adapter);
        }
        private void loadSpinnerHoras()  {

            // Create an ArrayAdapter using the string array and a default spinner
            // layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Horas, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            this.spHoraInicio01.setAdapter(adapter);
        }
        private void loadSpinnerMinutos()  {

            // Create an ArrayAdapter using the string array and a default spinner
            // layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Minutos, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            this.spMinutosInicio01.setAdapter(adapter);
        }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
