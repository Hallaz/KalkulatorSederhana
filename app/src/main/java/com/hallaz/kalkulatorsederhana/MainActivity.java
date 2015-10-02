package com.hallaz.kalkulatorsederhana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //varibale - variable view
    private TextView tv_hasil;
    private Spinner spinMode;
    private EditText ed_angka_1, ed_angka_2;
    private Button hitung;
    //variable array text mode untuk spinner
    private final String[] text_mode = new String[]{"Penambahan", "Pengurangan", "Perkalian", "Pembagian"};
    //varibel posisi mode terpilih pada spinner
    private int spinner_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set View ke Activity dengan parameter id resource layout
        setContentView(R.layout.activity_main);
        // inisialisasi variable View dengan paramater id resource view di xml
        tv_hasil = (TextView) findViewById(R.id.textViewHasil);
        spinMode = (Spinner) findViewById(R.id.spinnerMode);
        ed_angka_1 = (EditText) findViewById(R.id.editTextAngka1);
        ed_angka_2 = (EditText) findViewById(R.id.editTextAngka2);
        hitung = (Button) findViewById(R.id.buttonHitung);
        //Adapter View Controller untuk spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_layout, text_mode);
        //set adapter ke spinner
        spinMode.setAdapter(spinnerAdapter);
        //set spinner dengan listener OnItemSelectedListener()
        spinMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //method penangkap action jika ada item yg ter-select di spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //set listener button dengan listener OnClickListener
        hitung.setOnClickListener(new View.OnClickListener() {
            //method penangkap action click pada listener.
            @Override
            public void onClick(View v) {
                //panggil method fungsi hitung ketika ada action hitung dari user
                hitung();
            }
        });
    }

    //method fungsi hitung
    private void hitung() {
        //try-cath untuk proses converting / parsing text to double
        try {
            // variable angka 1 = Parsing/convert text dari EditText ke double
            double angka_1 = Double.parseDouble(ed_angka_1.getText().toString());
            double angka_2 = Double.parseDouble(ed_angka_2.getText().toString());
            double hasil = 0;
            //swich posisi mode spinner mana yang dipakai
            switch (spinner_position) {
                case 0:
                    hasil = angka_1 + angka_2;
                    break;
                case 1:
                    hasil = angka_1 - angka_2;
                    break;
                case 2:
                    hasil = angka_1 * angka_2;
                    break;
                case 3:
                    hasil = angka_1 / angka_2;
                    break;
            }
            //set hasil perhitungan ke TextView
            tv_hasil.setText(String.valueOf(hasil));
        } catch (Exception e) {
            //jika ada kesalahan converting berarti inputan user salah, notif
            Toast.makeText(getApplicationContext(), "Masukan salah !!!", Toast.LENGTH_LONG).show();
        }
    }
}