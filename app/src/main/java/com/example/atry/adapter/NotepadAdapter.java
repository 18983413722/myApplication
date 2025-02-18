package com.example.atry.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atry.R;
import com.example.atry.bean.Notepad;

import java.util.ArrayList;
import java.util.List;

public class NotepadAdapter extends RecyclerView.Adapter<NotepadAdapter.viewHolder>{

    List<Notepad> list = new ArrayList<>();

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.listview,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Notepad notepad = list.get(position);
        holder.textView1.setText(notepad.getContent());
        holder.textView2.setText(notepad.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Notepad> data) {
        this.list = data;
        notifyDataSetChanged();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);

        }
    }
}
