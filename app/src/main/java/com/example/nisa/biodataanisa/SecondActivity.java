package com.example.nisa.biodataanisa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nisa.biodataanisa.utility.Constant;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    TextView nameTV, emailTV, alamatTV, tempatTV, tanggalTV, jenisTV, hobiTV, pendidikanTV, hobilainTV;
    Button backBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameTV = findViewById(R.id.nama_textview);
        emailTV = findViewById(R.id.email_textview);
        alamatTV = findViewById(R.id.alamat_textview);
        tempatTV = findViewById(R.id.tempatlahir_textview);
        tanggalTV = findViewById(R.id.date_textview);
        jenisTV = findViewById(R.id.jeniskelamin_textview);
        hobiTV = findViewById(R.id.hobi_textview);
        hobilainTV = findViewById(R.id.hobilainnya_textview);
        pendidikanTV = findViewById(R.id.pendidikan_textview);

        backBTN = findViewById(R.id.back_button);
        backBTN.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            nameTV.setText(bundle.getString(Constant.KEY_RESULT_NAMA, ""));
            emailTV.setText(bundle.getString(Constant.KEY_RESULT_EMAIL, ""));
            alamatTV.setText(bundle.getString(Constant.KEY_RESULT_ALAMAT, ""));
            tempatTV.setText(bundle.getString(Constant.KEY_RESULT_TEMPATLAHIR, ""));
            tanggalTV.setText(bundle.getString(Constant.KEY_RESULT_TANGGALAHIR, ""));
            jenisTV.setText(bundle.getString(Constant.KEY_RESULT_JENISKELAMIN, ""));
            hobiTV.setText(bundle.getString(Constant.KEY_RESULT_HOBI, ""));
            hobilainTV.setText(bundle.getString(Constant.KEY_RESULT_HOBI_LAIN, ""));
            pendidikanTV.setText(bundle.getString(Constant.KEY_RESULT_PENDIDIKAN, ""));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm").setCancelable(false).setMessage("Are u sure?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                        finish();
                    }
                })
                .setNegativeButton("NO", null).show();
        return super.onOptionsItemSelected(item);
    }

}
