package fpoply.thanhdvph33594.duanmau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoply.thanhdvph33594.duanmau.R;
import fpoply.thanhdvph33594.duanmau.adapter.top10Adapter;
import fpoply.thanhdvph33594.duanmau.dao.Top10DAO;
import fpoply.thanhdvph33594.duanmau.model.Sach;

public class Top10Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top10,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerTop10);
        Top10DAO top10DAO = new Top10DAO(getContext());
        ArrayList<Sach> list= top10DAO.getTop10();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        top10Adapter top10Adapter = new top10Adapter(getContext(),list);
        recyclerView.setAdapter(top10Adapter);
        return view;
    }
}
