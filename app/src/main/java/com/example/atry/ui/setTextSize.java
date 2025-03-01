package com.example.atry.ui;


import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.atry.R;

public class setTextSize extends AppCompatActivity {

    private String[] textArr = {"小号", "默认", "大号", "超大号"};
    private int[] sizeArr = {14, 16, 18,100};
    private int index = 0;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_set_text_size);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        View view = getLayoutInflater().inflate(R.layout.listview, null);
        textView =view.findViewById(R.id.text1);

        }



    //创建调整字体大小的方法
    public void tiaozheng(View view) {


            new AlertDialog.Builder(this)
                    .setTitle("请选择字号")
                    .setSingleChoiceItems(textArr,index, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            index = which;
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            textView.setTextSize(sizeArr[index]);

                        }
                    })
                    .setNegativeButton("取消", null)
                    .create().show();

        }
    //创建返回按钮的方法
    public void onBackPressed(View view) {
        finish();
        Toast.makeText(this, "已退出设置界面", Toast.LENGTH_SHORT).show();
    }
}
