package com.example.umiacs.hellohydration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    SharedPreferences settings;
    String name, age, sex, ft, in, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //set up entry fields
        final EditText nameField = (EditText) findViewById(R.id.nameField);
        final EditText ageField = (EditText) findViewById(R.id.ageField);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final EditText ftField = (EditText) findViewById(R.id.ftField);
        final EditText inField = (EditText) findViewById(R.id.inField);
        final EditText weightField = (EditText) findViewById(R.id.weightField);

        //set up save button
        ImageButton saveButton = (ImageButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = settings.edit();

                //get input values
                name = nameField.getText().toString();
                age = ageField.getText().toString();
                RadioButton checked = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                if(checked != null) {
                    sex = checked.getText().toString();
                } else {
                    sex = "";
                }
                ft = ftField.getText().toString();
                in = inField.getText().toString();
                weight = weightField.getText().toString();

                if(name.matches("") || age.matches("") || sex.matches("") || ft.matches("") || in.matches("") || weight.matches("")) {
                    Toast.makeText(getApplicationContext(), "Fill out all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    //store inputs
                    editor.putString("name", name);
                    editor.putString("age", age);
                    editor.putString("sex", sex);
                    editor.putString("ft", ft);
                    editor.putString("in", in);
                    editor.putString("weight", weight);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Settings saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //load saved entries, set empty if not present
        settings = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        name = settings.getString("name","");
        age = settings.getString("age","");
        sex = settings.getString("sex","");
        ft = settings.getString("ft","");
        in = settings.getString("in","");
        weight = settings.getString("weight","");

        //put saved entries into entry fields
        nameField.setText(name);
        ageField.setText(age);
        RadioButton radio;
        if(sex.equals("Female")) {
            radio = (RadioButton) findViewById(R.id.femaleButton);
            radio.setChecked(true);
        } else if(sex.equals("Male")){
            radio = (RadioButton) findViewById(R.id.maleButton);
            radio.setChecked(true);
        }
        ftField.setText(ft);
        inField.setText(in);
        weightField.setText(weight);
    }

    //sets up toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //handles selected toolbar menu option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case(R.id.action_bargraph):
                intent = new Intent(Settings.this, BarGraph.class);
                startActivity(intent);
                break;
            case(R.id.action_exercisetracker):
                intent = new Intent(Settings.this, ExerciseTracker.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
