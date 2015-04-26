package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class Subjects extends ActionBarActivity {

    ListView subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);


        Cursor c = Inicio.cal.GetSubjects(this);
        NoIdCursorWrapper nc = new NoIdCursorWrapper(c, CalendarContract.Instances.EVENT_ID);

         String[] fromColumns = {CalendarContract.Instances.TITLE,CalendarContract.Instances.START_DAY};
         int[] toViews = {R.id.SubjectTitle,R.id.SubjectDay};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.subjects_row,nc,fromColumns,toViews,0);
        subjectList = (ListView)findViewById(R.id.listSubjectView);
        subjectList.setAdapter(adapter);
        //subjectList.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asignaturas, menu);
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

    public void gotoNuevaAsignatura(View v){
        Intent newView = new Intent(this, NewSubject.class); //preparamos la view que queremos lanzar
        startActivity(newView );


    }

}
