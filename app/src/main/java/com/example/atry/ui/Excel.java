package com.example.atry.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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

import java.io.Serializable;

public class Excel extends AppCompatActivity {

    private EditText edit;
    private Notepad notepad;
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
        edit = findViewById(R.id.editText);

        myDataBaseOpenHelper = new MyDataBaseOpenHelper(this);

        Serializable serializable = getIntent().getSerializableExtra("notepad");
        notepad = (Notepad) serializable;
        if(notepad != null){
            edit.setText(notepad.getContent());
        }
    }

    //创建退出编辑页面的方法
    public void esc(View view) {
        finish();
        Toast.makeText(this,"已退出编辑页面",Toast.LENGTH_SHORT).show();
    }

    //创建删除内容的方法
    public void detele(View view) {
        String content = edit.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            Toast.makeText(this,"请先输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        edit.setText("");
        Toast.makeText(this,"删除成功", Toast.LENGTH_SHORT).show();
    }
    //创建保存内容的方法
    public void save(View view) {
        String content = edit.getText().toString().trim();
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