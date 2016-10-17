package com.majesty.kana.game;

import java.util.ArrayList;

import com.majesty.kana.R;

public class Util {
    
    public static void sortInsert(ArrayList<String> arr,String line){
        if(arr==null)
            return;
        int score=Integer.valueOf(line.split(",")[1]);
        int size=arr.size();
        int i;
        for(i=0;i<size;i++){
            int scorei=Integer.valueOf(arr.get(i).split(",")[1]);
            if(score>scorei){
                arr.add(i, line);
                break;
            }
        }
        if(i==size){
            arr.add(line);
        }
    }
    public static int getSound(String roma){
        if (roma=="a") return R.raw.a;
        if (roma=="i") return R.raw.i;
        if (roma=="u") return R.raw.u;
        if (roma=="e") return R.raw.e;
        if (roma=="o") return R.raw.o;
        if (roma=="ka") return R.raw.ka;
        if (roma=="ki") return R.raw.ki;
        if (roma=="ku") return R.raw.ku;
        if (roma=="ke") return R.raw.ke;
        if (roma=="ko") return R.raw.ko;
        if (roma=="sa") return R.raw.sa;
        if (roma=="shi") return R.raw.shi;
        if (roma=="su") return R.raw.su;
        if (roma=="se") return R.raw.se;
        if (roma=="so") return R.raw.so;
        if (roma=="ta") return R.raw.ta;
        if (roma=="chi") return R.raw.chi;
        if (roma=="tsu") return R.raw.tsu;
        if (roma=="te") return R.raw.te;
        if (roma=="to") return R.raw.to;
        if (roma=="na") return R.raw.na;
        if (roma=="ni") return R.raw.ni;
        if (roma=="nu") return R.raw.nu;
        if (roma=="ne") return R.raw.ne;
        if (roma=="no") return R.raw.no;
        if (roma=="ha") return R.raw.ha;
        if (roma=="hi") return R.raw.hi;
        if (roma=="fu") return R.raw.fu;
        if (roma=="he") return R.raw.he;
        if (roma=="ho") return R.raw.ho;
        if (roma=="ma") return R.raw.ma;
        if (roma=="mi") return R.raw.mi;
        if (roma=="mu") return R.raw.mu;
        if (roma=="me") return R.raw.me;
        if (roma=="mo") return R.raw.mo;
        if (roma=="ya") return R.raw.ya;
        if (roma=="yu") return R.raw.yu;
        if (roma=="yo") return R.raw.yo;
        if (roma=="ra") return R.raw.ra;
        if (roma=="ri") return R.raw.ri;
        if (roma=="ru") return R.raw.ru;
        if (roma=="re") return R.raw.re;
        if (roma=="ro") return R.raw.ro;
        if (roma=="wa") return R.raw.wa;
        if (roma=="o") return R.raw.o;
        if (roma=="n") return R.raw.n;
        if (roma=="ga") return R.raw.ga;
        if (roma=="gi") return R.raw.gi;
        if (roma=="gu") return R.raw.gu;
        if (roma=="ge") return R.raw.ge;
        if (roma=="go") return R.raw.go;
        if (roma=="za") return R.raw.za;
        if (roma=="ji") return R.raw.ji;
        if (roma=="zu") return R.raw.zu;
        if (roma=="ze") return R.raw.ze;
        if (roma=="zo") return R.raw.zo;
        if (roma=="da") return R.raw.da;
        if (roma=="ji") return R.raw.ji;
        if (roma=="zu") return R.raw.zu;
        if (roma=="de") return R.raw.de;
        if (roma=="do") return R.raw.do_;
        if (roma=="ba") return R.raw.ba;
        if (roma=="bi") return R.raw.bi;
        if (roma=="bu") return R.raw.bu;
        if (roma=="be") return R.raw.be;
        if (roma=="bo") return R.raw.bo;
        if (roma=="pa") return R.raw.pa;
        if (roma=="pi") return R.raw.pi;
        if (roma=="pu") return R.raw.pu;
        if (roma=="pe") return R.raw.pe;
        if (roma=="po") return R.raw.po;
        if (roma=="kya") return R.raw.kya;
        if (roma=="kyu") return R.raw.kyu;
        if (roma=="kyo") return R.raw.kyo;
        if (roma=="sha") return R.raw.sha;
        if (roma=="shu") return R.raw.shu;
        if (roma=="sho") return R.raw.sho;
        if (roma=="cha") return R.raw.cha;
        if (roma=="chu") return R.raw.chu;
        if (roma=="cho") return R.raw.cho;
        if (roma=="nya") return R.raw.nya;
        if (roma=="nyu") return R.raw.nyu;
        if (roma=="nyo") return R.raw.nyo;
        if (roma=="hya") return R.raw.hya;
        if (roma=="hyu") return R.raw.hyu;
        if (roma=="hyo") return R.raw.hyo;
        if (roma=="mya") return R.raw.mya;
        if (roma=="myu") return R.raw.myu;
        if (roma=="myo") return R.raw.myo;
        if (roma=="rya") return R.raw.rya;
        if (roma=="ryu") return R.raw.ryu;
        if (roma=="ryo") return R.raw.ryo;
        if (roma=="gya") return R.raw.gya;
        if (roma=="gyu") return R.raw.gyu;
        if (roma=="gyo") return R.raw.gyo;
        if (roma=="ja") return R.raw.ja;
        if (roma=="ju") return R.raw.ju;
        if (roma=="jo") return R.raw.jo;
        if (roma=="bya") return R.raw.bya;
        if (roma=="byu") return R.raw.byu;
        if (roma=="byo") return R.raw.byo;
        if (roma=="pya") return R.raw.pya;
        if (roma=="pyu") return R.raw.pyu;
        if (roma=="pyo") return R.raw.pyo;
        return R.raw.a;
    }

    
    
}
