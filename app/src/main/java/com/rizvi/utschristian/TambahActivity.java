package com.rizvi.utschristian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    private EditText etNamaKampus, etKotaKampus, etAlamatKampus;
    private Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNamaKampus = findViewById(R.id.et_namakampus);
        etKotaKampus = findViewById(R.id.et_kotakampus);
        etAlamatKampus = findViewById(R.id.et_alamatkampus);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal, jam, kegiatan;

                NamaKampus = etNamaKampus.getText().toString();
                KotaKampus = etKotaKampus.getText().toString();
                AlamatKampus = etAlamatKampus.getText().toString();

                if(NamaKampus.trim().equals("")){
                    etNamaKampus.setError("Nama Kampus Harus Di isi");
                }
                else if(KotaKampus.trim().equals("")){
                    etKotaKampus.setError("Kota kampus Harus di isi");
                }
                else if(AlamatKampus.trim().equals("")){
                    etAl.setError("Alamat Kampus Harus di isi");
                }
                else{
                    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahActivity.this);
                    long eks = myDB.tambahAgenda(NamaKampus, KotaKampus, AlamatKampus);
                    if(eks == -1){
                        Toast.makeText(TambahActivity.this, "Tambah Data Gagal!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(TambahActivity.this, "Tambah Data Sukses!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}