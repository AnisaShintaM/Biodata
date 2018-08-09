package com.example.nisa.biodataanisa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nisa.biodataanisa.utility.Constant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    EditText namaET, emailET, alamatET, tempatET, lainnyaET;

    TextView birthdayTV;
    ImageView dateIV;
    DatePickerDialog datePickerDialog;

    RadioGroup jenisRG;


    String selectedGender;
    RadioButton perempuanRB, lakiRB;
    CheckBox olahragaCB, seniCB, lainnyaCB;
    String hobis = "";

    Spinner pendidikanSpinner;
    String selectedPendidikan;
    String namaString;
    String emailString;
    String alamatString;
    String tempatString;
    String tanggalString;

    Button kirimBTN;

    ArrayList<String> listHobi = new ArrayList<String>();
    ArrayList<String> listPendi = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaET = findViewById(R.id.nama_edittext);
        emailET = findViewById(R.id.email_edittext);
        alamatET = findViewById(R.id.alamat_edittext);
        tempatET = findViewById(R.id.tempatlahir_edittext);
        lainnyaET = findViewById(R.id.hobilain_edittext);


        birthdayTV = findViewById(R.id.date_textview);
        dateIV = findViewById(R.id.date_imageview);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");


        jenisRG = findViewById(R.id.radiogroupjeniskelamin);
        jenisRG.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.perempuan_radiobutton)).setChecked(true);


        olahragaCB = findViewById(R.id.olahraga_checkbox);
        seniCB = findViewById(R.id.seni_checkbox);
        lainnyaCB = findViewById(R.id.lain_checkbox);


        pendidikanSpinner = findViewById(R.id.pendidikan_spinner);
        pendidikanSpinner.setOnItemSelectedListener(this);

        kirimBTN = findViewById(R.id.kirim_button);

        olahragaCB.setOnCheckedChangeListener(this);
        seniCB.setOnCheckedChangeListener(this);
        lainnyaCB.setOnCheckedChangeListener(this);
        dateIV.setOnClickListener(this);
        kirimBTN.setOnClickListener(this);

        //list dinamis
        listPendi.add("SD");
        listPendi.add("SMP");
        listPendi.add("SMA");
        listPendi.add("S1");
        listPendi.add("S2");
        listPendi.add("S3");
        ArrayAdapter<String> pendidikanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listPendi);

        pendidikanSpinner.setAdapter(pendidikanAdapter);
        //end


        Calendar now = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(
                MainActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.kirim_button:
                namaString = namaET.getText().toString();
                emailString = emailET.getText().toString();
                alamatString = alamatET.getText().toString();
                tempatString = tempatET.getText().toString();
                tanggalString = birthdayTV.getText().toString();
                Pattern pattern1 = Pattern.compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");

                Matcher matcher1 = pattern1.matcher(emailString);
                if (TextUtils.isEmpty(namaString)) {
                    Toast.makeText(getApplicationContext(), "Mohon Isikan Nama Anda", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(emailString) || !matcher1.matches()) {
                    Toast.makeText(getApplicationContext(), "Mohon Isi Email Anda / Isikan Email Dengan Benar", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(alamatString)) {
                    Toast.makeText(getApplicationContext(), "Mohon Isi Alamat Anda", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(tempatString)) {
                    Toast.makeText(getApplicationContext(), "Mohon Isi Tempat Lahir Anda", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(tanggalString)) {
                    Toast.makeText(getApplicationContext(), "Mohon Isi Tanggal Lahir Anda", Toast.LENGTH_SHORT).show();
                } else {
                    hobis = "";
                    for (int i = 0; i < listHobi.size(); i++) {
                        hobis = hobis + " " + listHobi.get(i);
                    }
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Confirm").setCancelable(false).setMessage("Are u sure?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    String nama = namaET.getText().toString();
                                    String email = emailET.getText().toString();
                                    String alamat = alamatET.getText().toString();
                                    String tempat = tempatET.getText().toString();

                                    String tanggal = birthdayTV.getText().toString();
                                    String lainnya = lainnyaET.getText().toString();
                                    String study = "" + selectedPendidikan;

                                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                    intent.putExtra(Constant.KEY_RESULT_NAMA, nama);
                                    intent.putExtra(Constant.KEY_RESULT_EMAIL, email);
                                    intent.putExtra(Constant.KEY_RESULT_ALAMAT, alamat);
                                    intent.putExtra(Constant.KEY_RESULT_TEMPATLAHIR, tempat);
                                    intent.putExtra(Constant.KEY_RESULT_TANGGALAHIR, tanggal);
                                    intent.putExtra(Constant.KEY_RESULT_JENISKELAMIN, selectedGender);
                                    intent.putExtra(Constant.KEY_RESULT_HOBI, hobis);
                                    intent.putExtra(Constant.KEY_RESULT_HOBI_LAIN, lainnya);
                                    intent.putExtra(Constant.KEY_RESULT_PENDIDIKAN, study);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("NO", null).show();

                }
                break;

            case R.id.date_imageview:
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.perempuan_radiobutton:
                selectedGender = "Perempuan";
                break;
            case R.id.laki_radiobutton:
                selectedGender = "Laki-Laki";
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.olahraga_checkbox:
                if (isChecked) {
                    listHobi.add("Olahraga");
                } else {
                    listHobi.remove("Olahraga");
                }
                break;

            case R.id.seni_checkbox:
                if (isChecked) {
                    listHobi.add("Seni");

                } else {
                    listHobi.remove("Seni");
                }
                break;
            case R.id.lain_checkbox:
                if (isChecked) {
                    listHobi.add("Lainnya");
                    lainnyaET.setEnabled(true);
                } else {
                    listHobi.remove("Lainnya");
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPendidikan = pendidikanSpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + " " + (month + 1) + " " + year;
        birthdayTV.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.input_main_menu:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //tambahan
//    public interface OnSubmitButtonListener {
//        void onSubmitButton(String name, String address, String birthday);
//    }
//
//    public static MainActivity newInstance(String name, String address, String birthday){
//        MainActivity mainActivity = new MainActivity();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("nama", name);
//        mainActivity.setArguments(bundle);
//
//        return mainActivity;
//
//    }


}
