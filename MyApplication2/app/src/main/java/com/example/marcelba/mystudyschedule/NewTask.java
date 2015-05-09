package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class NewTask extends ActionBarActivity {

    private Spinner spAsigTarea;
    List<Integer> subjectsId = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);


        this.spAsigTarea = (Spinner) findViewById(R.id.SnAsignatura);
        loadSpinnerAsignaturas();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_tarea, menu);
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
            saveNewTask(item.getActionView());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Save New Task.*
     */
    private void saveNewTask(View v) {

         Intent newView = new Intent(this, Tasks.class); //preparamos la view que queremos lanzar

        EditText NuevaTarea = (EditText) findViewById(R.id.EtNuevaTarea);
        String addNuevaTarea = NuevaTarea.getText().toString();

        EditText DescTarea = (EditText) findViewById(R.id.EtDescTarea);
        String addDescTarea = DescTarea.getText().toString();

        EditText FechaTarea = (EditText) findViewById(R.id.EtFechaTarea);
        String addFechaTarea = FechaTarea.getText().toString();

        Spinner AsigTarea = (Spinner) findViewById(R.id.SnAsignatura);
        Integer addAsigTarea = subjectsId.get((int) AsigTarea.getSelectedItemId());


        RatingBar RatTarea = (RatingBar) findViewById(R.id.RatingTarea);
        Float addRatTarea = RatTarea.getRating();

        Inicio.db.AddTask(addNuevaTarea, addDescTarea, addFechaTarea, addRatTarea, addAsigTarea);
        startActivity(newView);

    }


    private void loadSpinnerAsignaturas() {

        Cursor c = Inicio.cal.GetSubjects(this);


        ArrayList<String> options=new ArrayList<String>();
        while (c.moveToNext()) {
            //processedCursor.addRow(new Object[]{c.getLong(CalendarController.PROJECTION_IDI_INDEX), c.getString(CalendarController.PROJECTION_TITLE_INDEX), Utils.GetWeekDay(c.getLong(CalendarController.PROJECTION_BEGIN_INDEX))});
            options.add(c.getString(CalendarController.PROJECTION_TITLE_INDEX));
            subjectsId.add((int)c.getLong(CalendarController.PROJECTION_IDI_INDEX));
        }

        Spinner spinner =  (Spinner) findViewById(R.id.SnAsignatura);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

    }


}
