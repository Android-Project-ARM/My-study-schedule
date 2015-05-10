package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class NewTask extends ActionBarActivity {

    List<Integer> subjectsId = new ArrayList<Integer>();
    private EditText NuevaTarea;
    private EditText DescTarea;
    private CheckBox DoneTarea;
    private EditText FechaTarea;
    private Spinner AsigTarea;
    private RatingBar RatTarea;
    private DBProxy db = Inicio.db;
    private Long idTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        NuevaTarea = (EditText) findViewById(R.id.EtNuevaTarea);
        DescTarea = (EditText) findViewById(R.id.EtDescTarea);
        DoneTarea = (CheckBox) findViewById(R.id.TvDone);
        FechaTarea = (EditText) findViewById(R.id.EtFechaTarea);
        AsigTarea = (Spinner) findViewById(R.id.SnAsignatura);
        RatTarea = (RatingBar) findViewById(R.id.RatingTarea);

        AsigTarea = (Spinner) findViewById(R.id.SnAsignatura);
        loadSpinnerAsignaturas();

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            idTask =  extras.getLong("id",20);
            Cursor c = db.ReadTask(idTask);
            while (c.moveToNext()) {
                NuevaTarea.setText(c.getString(c.getColumnIndex(db.DB_TASK_COL_NAME)));
                DescTarea.setText(c.getString(c.getColumnIndex(db.DB_TASK_COL_DESC)));
                DoneTarea.setChecked(false);
                int index = subjectsId.indexOf(c.getInt(c.getColumnIndex(db.DB_TASK_COL_SUBJECT_ID)));
                AsigTarea.setSelection(index);
                FechaTarea.setText(c.getString(c.getColumnIndex(db.DB_TASK_COL_ENDDATE)));
                RatTarea.setRating(c.getFloat(c.getColumnIndex(db.DB_TASK_COL_PRIORITY)));

            }
        }

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
            saveTask(item.getActionView());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Save New Task.*
     */
    private void saveTask(View v) {

        Intent newView = new Intent(this, Tasks.class); //preparamos la view que queremos lanzar

        String addNuevaTarea = NuevaTarea.getText().toString();

        String addDescTarea = DescTarea.getText().toString();

        Integer addTareaEcha = DoneTarea.isChecked() ? 1 : 0;

        String addFechaTarea = FechaTarea.getText().toString();

        Integer addAsigTarea = subjectsId.get((int) AsigTarea.getSelectedItemId());

        Float addRatTarea = RatTarea.getRating();

        if(idTask != null){
            Inicio.db.UpdateTask(idTask,addNuevaTarea, addDescTarea, addFechaTarea, addRatTarea, addAsigTarea, addTareaEcha);
        }else{
            Inicio.db.AddTask(addNuevaTarea, addDescTarea, addFechaTarea, addRatTarea, addAsigTarea, addTareaEcha);
        }
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

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AsigTarea.setAdapter(spinnerArrayAdapter);

    }


}
