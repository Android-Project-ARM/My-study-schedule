package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Tasks extends ActionBarActivity  implements AdapterView.OnItemClickListener {


    private ListView noteTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);


        Cursor c = Inicio.db.ReadTasks();
        String[] fromColumns = {Inicio.db.DB_TASK_COL_NAME,Inicio.db.DB_TASK_COL_ENDDATE};
        int[] toView = {R.id.ElementTarea,R.id.ElementTareaFecha};


        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.task_list,
                c,
                fromColumns,
                toView,
                0
        );
        ListView list = (ListView) findViewById(R.id.listTasks);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
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
            Intent newView = new Intent(this, NewTask.class); //preparamos la view que queremos lanzar
            startActivity(newView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, NewTask.class);
        i.putExtra("id", id);
        startActivity(i);
    }
}
