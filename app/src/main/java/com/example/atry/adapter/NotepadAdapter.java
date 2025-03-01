package com.example.atry.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atry.Fragment.Fragmentjizhang;
import com.example.atry.MainActivity2;
import com.example.atry.R;
import com.example.atry.bean.Notepad;
import com.example.atry.ui.Excel;

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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDeleteDialog(notepad,v.getContext());
                return false;
            }
        });
        //点击进入编辑页面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity2 mainActivity2 = (MainActivity2) v.getContext();
                Intent intent = new Intent(mainActivity2, Excel.class);
                intent.putExtra("notepad", notepad);
                mainActivity2.startActivityForResult(intent,100);
            }
        });
    }
    //封装长安删除方法
    private void showDeleteDialog(Notepad notepad, Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("删除")
             .setMessage("确定删除吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"已取消删除", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity2 activity = (MainActivity2) context;
                        activity.myDataBaseOpenHelper.delete(notepad);
                        Toast.makeText(context,"已删除", Toast.LENGTH_SHORT).show();
                        //删除后刷新页面
                        Fragmentjizhang fragment = (Fragmentjizhang) activity.list.get(0);
                        fragment.copyway();
                    }
                })
             .create();
        alertDialog.show();
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
