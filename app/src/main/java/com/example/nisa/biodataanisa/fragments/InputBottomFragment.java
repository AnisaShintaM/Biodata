//package com.example.nisa.biodataanisa.fragments;
//
//
//import android.app.DatePickerDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.BottomSheetDialogFragment;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AlertDialog;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.nisa.biodataanisa.MainActivity;
//import com.example.nisa.biodataanisa.R;
//import com.example.nisa.biodataanisa.SecondActivity;
//import com.example.nisa.biodataanisa.utility.Constant;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class InputBottomFragment extends BottomSheetDialogFragment implements  View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//    }
//
//    public interface OnSubmitButtonListener {
//        void onSubmitButton(String name, String address, String birthday);
//    }
//
//    public InputBottomFragment() {
//        // Required empty public constructor
//    }
//
//    public static InputBottomFragment newInstance(String name, String address, String birthday){
//        InputBottomFragment fragment = new InputBottomFragment();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("nama", name);
//        bundle.putString("address", address);
//        bundle.putString("birthday", birthday);
//
//        fragment.setArguments(bundle);
//
//        return fragment;
//
//    }
//
//    EditText namaET, emailET, alamatET, tempatET;
//
//    TextView birthdayTV;
//    ImageView dateIV;
//    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog;
//
//    RadioGroup jenisRG;
//    String selectedGender;
//    RadioButton perempuanRB, lakiRB;
//    CheckBox olahragaCB, seniCB, lainnyaCB;
//    String hobis = "";
//
//    Spinner pendidikanSpinner;
//    String selectedPendidikan;
//    String namaString;
//    String emailString;
//    String alamatString;
//    String tempatString;
//
//    Button kirimBTN;
//
//    ArrayList<String> listHobi = new ArrayList<String>();
//    ArrayList<String> listPendi = new ArrayList<>();
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View view = inflater.inflate(R.layout.fragment_input_bottom, container, false);
//
//        namaET = view.findViewById(R.id.nama_edittext);
//        emailET = view.findViewById(R.id.email_edittext);
//        alamatET = view.findViewById(R.id.alamat_edittext);
//        tempatET = view.findViewById(R.id.tempatlahir_edittext);
//
//        birthdayTV = view.findViewById(R.id.date_textview);
//        dateIV = view.findViewById(R.id.date_imageview);
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
//
//
//
//        jenisRG = view.findViewById(R.id.radiogroupjeniskelamin);
//        jenisRG.setOnCheckedChangeListener(this);
//        ((RadioButton) view.findViewById(R.id.perempuan_radiobutton)).setChecked(true);
//
//        olahragaCB = view.findViewById(R.id.olahraga_checkbox);
//        seniCB = view.findViewById(R.id.seni_checkbox);
//        lainnyaCB = view.findViewById(R.id.lain_checkbox);
//
//
//        pendidikanSpinner = view.findViewById(R.id.pendidikan_spinner);
//        pendidikanSpinner.setOnItemSelectedListener(this);
//
//        kirimBTN = view.findViewById(R.id.kirim_button);
//
//        olahragaCB.setOnCheckedChangeListener(this);
//        seniCB.setOnCheckedChangeListener(this);
//        lainnyaCB.setOnCheckedChangeListener(this);
//        kirimBTN.setOnClickListener(this);
//
//        //list dinamis
//        listPendi.add("SD");
//        listPendi.add("SMP");
//        listPendi.add("SMA");
//        listPendi.add("S1");
//        listPendi.add("S2");
//        listPendi.add("S3");
//        ArrayAdapter<String> pendidikanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listPendi);
//
//        pendidikanSpinner.setAdapter(pendidikanAdapter);
//
//
//        return view;
//    }
//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case R.id.kirim_button:
//                namaString = namaET.getText().toString();
//                emailString = emailET.getText().toString();
//                alamatString = alamatET.getText().toString();
//                tempatString = tempatET.getText().toString();
//
//                if (TextUtils.isEmpty(namaString)) {
//                    Toast.makeText(getApplicationContext(), "Mohon Isikan Nama Anda", Toast.LENGTH_SHORT).show();
//
//                }
//                else if(TextUtils.isEmpty(emailString)){
//                    Toast.makeText(getApplicationContext(), "Mohon Isi Email Anda", Toast.LENGTH_SHORT).show();
//                }
//                else if(TextUtils.isEmpty(alamatString))
//                {
//                    Toast.makeText(getApplicationContext(), "Mohon Isi Alamat Anda", Toast.LENGTH_SHORT).show();
//                }
//                else if(TextUtils.isEmpty(tempatString))
//                {
//                    Toast.makeText(getApplicationContext(), "Mohon Isi Tempat Lahir Anda", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    hobis = "";
//                    for (int i = 0; i < listHobi.size(); i++) {
//                        hobis = hobis + " " + listHobi.get(i);
//                    }
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//                    alertDialog.setTitle("Confirm").setCancelable(false).setMessage("Are u sure?")
//                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
////                                    String result = "Welcome " + namaString + " " + "\nGender :" + " " + selectedGender + " " +
////                                            "\nKesukaan :" + " " + hobis + " " + "\nAsal :" + " " + selectedPendidikan;
//
//                                    String nama = namaET.getText().toString();
//                                    String email = emailET.getText().toString();
//                                    String alamat = alamatET.getText().toString();
//                                    String tempat = tempatET.getText().toString();
//
//                                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                                    intent.putExtra(Constant.KEY_RESULT_NAMA,  nama);
//                                    intent.putExtra(Constant.KEY_RESULT_EMAIL,  email);
//                                    intent.putExtra(Constant.KEY_RESULT_ALAMAT,  alamat);
//                                    intent.putExtra(Constant.KEY_RESULT_TEMPATLAHIR,  tempat);
//                                    startActivity(intent);
//                                }
//                            })
//                            .setNegativeButton("NO", null).show();
//
//                }
//                break;
//
//            case R.id.date_imageview:
//                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
//                break;
//        }
//
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (checkedId) {
//            case R.id.perempuan_radiobutton:
//                selectedGender = "Perempuan";
//                break;
//            case R.id.laki_radiobutton:
//                selectedGender = "Laki-Laki";
//                break;
//        }
//
//    }
//
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        int id = buttonView.getId();
//        switch (id) {
//            case R.id.olahraga_checkbox:
//                if (isChecked) {
//                    listHobi.add("Olahraga");
//                } else {
//                    listHobi.remove("Olahraga");
//                }
//                break;
//
//            case R.id.seni_checkbox:
//                if (isChecked) {
//                    listHobi.add("Seni");
//                } else {
//
//                    listHobi.remove("Seni");
//                }
//                break;
//            case R.id.lain_checkbox:
//                if (isChecked) {
//                    listHobi.add("Lainnya");
//                } else {
//                    listHobi.remove("Lainnya");
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        selectedPendidikan = pendidikanSpinner.getSelectedItem().toString();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//
//}
