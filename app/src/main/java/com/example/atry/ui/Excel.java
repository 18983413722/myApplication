package com.example.atry.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.atry.R;
import com.example.atry.bean.Notepad;
import com.example.atry.database.MyDataBaseOpenHelper;
import com.example.atry.utils.gettime;

public class Excel extends AppCompatActivity {

    private MyDataBaseOpenHelper myDataBaseOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_excel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //创建退出编辑页面的方法
    public void esc(View view) {
        finish();
        Toast.makeText(this,"已退出编辑页面",Toast.LENGTH_SHORT).show();
    }

    public void detele(View view) {
        String content = getIntent().getStringExtra("content");
        if(TextUtils.isEmpty(content)){
            Toast.makeText(this,"请先输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        content.isEmpty();

    }
    public void save(View view) {
        String content = getIntent().getStringExtra("content");
        if(TextUtils.isEmpty(content)){
            Toast.makeText(this,"请先输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        Notepad notepad = new Notepad();
        notepad.setContent(content);
        notepad.setTime(gettime.getTime());

        myDataBaseOpenHelper.insert(notepad);
        Toast.makeText(this,"保存成功", Toast.LENGTH_SHORT).show();
        finish();

    }
}