package com.majesty.kana.game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.majesty.kana.R;

public class KanaGame {

    public String question;
    public String[] ans;
    public int right_ans;

    private int kana;
    private int mode_game;

    Random random;
    
    public KanaGame(int kana, int mode_game) {
        this.kana = kana;
        this.mode_game = mode_game;
        ans=new String[4];
        random=new Random(System.currentTimeMillis());
    }

    public void nextGame() {

        //产生四个不同随机数[0~104),以及答案[0~4)
        

        
        right_ans=random.nextInt(4);
        int[] ans_ind=get4Random();
        int aim=ans_ind[right_ans];        
        
        
        //如果是随机模式，则随机成3个确定模式;假名同;
        int final_mod=mode_game;
        int final_kana=kana;
        
        if(final_mod==R.id.rbtn_mode_game_all){
            int roll=random.nextInt(3);
            if(roll==0){
                final_mod=R.id.rbtn_mode_game_kana_to_roma;
            }
            else if(roll==1){
                final_mod=R.id.rbtn_mode_game_roma_to_kana;
            }
            else{
                final_mod=R.id.rbtn_mode_game_kana_switch;
            }
        }
        if(final_kana==R.id.rbtn_allkana){
            int roll=random.nextInt(2);
            if(roll==0){
                final_kana=R.id.rbtn_hiragana;
            }
            else{
                final_kana=R.id.rbtn_katakana;
            }
        }
        
        
        //对不同模式，填充问题和答案
        switch (final_mod) {
        case R.id.rbtn_mode_game_kana_to_roma:
            for(int i=0;i<4;i++){
                ans[i]=kana_roma[ans_ind[i]];
            }
            if(final_kana==R.id.rbtn_hiragana){
                question=kana_small[aim];
            }
            else if(final_kana==R.id.rbtn_katakana){
                question=kana_big[aim];
            }
            break;
        case R.id.rbtn_mode_game_roma_to_kana:
            question=kana_roma[aim];
            
            for(int i=0;i<4;i++){
                if(final_kana==R.id.rbtn_hiragana){
                    ans[i]=kana_small[ans_ind[i]];
                }
                else if(final_kana==R.id.rbtn_katakana){
                    ans[i]=kana_big[ans_ind[i]];
                }
            }
            
            
            break;
        case R.id.rbtn_mode_game_kana_switch:
            if(final_kana==R.id.rbtn_hiragana){
                question=kana_small[aim];
                for(int i=0;i<4;i++){
                    ans[i]=kana_big[ans_ind[i]];
                }
            }
            else if(final_kana==R.id.rbtn_katakana){
                question=kana_big[aim];
                for(int i=0;i<4;i++){
                    ans[i]=kana_small[ans_ind[i]];
                }
            }
            break;
        default: // never go here
            break;
        }
    }
    
    private int[] get4Random(){
        Set<Integer> set = new HashSet<Integer>(); 
        while(set.size()<4){
            set.add(random.nextInt(104));
        }
        int[] ret=new int[4];
        int i=0;
        for (int aint : set) {
            ret[i++]=aint;
        }
        return ret;
    }
    
    
    public static final String[] kana_small={"あ","い","う","え","お","か","き","く","け","こ","さ","し","す","せ","そ","た","ち","つ","て","と","な","に","ぬ","ね","の","は","ひ","ふ","へ","ほ","ま","み","む","め","も","や","ゆ","よ","ら","り","る","れ","ろ","わ","を","ん","が","ぎ","ぐ","げ","ご","ざ","じ","ず","ぜ","ぞ","だ","ぢ","づ","で","ど","ば","び","ぶ","べ","ぼ","ぱ","ぴ","ぷ","ぺ","ぽ","きゃ","きゅ","きょ","しゃ","しゅ","しょ","ちゃ","ちゅ","ちょ","にゃ","にゅ","にょ","ひゃ","ひゅ","ひょ","みゃ","みゅ","みょ","りゃ","りゅ","りょ","ぎゃ","ぎゅ","ぎょ","じゃ","じゅ","じょ","びゃ","びゅ","びょ","ぴゃ","ぴゅ","ぴょ"};
    public static final String[] kana_big={"ア","イ","ウ","エ","オ","カ","キ","ク","ケ","コ","サ","シ","ス","セ","ソ","タ","チ","ツ","テ","ト","ナ","ニ","ヌ","ネ","ノ","ハ","ヒ","フ","ヘ","ホ","マ","ミ","ム","メ","モ","ヤ","ユ","ヨ","ラ","リ","ル","レ","ロ","ワ","ヲ","ン","ガ","ギ","グ","ゲ","ゴ","ザ","ジ","ズ","ゼ","ゾ","ダ","ヂ","ヅ","デ","ド","バ","ビ","ブ","ベ","ボ","パ","ピ","プ","ペ","ポ","キャ","キュ","キョ","シャ","シュ","ショ","チャ","チュ","チョ","ニャ","ニュ","ニョ","ヒャ","ヒュ","ヒョ","ミャ","ミュ","ミョ","リャ","リュ","リョ","ギャ","ギュ","ギョ","ジャ","ジュ","ジョ","ビャ","ビュ","ビョ","ピャ","ピュ","ピョ"};
    public static final String[] kana_roma={"a","i","u","e","o","ka","ki","ku","ke","ko","sa","shi","su","se","so","ta","chi","tsu","te","to","na","ni","nu","ne","no","ha","hi","fu","he","ho","ma","mi","mu","me","mo","ya","yu","yo","ra","ri","ru","re","ro","wa","o","n","ga","gi","gu","ge","go","za","ji","zu","ze","zo","da","ji","zu","de","do","ba","bi","bu","be","bo","pa","pi","pu","pe","po","kya","kyu","kyo","sha","shu","sho","cha","chu","cho","nya","nyu","nyo","hya","hyu","hyo","mya","myu","myo","rya","ryu","ryo","gya","gyu","gyo","ja","ju","jo","bya","byu","byo","pya","pyu","pyo"};

    public int getGameTime(int time_hard,String time_user) {
        switch (time_hard) {
        case R.id.rbtn_time_1min:
            return 60;
        case R.id.rbtn_time_2min:
            return 120;
        case R.id.rbtn_time_5min:
            return 300;
        default:
            return Integer.parseInt(time_user)*60;
        }
    }

    public int getGameNum(int num_hard,String num_user) {
        switch (num_hard) {
        case R.id.rbtn_num_10:
            return 10;
        case R.id.rbtn_num_20:
            return 20;
        case R.id.rbtn_num_50:
            return 50;
        default:
            return Integer.parseInt(num_user);
        }
    }

}
