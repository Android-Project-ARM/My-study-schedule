package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Tasks extends ActionBarActivity  {


    public static DBProxy db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        /**db = new DBProxy(this);

        Cursor c = db.ReadTask();
        String[] fromColumns = {db.DB_TASK_COL_NAME};
        int [] toView = {R.id.ElementTarea};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.activity_detalle_tarea,
                c,
                fromColumns,
                toView,
                0
        );
        ListView list = (ListView) findViewById(R.id.listElementsTask);
        list.setAdapter(adapter);**/



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tareas, menu);
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

    public void gotoNuevaTarea(View v){
        Intent newView = new Intent(this, NewTask.class); //preparamos la view que queremos lanzar
        startActivity(newView);


    }


}
