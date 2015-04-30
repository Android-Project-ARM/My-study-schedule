package com.example.marcelba.mystudyschedule;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;


public class NewTask extends ActionBarActivity {

    public Integer idTarea;
    public boolean update = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);
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


     @Override


    protected void onPause() {
    super.onPause();




    EditText NuevaTarea = (EditText) findViewById(R.id.EtNuevaTarea);
    String addNuevaTarea = NuevaTarea.getText().toString();



    EditText FechaTarea = (EditText)findViewById(R.id.EtFechaTarea);
    String addFechaTarea = FechaTarea.getText().toString();

    RatingBar RatTarea = (RatingBar) findViewById(R.id.RatingTarea);
    Float addRatTarea = RatTarea.getRating();

    EditText DescTarea = (EditText)findViewById(R.id.EtDescTarea);
    String addDescTarea = DescTarea.getText().toString();



    Tasks.db.AddTask(addNuevaTarea, addFechaTarea, addRatTarea, addDescTarea );


    }


}
