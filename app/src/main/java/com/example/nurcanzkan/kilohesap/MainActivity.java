package com.example.nurcanzkan.kilohesap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText boyEt;
    SeekBar kiloSb;
    RadioGroup gender;
    RadioButton woman,man;
    TextView idealKiloTv,durumTv,kiloTv,boyTv;
    double boy=1.1;
    int kilo=50;
    boolean kadinmi=true;
    RadioGroup.OnCheckedChangeListener RadioGroupOlayisleyicisi=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
             if(checkedId == R.id.woman)
                 kadinmi=true;
             else if(checkedId == R.id.man)
                 kadinmi=false;
             guncelle();
        }
    };
    SeekBar.OnSeekBarChangeListener SeekBarOlayisleyicisi=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            kilo=30+progress;
            guncelle();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    };
    TextWatcher EditTextOlayisleyicisi=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                   boy=Double.parseDouble(s.toString())/100.0;
                }
                catch (NumberFormatException e){
                    boy=0.0;
                }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idealKiloTv=findViewById(R.id.idealKiloTv);
        boyEt=findViewById(R.id.boyEt);
        kiloSb =findViewById(R.id.kiloSb);
        gender =findViewById(R.id.gender);
        woman =findViewById(R.id.woman);
        man=findViewById(R.id.man);
        kiloTv=findViewById(R.id.kiloTv);
        boyTv=findViewById(R.id.boyTv);
        durumTv=findViewById(R.id.durumTv);

        boyTv.setText(String.valueOf(boy));

        boyEt.addTextChangedListener(EditTextOlayisleyicisi);
        kiloSb.setOnSeekBarChangeListener(SeekBarOlayisleyicisi);
        gender.setOnCheckedChangeListener(RadioGroupOlayisleyicisi);

       guncelle();


    }

     void guncelle() {
         boyTv.setText(String.valueOf(boy)+" m");
         kiloTv.setText(String.valueOf(kilo)+" kg");


         int ideal_kiloBayan = (int) (45.5 + 2.3*(boy*100*0.4-60));
         int ideal_kiloBay = (int) (50+2.3*(boy*100*0.4-60));
         double vki=kilo/(boy*boy);

         if(kadinmi){
             idealKiloTv.setText(String.valueOf(ideal_kiloBayan));
             if(vki<=19.1){
                 durumTv.setText(R.string.zayif);
             }else if(vki>19.1 && vki<=25.8){
                 durumTv.setText(R.string.ideal);
             }else if(5.8>vki && vki<=27.3){
                 durumTv.setText(R.string.normalden_fazla);
                // durumTv.setBackgroundResource(R.color.normalKilodanFazla);
             }
             else if(27.3>vki && vki<=32.3){
                 durumTv.setText(R.string.fazla_kilo);
                // durumTv.setBackgroundResource(R.color.cokFazlaKilolu);
             }
             else if(32.3>vki && vki<=34.9){
                 durumTv.setText(R.string.obez);
                // durumTv.setBackgroundColor(R.color.yüksekRiskKilo);
             }
             else {
                 durumTv.setText("Doktora git");
                // durumTv.setBackgroundColor(R.color.yüksekAsiriKilolu);
             }

         }else{
                 idealKiloTv.setText(String.valueOf(ideal_kiloBay));

             if(vki<=20.7){
                 durumTv.setText(R.string.zayif);
             }else if(vki>20.7 && vki<=26.4){
                 durumTv.setText(R.string.ideal);
             }else if(26.4>vki && vki<=27.8){
                 durumTv.setText(R.string.normalden_fazla);
                // durumTv.setBackgroundResource(R.color.normalKilodanFazla);
             }
             else if(27.8>vki && vki<=31.1){
                 durumTv.setText(R.string.fazla_kilo);
                // durumTv.setBackgroundResource(R.color.cokFazlaKilolu);
             }
             else if(31.1>vki && vki<=34.9){
                 durumTv.setText(R.string.obez);
                // durumTv.setBackgroundResource(R.color.yüksekRiskKilo);
             }
             else {
                 durumTv.setText(R.string.doktora);
                // durumTv.setBackgroundResource(R.color.yüksekAsiriKilolu);
             }
         }
    }
}
