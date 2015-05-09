package com.example.marcelba.mystudyschedule;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


public class Inicio extends ActionBarActivity implements Button.OnClickListener {

    public static CalendarController cal;
    public static DBProxy db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Button horaryButton = ((Button) this.findViewById(R.id.horaryButton));
        Button subjectsButton = ((Button) this.findViewById(R.id.subjectsButton));
        Button tasksButton = ((Button) this.findViewById(R.id.tasksButton));
        Button evaluationButton = ((Button) this.findViewById(R.id.evaluationButton));
        horaryButton.setOnClickListener(this);
        subjectsButton.setOnClickListener(this);
        tasksButton.setOnClickListener(this);
        evaluationButton.setOnClickListener(this);
        // MenuItem item= (MenuItem) findViewById(R.id.action_settings);
        //item.setVisible(false);

        db = new DBProxy(this);

        cal = new CalendarController(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
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
            //int thingy = Utils.Month("Viernes");
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.horaryButton:
                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder, Calendar.getInstance().getTimeInMillis());
                intent = new Intent(Intent.ACTION_VIEW)
                        .setData(builder.build());
                startActivity(intent);
                return;
            case R.id.subjectsButton:
                intent = new Intent(this, Subjects.class);
                break;
            case R.id.tasksButton:
                if (cal.GetSubjects(this).getCount() == 0) {
                    Utils.ShowDialog(this,R.string.no_subjects_yet);
                    return;
                }
                intent = new Intent(this, Tasks.class);
                break;
            case R.id.evaluationButton:
                //Utils.ShowDialog(this,R.string.in_development);
                //return;
                intent = new Intent(this, Evaluation.class);
                break;
            default:
                intent = new Intent(this, Inicio.class);
                break;
        }
        startActivity(intent);

    }

}
