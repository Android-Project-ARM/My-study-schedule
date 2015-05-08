package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;


public class NewTask extends ActionBarActivity {

    private Spinner spAsigTarea;

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /** Save New Task.**/
    public void saveNewTask(View v){

        Intent newView = new Intent(this, Tasks.class); //preparamos la view que queremos lanzar

        EditText NuevaTarea = (EditText) findViewById(R.id.EtNuevaTarea);
        String addNuevaTarea = NuevaTarea.getText().toString();

        EditText DescTarea = (EditText) findViewById(R.id.EtDescTarea);
        String addDescTarea = DescTarea.getText().toString();

        EditText FechaTarea = (EditText) findViewById(R.id.EtFechaTarea);
        String addFechaTarea = FechaTarea.getText().toString();

        Spinner AsigTarea = (Spinner) findViewById(R.id.SnAsignatura);
        Integer addAsigTarea = AsigTarea.getSelectedItemPosition();


        RatingBar RatTarea = (RatingBar) findViewById(R.id.RatingTarea);
        Float addRatTarea = RatTarea.getRating();

        Inicio.db.AddTask(addNuevaTarea,addDescTarea,addFechaTarea,addRatTarea,addAsigTarea );
        startActivity(newView);

    }


    private void loadSpinnerAsignaturas()  {

        Cursor c = Inicio.db.ReadSubject();
        String[] fromColumns = {Inicio.db.DB_SUBJECTS_COL_NAME};
        int test = c.getCount();
        int [] toView = {R.id.SnAsignatura};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.activity_nueva_tarea,
                c,
                fromColumns,
                toView,
                0
        );
        Spinner spAsignaturas = (Spinner) findViewById(R.id.SnAsignatura);
        spAsignaturas.setAdapter(adapter);

    }


}
