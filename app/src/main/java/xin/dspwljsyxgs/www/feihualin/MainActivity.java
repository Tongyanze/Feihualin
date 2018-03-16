package xin.dspwljsyxgs.www.feihualin;

import android.app.Activity;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.io.*;
import java.lang.*;
import java.util.Scanner;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;

import android.widget.Toast;

public class MainActivity extends Activity {
    static int func=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt;
        bt = (Button)findViewById(R.id.button1);
        RadioGroup rgp=(RadioGroup)findViewById(R.id.rgp1);
        final RadioButton rdb1,rdb2;
        rdb1=(RadioButton)findViewById(R.id.btn1);
        rdb2=(RadioButton)findViewById(R.id.btn2);
        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == rdb1.getId()){
                    func=1;
                }
                if (i == rdb2.getId()){
                    func=2;
                }
            }
        });


        bt.setOnClickListener(new View.OnClickListener(){
            @Override //监听点击事件

            public void onClick(View v) {
                EditText inp =(EditText) findViewById(R.id.edittext1);
                String st = inp.getText().toString();
                String ans = "";
                Scanner filescan,utlscan;

                int num = 0;

                try {
                    TextView tv = (TextView) findViewById(R.id.textview1);
                    InputStream context = getClass().getClassLoader().getResourceAsStream("assets/tang300.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(context));
                    String ss;

                    String delta = " ";
                    if (st.equals("") || st == null){
                        tv.setText("orz，你还什么都没有输入呢！3");
                        return;
                    }
                    if (func == 2) {
                        while ((ss = br.readLine()) != null) {

                            if (ss.length() > 3)
                                if ((ss.charAt(0) >= '0') && (ss.charAt(0) <= '9')) {
                                    delta = " (" + ss + ")";
                                    continue;
                                }
                            if (ss.contains(st)) {
                                if ((ss.charAt(0) >= '0') && (ss.charAt(0) <= '9'))
                                    continue;
                                num++;
                                ans = ans + num + "." + ss + delta + "\n";
                                //tv.setText("OMG");

                            }
                        }
                    }
                    if (func == 1){
                        boolean find=false;
                        //Toast.makeText(MainActivity.this,"调试用：功能为1",Toast.LENGTH_SHORT).show();
                        while ((ss = br.readLine()) != null) {

                            if (ss.length() > 3) {
                                if ((ss.charAt(0) >= '0') && (ss.charAt(0) <= '9')) {
                                    find=false;
                                    if (ss.contains(st)) {
                                        find=true;
                                        delta = " (" + ss + ")";
                                        continue;
                                    }
                                }
                                if (find == true) {
                                    num++;
                                    ans = ans + num + "." + ss + delta + "\n";
                                }
                            }


                            //tv.setText("OMG");
                        }
                    }
                    int k = 0;
                    ans = "共搜索到" + num +"条结果.\n" + ans;
                    SpannableStringBuilder style=new SpannableStringBuilder(ans);
                    while( k >= 0)
                    {
                        int l=ans.indexOf(st, k);
                        int r = l + st.length();
                        if (l == -1)
                            break;
                        k = l + 1;

                        style.setSpan(new ForegroundColorSpan(Color.rgb(19,29,242)),l,r,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    }
                    tv.setMovementMethod(ScrollingMovementMethod.getInstance());
                    tv.setHorizontallyScrolling(true);

                    tv.setText(style);

                }
                catch (Exception e)
                {
                    //Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                    TextView tv = (TextView) findViewById(R.id.textview1);
                    ans = "我的天哪,程序崩溃了。。。";
                    tv.setText(ans);
                }
            }
        });
    }

}