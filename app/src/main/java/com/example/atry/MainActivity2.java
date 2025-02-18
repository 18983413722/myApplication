package com.example.atry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atry.Fragment.Fragmentjizhang;
import com.example.atry.Fragment.Fragmenttongji;
import com.example.atry.Fragment.Fragmentwode;
import com.example.atry.adapter.NotepadAdapter;
import com.example.atry.bean.Notepad;
import com.example.atry.database.MyDataBaseOpenHelper;
import com.example.atry.ui.Excel;
import com.example.atry.utils.gettime;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    List<Fragment> list;
    private MyDataBaseOpenHelper myDataBaseOpenHelper;
    private RecyclerView recyclerView;
    private NotepadAdapter notepadAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list = new ArrayList<>();
        list.add(new Fragmentjizhang());
        list.add(new Fragmenttongji());
        list.add(new Fragmentwode());

        // 设置启动页面
        startFragment(list.get(0));

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_nav);

        /*myDataBaseOpenHelper = new MyDataBaseOpenHelper(MainActivity2.this);

        // 初始化RecyclerView和适配器
        recyclerView = findViewById(R.id.recycler); // 确保布局文件中有RecyclerView，id为recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notepadAdapter = new NotepadAdapter();
        recyclerView.setAdapter(notepadAdapter);

        // 获取数据并更新适配器
        List<Notepad> notepadList = myDataBaseOpenHelper.show();
        notepadAdapter.setData(notepadList);*/

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.jizhang) {
                    startFragment(list.get(0));
                    return true;
                } else if (itemId == R.id.tongji) {
                    startFragment(list.get(1));
                    return true;
                } else if (itemId == R.id.wode) {
                    startFragment(list.get(2));
                    return true;
                }
                return false;
            }
        });
    }

    // 启动Fragment
    private void startFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    // 创建添加按钮
    public void add(View view) {
        Intent intent = new Intent(MainActivity2.this, Excel.class);
        startActivity(intent);
    }
}