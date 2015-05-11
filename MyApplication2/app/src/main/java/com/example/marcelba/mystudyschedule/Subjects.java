package com.example.marcelba.mystudyschedule;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class Subjects extends ActionBarActivity implements AdapterView.OnItemLongClickListener {

    private ListView subjectList;
    private long lastId;
    private View lastView;
    private SimpleCursorAdapter  subjectsAdapter;
    private CalendarController cal= Inicio.cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);

        GenerateListView();

        // TextView et;
        //for (int i = 0; i < subjectList.getCount(); i++) {
        // et = (TextView) subjectList.getChildAt(i).findViewById(R.id.SubjectDay);
        //  if (et!=null) {
        //  int number = Integer.parseInt(et.getText().toString());
        // et.setText(Utils.GetWeekDay(number));
        //  }
        //}

        //TextView tempText = (TextView)findViewById(R.id.SubjectDay);
        //int number = Integer.parseInt(tempText.getText().toString());
        //tempText.setText(Utils.GetWeekDay(number));
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
            Intent newView = new Intent(this, NewSubject.class); //preparamos la view que queremos lanzar
            startActivity(newView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        lastId = id;
        lastView=view;
        Utils.ShowDialog(this,R.string.deleteSubject,true);
        return false;
    }

    private void GenerateListView()
    {
        Cursor c = cal.GetSubjects(this);

        String[] columns = new String[]{"_id", "title", "day"};
        String[] fromColumns = new String[]{"title", "day"};
        int[] toViews = {R.id.SubjectTitle, R.id.SubjectDay};

        MatrixCursor processedCursor = new MatrixCursor(columns);

        while (c.moveToNext()) {
            processedCursor.addRow(new Object[]{c.getLong(CalendarController.PROJECTION_IDI_INDEX), c.getString(CalendarController.PROJECTION_TITLE_INDEX), Utils.GetWeekDay(c.getLong(CalendarController.PROJECTION_BEGIN_INDEX))});
        }
        subjectsAdapter = new SimpleCursorAdapter(this, R.layout.subjects_row, processedCursor, fromColumns, toViews, 0);
        subjectList = (ListView) findViewById(R.id.listSubjectView);
        subjectList.setAdapter(subjectsAdapter);
        subjectList.setOnItemLongClickListener(this);
    }


    public void DeleteCurrentSubject(){
        cal.CancelSubject(this, lastId);
        Inicio.db.DeleteSubjectTasks(lastId);
        GenerateListView();
    }
}
