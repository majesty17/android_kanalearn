package com.majesty.kana;

import com.majesty.kana.game.GameRecord;
import com.majesty.kana.game.Util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ConfigActivity extends Activity {

    Button btn_config_clean_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        // 返回键
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 控件
        btn_config_clean_record = (Button) findViewById(R.id.btn_config_clean_record);
        btn_config_clean_record.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ret = GameRecord.cleanRecord(ConfigActivity.this);
                if (ret) {
                    Toast.makeText(ConfigActivity.this, "清理成功~", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
