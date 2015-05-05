package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class Subjects extends ActionBarActivity implements AdapterView.OnItemClickListener{

    ListView subjectList;
    public static DBProxy db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);


        Cursor c = Inicio.cal.GetSubjects(this);
        NoIdCursorWrapper nc = new NoIdCursorWrapper(c, CalendarContract.Instances.EVENT_ID);

        String[] columns = new String[] { "_id", "title", "day" };
        String[] fromColumns = new String[] {"title", "day" };
        int[] toViews = {R.id.SubjectTitle,R.id.SubjectDay};

        MatrixCursor  processedCursor = new MatrixCursor(columns);

        while(c.moveToNext()){
            processedCursor.addRow(new Object[]{c.getLong(CalendarController.PROJECTION_IDI_INDEX),c.getString(CalendarController.PROJECTION_TITLE_INDEX),Utils.GetWeekDay(c.getLong(CalendarController.PROJECTION_BEGIN_INDEX))});
          }

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.subjects_row,processedCursor,fromColumns,toViews,0);
        subjectList = (ListView)findViewById(R.id.listSubjectView);
        subjectList.setAdapter(adapter);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
