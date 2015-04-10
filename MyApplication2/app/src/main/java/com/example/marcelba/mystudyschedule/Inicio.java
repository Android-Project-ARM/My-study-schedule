package com.example.marcelba.mystudyschedule;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Inicio extends ActionBarActivity implements Button.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Button horaryButton = ((Button)this.findViewById(R.id.horaryButton));
        Button subjectsButton = ((Button)this.findViewById(R.id.subjectsButton));
        Button tasksButton = ((Button)this.findViewById(R.id.tasksButton));
        Button evaluationButton = ((Button)this.findViewById(R.id.evaluationButton));
        horaryButton.setOnClickListener(this);
        subjectsButton.setOnClickListener(this);
        tasksButton.setOnClickListener(this);
        evaluationButton.setOnClickListener(this);
        // MenuItem item= (MenuItem) findViewById(R.id.action_settings);
        //item.setVisible(false);

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
            Intent intent = new Intent(this, Ajustes.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
       Intent intent=null;
        switch(v.getId()){
            case R.id.horaryButton:
                intent = new Intent(this, Horario.class);
                break;
            case R.id.subjectsButton:
                intent = new Intent(this, Asignaturas.class);
                break;
            case R.id.tasksButton:
                intent = new Intent(this, Tareas.class);
                break;
            case R.id.evaluationButton:
                intent = new Intent(this, Evaluacion.class);
                break;
              default:
                intent = new Intent(this, Inicio.class);
                break;
            }
        startActivity(intent);

         }

}
