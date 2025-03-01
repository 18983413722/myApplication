package com.example.atry.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atry.MainActivity2;
import com.example.atry.R;
import com.example.atry.adapter.NotepadAdapter;
import com.example.atry.bean.Notepad;
import com.example.atry.database.MyDataBaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Fragmentjizhang extends Fragment {

    public MyDataBaseOpenHelper myDataBaseOpenHelper;
    private RecyclerView recyclerView;
    public NotepadAdapter notepadAdapter;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            View view = inflater.inflate(R.layout.jizhang_layout, container, false);

            //这里有误 尝试了很多次都没能改变主页面的字体大小，没有删，以后再试试
            Bundle args = getArguments();
            if (args != null) {
                // 从 Bundle 中提取字号数据
                int selectedFontSize = args.getInt("selectedFontSize", 16); // 默认字号为 16


                View v = getLayoutInflater().inflate(R.layout.listview, null);
                textView = v.findViewById(R.id.text1);
                if (textView != null) {
                    // 设置字号
                    textView.setTextSize(selectedFontSize);
                }
            }

            return view;
        }



    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDataBaseOpenHelper = new MyDataBaseOpenHelper(getActivity());
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        notepadAdapter = new NotepadAdapter();
        recyclerView.setAdapter(notepadAdapter);
        // recyclerView显示分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        // 获取数据并更新适配器
        copyway();
    }
    //封装刷新数据的方法，以便多次调用
    public void copyway() {
        List<Notepad> notepadList = myDataBaseOpenHelper.show();
        notepadAdapter.setData(notepadList);
    }
    //设置返回界面时能更新实时数据
    @Override
    public void onResume() {
        super.onResume();
        copyway();
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        List<Notepad> list = new ArrayList<>();

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Notepad notepad = list.get(position);

            holder.textView1.setText(notepad.getContent());
            holder.textView2.setText(notepad.getTime());
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            TextView textView1, textView2;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView1 = itemView.findViewById(R.id.text1);
                textView2 = itemView.findViewById(R.id.text2);
            }
        }
    }
}