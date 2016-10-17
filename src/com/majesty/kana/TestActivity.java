package com.majesty.kana;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class TestActivity extends Activity {

    LinearLayout ll_time;
    LinearLayout ll_num;
    ImageButton btn_mode_help;
    Button btn_test_start;
    RadioGroup rg_kana, rg_mode, rg_mode_game, rg_num_choose, rg_time_choose;
    EditText et_time_user, et_num_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // 返回键
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 控件绑定
        ll_time = (LinearLayout) findViewById(R.id.ll_time);
        ll_num = (LinearLayout) findViewById(R.id.ll_num);
        btn_mode_help = (ImageButton) findViewById(R.id.btn_mode_help);
        btn_test_start = (Button) findViewById(R.id.btn_test_start);
        rg_kana = (RadioGroup) findViewById(R.id.rg_kana);
        rg_mode = (RadioGroup) findViewById(R.id.rg_mode);
        rg_mode_game = (RadioGroup) findViewById(R.id.rg_mode_game);
        rg_num_choose = (RadioGroup) findViewById(R.id.rg_num_choose);
        rg_time_choose = (RadioGroup) findViewById(R.id.rg_time_choose);
        et_time_user = (EditText) findViewById(R.id.et_time_user);
        et_num_user = (EditText) findViewById(R.id.et_num_user);

        // 帮助按钮
        btn_mode_help.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new Builder(TestActivity.this);
                builder.setTitle("游戏模式");
                builder.setMessage(getResources().getString(R.string.mode_help));
                builder.setNegativeButton("确定", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 切换范围
        // 模式切换,决定时间和次数选择是否出现
        rg_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                case R.id.rbtn_mode_num:
                    ll_num.setVisibility(View.VISIBLE);
                    ll_time.setVisibility(View.GONE);
                    break;
                case R.id.rbtn_mode_time:
                    ll_num.setVisibility(View.GONE);
                    ll_time.setVisibility(View.VISIBLE);
                    break;
                default:
                    ll_num.setVisibility(View.GONE);
                    ll_time.setVisibility(View.GONE);
                    break;
                }
            }
        });

        // 次数选择
        rg_num_choose.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbtn_num_user) {
                    et_num_user.setEnabled(true);
                } else {
                    et_num_user.setEnabled(false);
                }
            }
        });
        rg_time_choose.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbtn_time_user) {
                    et_time_user.setEnabled(true);
                } else {
                    et_time_user.setEnabled(false);
                }
            }
        });

        // 初始化他们的位置
        rg_kana.check(R.id.rbtn_hiragana);
        rg_mode.check(R.id.rbtn_mode_num);
        rg_mode_game.check(R.id.rbtn_mode_game_kana_switch);
        rg_num_choose.check(R.id.rbtn_num_20);
        rg_time_choose.check(R.id.rbtn_time_1min);

        // 开始测验
        btn_test_start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2,打开游戏界面
                Intent intent = new Intent(TestActivity.this, GameActivity.class);
                intent.putExtra("kana", rg_kana.getCheckedRadioButtonId());
                intent.putExtra("mode", rg_mode.getCheckedRadioButtonId());
                intent.putExtra("mode_game", rg_mode_game.getCheckedRadioButtonId());
                intent.putExtra("time", rg_time_choose.getCheckedRadioButtonId());
                intent.putExtra("num", rg_num_choose.getCheckedRadioButtonId());
                intent.putExtra("time_user", et_time_user.getText().toString().trim());
                intent.putExtra("num_user", et_num_user.getText().toString().trim());
                startActivity(intent);
                saveMode();
            }
        });

        // 预先加载上一次设定
        loadMode();
    }

    // 读取配置
    private void loadMode() {
        SharedPreferences mySharedPreferences = getSharedPreferences("mode_saved", Activity.MODE_PRIVATE);
        String mode_str = mySharedPreferences.getString("mode_str", "0_0_0_0_0");
        String[] mode_arr = mode_str.split("_");

        rg_kana.check(rg_kana.getChildAt(Integer.valueOf(mode_arr[0])).getId());
        rg_mode.check(rg_mode.getChildAt(Integer.valueOf(mode_arr[1])).getId());
        rg_mode_game.check(rg_mode_game.getChildAt(Integer.valueOf(mode_arr[2])).getId());
        rg_num_choose.check(rg_num_choose.getChildAt(Integer.valueOf(mode_arr[3])).getId());
        rg_time_choose.check(rg_time_choose.getChildAt(Integer.valueOf(mode_arr[4])).getId());
    }

    // 保存配置
    private void saveMode() {
        SharedPreferences mySharedPreferences = getSharedPreferences("mode_saved", Activity.MODE_PRIVATE);
        // 实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        // 用putString的方法保存数据
        int conf0 = rg_kana.indexOfChild(findViewById(rg_kana.getCheckedRadioButtonId()));
        int conf1 = rg_mode.indexOfChild(findViewById(rg_mode.getCheckedRadioButtonId()));
        int conf2 = rg_mode_game.indexOfChild(findViewById(rg_mode_game.getCheckedRadioButtonId()));
        int conf3 = rg_num_choose.indexOfChild(findViewById(rg_num_choose.getCheckedRadioButtonId()));
        int conf4 = rg_time_choose.indexOfChild(findViewById(rg_time_choose.getCheckedRadioButtonId()));

        editor.putString("mode_str", conf0 + "_" + conf1 + "_" + conf2 + "_" + conf3 + "_" + conf4);
        // 提交当前数据
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TestActivity.this.finish();
        return super.onOptionsItemSelected(item);
    }
}
