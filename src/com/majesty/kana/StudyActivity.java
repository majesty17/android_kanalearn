package com.majesty.kana;

import java.util.ArrayList;
import java.util.HashMap;

import com.majesty.kana.game.KanaGame;
import com.majesty.kana.game.Util;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class StudyActivity extends Activity {

    GridView gv_qingyin, gv_zhuoyin, gv_aoyin;
    public static Typeface tf = null;

    private MediaPlayer music = null;// 播放器引用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        
        //返回键
        ActionBar actionBar = getActionBar();  
        actionBar.setDisplayHomeAsUpEnabled(true);  
        
        // 拿到控件
        gv_qingyin = (GridView) findViewById(R.id.gv_qingyin);
        gv_zhuoyin = (GridView) findViewById(R.id.gv_zhuoyin);
        gv_aoyin = (GridView) findViewById(R.id.gv_aoyin);

        // 初始化sp

        // 初始化，设置适配器
        gv_qingyin.setNumColumns(5);
        ArrayList<HashMap<String, Object>> item = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> null_map = new HashMap<String, Object>();
        for (int i = 0; i < 46; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("hira", KanaGame.kana_small[i]);
            map.put("kata", KanaGame.kana_big[i]);
            map.put("roma", KanaGame.kana_roma[i]);
            if (i == 36 || i == 37) {
                item.add(null_map);
            }
            if (i == 44) {
                item.add(null_map);
                item.add(null_map);
                item.add(null_map);
            }
            item.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, item, R.layout.griditem_study,
                new String[] { "hira", "kata", "roma" }, new int[] { R.id.tv_hira, R.id.tv_kata, R.id.tv_roma }) {
        };
        gv_qingyin.setAdapter(simpleAdapter);

        // 2浊音
        gv_zhuoyin.setNumColumns(5);
        ArrayList<HashMap<String, Object>> item2 = new ArrayList<HashMap<String, Object>>();

        for (int i = 46; i < 46 + 25; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("hira", KanaGame.kana_small[i]);
            map.put("kata", KanaGame.kana_big[i]);
            map.put("roma", KanaGame.kana_roma[i]);
            item2.add(map);
        }
        SimpleAdapter simpleAdapter2 = new SimpleAdapter(this, item2, R.layout.griditem_study,
                new String[] { "hira", "kata", "roma" }, new int[] { R.id.tv_hira, R.id.tv_kata, R.id.tv_roma }) {
        };
        gv_zhuoyin.setAdapter(simpleAdapter2);

        // 3拗音
        gv_aoyin.setNumColumns(3);
        ArrayList<HashMap<String, Object>> item3 = new ArrayList<HashMap<String, Object>>();

        for (int i = 46 + 25; i < 46 + 25 + 11 * 3; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("hira", KanaGame.kana_small[i]);
            map.put("kata", KanaGame.kana_big[i]);
            map.put("roma", KanaGame.kana_roma[i]);
            item3.add(map);
        }
        SimpleAdapter simpleAdapter3 = new SimpleAdapter(this, item3, R.layout.griditem_study,
                new String[] { "hira", "kata", "roma" }, new int[] { R.id.tv_hira, R.id.tv_kata, R.id.tv_roma }) {
        };
        gv_aoyin.setAdapter(simpleAdapter3);

        // 点击发音
        gv_qingyin.setOnItemClickListener(itemClick);
        gv_zhuoyin.setOnItemClickListener(itemClick);
        gv_aoyin.setOnItemClickListener(itemClick);

        // 字体初始化
        tf = Typeface.createFromAsset(this.getAssets(), "epkyouka.ttf");
    }

    private OnItemClickListener itemClick = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String roma = (String) ((TextView) view.findViewById(R.id.tv_roma)).getText();
            if (roma.equals("") == false) {
                if (music != null)
                    music.release();
                music = MediaPlayer.create(StudyActivity.this, Util.getSound(roma));
                music.start();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.study, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        gv_qingyin.setVisibility(View.GONE);
        gv_zhuoyin.setVisibility(View.GONE);
        gv_aoyin.setVisibility(View.GONE);
        switch (id) {
        case R.id.menu_study_qingyin:
            gv_qingyin.setVisibility(View.VISIBLE);
            break;
        case R.id.menu_study_zhuoyin:
            gv_zhuoyin.setVisibility(View.VISIBLE);
            break;
        case R.id.menu_study_aoyin:
            gv_aoyin.setVisibility(View.VISIBLE);
            break;
        
        default:
            //后退
            StudyActivity.this.finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
