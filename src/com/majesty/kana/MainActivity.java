package com.majesty.kana;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    
    Button btn_study,btn_test,btn_about,btn_record,btn_config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到控件
        btn_study = (Button) findViewById(R.id.btn_study);
        btn_test = (Button) findViewById(R.id.btn_test);
        btn_about=(Button)findViewById(R.id.btn_about);
        btn_record=(Button)findViewById(R.id.btn_record);
        btn_config=(Button)findViewById(R.id.btn_config);
        
        //增加事件
        btn_study.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                startActivity(intent);
            }
        });
        btn_test.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        btn_about.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new Builder(MainActivity.this);
                builder.setTitle("信息");
                builder.setMessage("假名学习助手\nCoded by Majesty~");
                builder.setNegativeButton("确定", null);
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }
        });
        //记录
        btn_record.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RecordActivity.class);
                startActivity(intent);
            }
        });
        
        //config
        btn_config.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ConfigActivity.class));
            }
        });
    }

}
