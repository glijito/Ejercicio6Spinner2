package com.example.prpas.ejercicio6spinner2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Button jbn1;
    private EditText jet1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jbn1 = (Button) findViewById(R.id.xbn1);
        jet1 = (EditText) findViewById(R.id.xet1);
        spinner = (Spinner) findViewById(R.id.xsp1);
        cargaSpinner();
        jbn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = jet1.getText().toString();
                if (s.trim().length() > 0) {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.insertLabel(s);
                    jet1.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(jet1.getWindowToken(), 0);
                    cargaSpinner();
                } else {
                    Toast.makeText(getApplicationContext(), "Escribir elemento", Toast.LENGTH_SHORT).show();
                }
        }
    });
}

private void cargaSpinner() {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<String> l = db.getAllLabels();
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, l);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = spinner.getItemAtPosition(position).toString();
        Toast.makeText(spinner.getContext(), "Selección: " + s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
