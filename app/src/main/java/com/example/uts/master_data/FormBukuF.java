package com.example.uts.master_data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uts.DBHelper;
import com.example.uts.Global;
import com.example.uts.MainActivity;
import com.example.uts.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormBukuF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormBukuF extends Fragment {
    Button btnSubmitBuku;
    EditText txJudul, txHarga, txDesk;
    Context context;
    TextView formBukuTitle;
    public FormBukuF() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormBukuF.
     */
    // TODO: Rename and change types and number of parameters
    public static FormBukuF newInstance(String param1, String param2) {
        FormBukuF fragment = new FormBukuF();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form_buku, container, false);
        DBHelper db = new DBHelper(this.getActivity());

        btnSubmitBuku = (Button) view.findViewById(R.id.btnSubmitBuku);
        txJudul = (EditText) view.findViewById(R.id.txJudul);
        txHarga = (EditText) view.findViewById(R.id.txHarga);
        txDesk = (EditText) view.findViewById(R.id.txDesk);
        formBukuTitle = (TextView) view.findViewById(R.id.formBukuTitle);

        if(Global.isEditBuku) {
            formBukuTitle.setText("Edit Buku");
            txJudul.setText(Global.bukuEdit.Judul);
            txHarga.setText(Global.bukuEdit.Harga.toString());
            txDesk.setText(Global.bukuEdit.Deskripsi);
            btnSubmitBuku.setText("Edit Buku");
        }
        else {
            formBukuTitle.setText("Tambah Buku");
            btnSubmitBuku.setText("Tambahkan Buku");
        }


        btnSubmitBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = txJudul.getText().toString().trim();
                String harga = txHarga.getText().toString().trim();
                String deskripsi = txDesk.getText().toString().trim();
                if (judul.isEmpty())
                    txJudul.setError("Judul Tidak Boleh Kosong!");
                if (harga.isEmpty())
                    txHarga.setError("Harga Tidak Boleh Kosong!");
                if (deskripsi.isEmpty())
                    txDesk.setError("Deskripsi Tidak Boleh Kosong!");

                ContentValues values = new ContentValues();
                values.put(DBHelper.master_buku_judul, judul);
                values.put(DBHelper.master_buku_harga, harga);
                values.put(DBHelper.master_buku_deskripsi, deskripsi);
                if(Global.isEditBuku) {
                    db.updateData(DBHelper.table_master_buku, values, DBHelper.master_buku_id, Global.bukuEdit._id);
                    Toast.makeText(context, "Buku Berhasil Diubah " + judul, Toast.LENGTH_SHORT).show();
                }
                else {
                    db.insertData(DBHelper.table_master_buku, values);
                    Toast.makeText(context, "Buku Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                }

                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.mainFragment, new ViewBukuF()).addToBackStack(null).commit();
            }

        });



        return view;
    }
}