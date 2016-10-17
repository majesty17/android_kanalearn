package com.majesty.kana;

import java.util.ArrayList;

import com.majesty.kana.game.GameRecord;
import com.majesty.kana.game.Util;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecordActivity extends Activity {
    
    
    LinearLayout ll_record_dead,ll_record_inf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        
      //返回键
        ActionBar actionBar = getActionBar();  
        actionBar.setDisplayHomeAsUpEnabled(true);  
        
        //空间
        ll_record_inf=(LinearLayout)findViewById(R.id.ll_record_inf);
        ll_record_dead=(LinearLayout)findViewById(R.id.ll_record_dead);
        
        //load
        showRecord();
    }
    
    
    private void showRecord(){
        ArrayList<String> record=GameRecord.readRecord(this);;
        if(record!=null){
            
            ArrayList<String> record_dead=new ArrayList<String>();
            ArrayList<String> record_inf=new ArrayList<String>();
            
            //分类and排序
            for(String line:record){
                if(line.startsWith("死亡")){
                    Util.sortInsert(record_dead, line);
                }
                else{
                    Util.sortInsert(record_inf, line);
                }
            }
            
            //show
            for(String line:record_dead){
                TextView tv=new TextView(RecordActivity.this);
                tv.setText(line.replace(',', '\t'));
                ll_record_dead.addView(tv);
            }
            
            for(String line:record_inf){
                TextView tv=new TextView(RecordActivity.this);
                tv.setText(line.replace(',', '\t'));
                ll_record_inf.addView(tv);
            }
            
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RecordActivity.this.finish();
        return super.onOptionsItemSelected(item);
    }
}
