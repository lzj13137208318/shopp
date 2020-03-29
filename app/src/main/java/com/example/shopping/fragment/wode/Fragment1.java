package com.example.shopping.fragment.wode;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    private RecyclerView rec;

    List<String> list;
    Context context;
    MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_item, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rec = (RecyclerView) view.findViewById(R.id.rec);
        context = getContext();

        list = new ArrayList<>();

        for(int i=0; i<100; i++){
            list.add("item data "+i);
        }
        myAdapter = new MyAdapter();
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec.setAdapter(myAdapter);
    }



    class MyAdapter extends RecyclerView.Adapter{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_item1,null);
            VH vh = new VH(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            VH vh = (VH) holder;
            vh.txtItem.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class VH extends RecyclerView.ViewHolder{

        TextView txtItem;
        public VH(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txt_item);
        }
    }
}
