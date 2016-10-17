package com.majesty.kana;

import java.util.Date;

import com.majesty.kana.game.GameRecord;
import com.majesty.kana.game.KanaGame;

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
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements OnClickListener{
    
    //
    Button[] btn_game;
    Button btn_giveup;
    TextView tv_mode,tv_question,tv_progress;
    TextView tv_right_num,tv_wrong_num;
    //
    int kana;
    int mode;
    int mode_game;
    int time;
    int num;
    String time_user,num_user;
    KanaGame kanaGame;
    int right_ans;

    int game_progress=0;
    int right_ct=0;
    int wrong_ct=0;
    //游戏次数以及游戏时间
    int game_time=0;
    int game_num=0;
    
    //游戏持续时间
    Date start_date,end_date;
    //mode名
    String mode_name="";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        start_date=new Date();
        
        //0,取出intent数据
        Intent intent=getIntent();
        kana=intent.getIntExtra("kana", 0);
        mode=intent.getIntExtra("mode", 0);
        mode_game=intent.getIntExtra("mode_game", 0);
        time=intent.getIntExtra("time", 0);
        num=intent.getIntExtra("num", 0);
        time_user=intent.getStringExtra("time_user");
        num_user=intent.getStringExtra("num_user");
        if(mode==R.id.rbtn_mode_inf)
            mode_name="无限模式";
        else if(mode==R.id.rbtn_mode_dead)
            mode_name="死亡模式";
        else if(mode==R.id.rbtn_mode_num)
            mode_name="次数模式";
        else if(mode==R.id.rbtn_mode_time)
            mode_name="限时模式";
        else
            mode_name="";
        
        
        //1,绑定控件
        btn_game=new Button[4];
        btn_game[0]=(Button)findViewById(R.id.btn_game01);
        btn_game[1]=(Button)findViewById(R.id.btn_game02);
        btn_game[2]=(Button)findViewById(R.id.btn_game03);
        btn_game[3]=(Button)findViewById(R.id.btn_game04);
        btn_giveup=(Button)findViewById(R.id.btn_giveup);
        tv_mode=(TextView)findViewById(R.id.tv_mode);
        tv_question=(TextView)findViewById(R.id.tv_question);
        tv_progress=(TextView)findViewById(R.id.tv_progress);
        
        tv_wrong_num=(TextView)findViewById(R.id.tv_wrong_num);
        tv_right_num=(TextView)findViewById(R.id.tv_right_num);
        //初始化游戏
        kanaGame=new KanaGame(kana,mode_game);
        game_time=kanaGame.getGameTime(time,time_user);
        game_num=kanaGame.getGameNum(num,num_user);
        
        //点击事件
        for(int i=0;i<btn_game.length;i++){
            btn_game[i].setOnClickListener(this);
        }
        
        //开始第一个游戏
        nextGame();
        tv_mode.setText("游戏模式:"+mode_name);
        
        //放弃,只有无限模式，会进入积分；其他模式直接退出；
        btn_giveup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mode==R.id.rbtn_mode_inf){
                    endTheGame();
                }
                else{
                    GameActivity.this.finish();
                }
            }
        });
    }
    
    
    
    
    
    private void nextGame() {
        game_progress++;
        tv_progress.setText("当前进度: "+game_progress+"/");
        kanaGame.nextGame();
        tv_question.setText(kanaGame.question);
        for(int i=0;i<btn_game.length;i++){
            btn_game[i].setText(kanaGame.ans[i]);
        }
     
        right_ans=kanaGame.right_ans;
    }


    //判断是否退出,次数模式和死亡模式从这里退出
    private boolean ifEnd(){
        switch (mode) {
        case R.id.rbtn_mode_num:
            if(game_progress>=this.game_num){
                return true;
            }
            break;
        case R.id.rbtn_mode_dead:
            if(wrong_ct>=5){
                return true;
            }
        default:
            break;
        }
        return false;
    }
    //结束&统计排
    private void endTheGame(){
        
        end_date=new Date();
        long val=(end_date.getTime()-start_date.getTime())/1000;
        String msg="游戏时间:"+val/60+"分"+val%60+"秒\n答对数:"+right_ct+"\n答错数:"+wrong_ct;
        
        //计入排行榜
        if(mode==R.id.rbtn_mode_inf || mode==R.id.rbtn_mode_dead){
            GameRecord.writeRecord(this,mode_name, right_ct, wrong_ct, (int)val,end_date);
        }
        
        AlertDialog.Builder builder = new Builder(GameActivity.this);
        builder.setTitle("游戏结束");
        builder.setMessage(msg);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
                GameActivity.this.finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        //点的对不对
        int click_ind=0;
        for(int i=0;i<btn_game.length;i++){
            if(v.getId()==btn_game[i].getId()){
                click_ind=i;
                break;
            }
        }
        if(click_ind==right_ans){
            right_ct++;
        }
        else{
            if(mode==R.id.rbtn_mode_inf){
                right_ct=0;
                wrong_ct=0;
            }
            else
                wrong_ct++;
        }
        tv_right_num.setText(""+right_ct);
        tv_wrong_num.setText(""+wrong_ct);
        
        
        if(ifEnd()==false){
            nextGame();
        }
        else{
            //结束统计
            endTheGame();
        }
    }
    
}
