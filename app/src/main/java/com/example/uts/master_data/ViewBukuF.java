package com.example.uts.master_data;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts.DBHelper;
import com.example.uts.Global;
import com.example.uts.R;
import com.example.uts.adapter.BukuAdapter;
import com.example.uts.model.BukuEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewBukuF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewBukuF extends Fragment {
    FloatingActionButton btnAddBuku;
    TextView jumlahBuku;
    Context context;
    public ViewBukuF() {
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
    public static ViewBukuF newInstance(String param1, String param2) {
        ViewBukuF fragment = new ViewBukuF();
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
        View view = inflater.inflate(R.layout.fragment_view_buku, container, false);
        DBHelper db = new DBHelper(context);

        btnAddBuku = (FloatingActionButton) view.findViewById(R.id.btnAddBuku);
        Global.rcViewBuku = (RecyclerView) view.findViewById(R.id.rcViewBuku);
        jumlahBuku = (TextView) view.findViewById(R.id.jumlahBuku);

        btnAddBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.isEditBuku = false;
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.mainFragment, new FormBukuF()).addToBackStack(null).commit();
            }
        });

        List<BukuEntity> listBuku = db.getAllBuku();
        Global.rcViewBuku.setLayoutManager(new LinearLayoutManager(context));
        Global.rcViewBuku.setAdapter(new BukuAdapter(listBuku));
        jumlahBuku.setText("Jumlah Buku: " + listBuku.size());



        return view;
    }
}