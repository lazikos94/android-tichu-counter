package com.crib.counter;

import android.content.Context;
import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends Activity {
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    EditText team1points,team2points;
    TextView score1,score2,scores1,scores2,name1,name2;
    Integer sum1=0,sum2=0,tp1=0,tp2=0;
    Button reset,subtract,add;
    ArrayList<HashMap<String, String>> list =new ArrayList<HashMap<String,String>>();
    ListView show;
    CheckBox tichu1,tichu2,grand1,grand2,lostt1,lostt2,lostg1,lostg2,onetwo1,onetwo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1=(TextView)findViewById(R.id.name1);
        name2=(TextView)findViewById(R.id.name2);
        team1points = (EditText) findViewById(R.id.editText1);
        team1points.setFilters(new InputFilter[]{ new MinMaxFilter(-25, 125)});
        team2points = (EditText) findViewById(R.id.editText2);
        team2points.setFilters(new InputFilter[]{ new MinMaxFilter(-25, 125)});
        add = (Button) findViewById(R.id.add);
        score1 = (TextView) findViewById(R.id.textView1);
        score2 = (TextView) findViewById(R.id.textView2);
        subtract= (Button)findViewById(R.id.sub);
        reset = (Button)findViewById(R.id.Reset);
        show = (ListView)findViewById(R.id.show);
        tichu1=(CheckBox)findViewById(R.id.Tichu1);
        tichu2=(CheckBox)findViewById(R.id.Tichu2);
        grand1=(CheckBox)findViewById(R.id.Grand1);
        grand2=(CheckBox)findViewById(R.id.Grand2);
        lostt1=(CheckBox)findViewById(R.id.Ltichu1);
        lostt2=(CheckBox)findViewById(R.id.Ltichu2);
        lostg1=(CheckBox)findViewById(R.id.Lgrand1);
        lostg2=(CheckBox)findViewById(R.id.Lgrand2);
        onetwo1=(CheckBox)findViewById(R.id.onetwo1);
        onetwo2=(CheckBox)findViewById(R.id.onetwo2);
        String teamname1=getIntent().getStringExtra("teamname1");
        String teamname2=getIntent().getStringExtra("teamname2");
        name1.setText(teamname1);
        name2.setText(teamname2);
        team1points.setText(String.valueOf(0));
        team2points.setText(String.valueOf(0));

        tichu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onetwo2.isChecked()||tichu2.isChecked()||grand2.isChecked()||grand1.isChecked()){
                    Toast.makeText(MainActivity.this,"Invalid Action",Toast.LENGTH_SHORT).show();
                    tichu1.toggle();
                }
            }
        });
        tichu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onetwo1.isChecked()||tichu1.isChecked()||grand1.isChecked()||grand2.isChecked()){
                    Toast.makeText(MainActivity.this,"Invalid Action",Toast.LENGTH_SHORT).show();
                    tichu2.toggle();
                }
            }
        });
        grand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onetwo1.isChecked()||tichu1.isChecked()||grand1.isChecked()||tichu2.isChecked()){
                    Toast.makeText(MainActivity.this,"Invalid Action",Toast.LENGTH_SHORT).show();
                    grand2.toggle();
                }
            }
        });
        grand1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onetwo2.isChecked()||tichu2.isChecked()||grand2.isChecked()||tichu1.isChecked()){
                    Toast.makeText(MainActivity.this,"Invalid Action",Toast.LENGTH_SHORT).show();
                    grand1.toggle();
                }
            }
        });
        onetwo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onetwo2.isChecked()){
                    Toast.makeText(MainActivity.this,"Invalid Action",Toast.LENGTH_SHORT).show();
                    onetwo1.toggle();
                }
            }
        });
        onetwo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onetwo1.isChecked()){
                    Toast.makeText(MainActivity.this,"Invalid Action",Toast.LENGTH_SHORT).show();
                    onetwo2.toggle();
                }
            }
        });
        team1points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team1points.setText("");
            }
        });

        team2points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team2points.setText("");
            }
        });

        team1points.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Integer points= Integer.valueOf(team1points.getText().toString());
                if((actionId== EditorInfo.IME_ACTION_DONE)&&(points%5==0||points==0)){
                    Integer dab;
                    dab=100-points;
                    team2points.setText(String.valueOf(dab));

                }
                else if((actionId== EditorInfo.IME_ACTION_DONE)&&(points%5!=0||points!=0)){
                    Toast.makeText(MainActivity.this,"Put correct number in 1",Toast.LENGTH_LONG).show();
                    team1points.setText(String.valueOf(0));
                }
                return false;
            }
        });

        team2points.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Integer points= Integer.valueOf(team2points.getText().toString());
                if((actionId== EditorInfo.IME_ACTION_DONE)&&(points%5==0||points==0)){
                    Integer dab;
                    dab=100-points;
                    team1points.setText(String.valueOf(dab));

                }
                else if((actionId== EditorInfo.IME_ACTION_DONE)&&(points%5!=0||points!=0)){
                    Toast.makeText(MainActivity.this,"Put correct number in 2",Toast.LENGTH_LONG).show();
                    team2points.setText(String.valueOf(0));
                }
                return false;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String team1=team1points.getText().toString();
                String team2=team2points.getText().toString();
                tp1 = Integer.valueOf(team1);
                tp2 = Integer.valueOf(team2);
                HashMap<String,String> values= new HashMap<String, String>();
                if (tichu1.isChecked()&&onetwo1.isChecked()){
                    sum1=sum1+300;
                    sum2=sum2;
                    values.put(FIRST_COLUMN,String.valueOf(300));
                }
                else if (grand1.isChecked()&&onetwo1.isChecked()){
                    sum1=sum1+400;
                    sum2=sum2;
                    values.put(FIRST_COLUMN,String.valueOf(400));
                }
                else if (lostt1.isChecked()&&onetwo1.isChecked()){
                    sum1=sum1+100;
                    sum2=sum2;
                    values.put(FIRST_COLUMN,String.valueOf(100));
                }
                else if (lostg1.isChecked()&&onetwo1.isChecked()){
                    sum1=sum1;
                    sum2=sum2;
                    values.put(FIRST_COLUMN,String.valueOf(0));
                }
                else if (lostt1.isChecked()&&onetwo2.isChecked()){
                    sum1=sum1-100;
                    values.put(FIRST_COLUMN,String.valueOf(-100));
                }
                else if (lostg1.isChecked()&&onetwo2.isChecked()){
                    sum1=sum1-200;
                    values.put(FIRST_COLUMN,String.valueOf(-200));
                }
                else if (onetwo1.isChecked()){
                    sum1=sum1+200;
                    sum2=sum2;
                    values.put(FIRST_COLUMN,String.valueOf(200));
                }
                else if (tichu1.isChecked()){
                    sum1=sum1+tp1+100;
                    values.put(FIRST_COLUMN,String.valueOf(tp1+100));
                }
                else if (grand1.isChecked()){
                    sum1=sum1+tp1+200;
                    values.put(FIRST_COLUMN,String.valueOf(tp1+200));
                }
                else if (lostt1.isChecked()){
                    sum1=sum1+tp1-100;
                    values.put(FIRST_COLUMN,String.valueOf(tp1-100));
                }
                else if (lostg1.isChecked()){
                    sum1=sum1+tp1-200;
                    values.put(FIRST_COLUMN,String.valueOf(tp1-200));
                }
                else{
                    if (onetwo2.isChecked()){
                        sum1=sum1;
                        values.put(FIRST_COLUMN,String.valueOf(0));
                    }
                    else{
                        sum1=sum1+tp1;
                        values.put(FIRST_COLUMN,team1);}
                }
                if (tichu2.isChecked()&&onetwo2.isChecked()){
                    sum2=sum2+300;
                    sum1=sum1;
                    values.put(SECOND_COLUMN,String.valueOf(300));
                }
                else if (grand2.isChecked()&&onetwo2.isChecked()){
                    sum2=sum2+400;
                    sum1=sum1;
                    values.put(SECOND_COLUMN,String.valueOf(400));
                }
                else if (lostt2.isChecked()&&onetwo2.isChecked()){
                    sum2=sum2+100;
                    sum1=sum1;
                    values.put(SECOND_COLUMN,String.valueOf(100));
                }
                else if (lostg2.isChecked()&&onetwo2.isChecked()){
                    sum2=sum2;
                    sum1=sum1;
                    values.put(SECOND_COLUMN,String.valueOf(0));
                }
                else if (lostt2.isChecked()&&onetwo1.isChecked()){
                    sum2=sum2-100;
                    values.put(SECOND_COLUMN,String.valueOf(-100));
                }
                else if (lostg2.isChecked()&&onetwo1.isChecked()){
                    sum2=sum2-200;
                    values.put(SECOND_COLUMN,String.valueOf(-200));
                }
                else if(onetwo2.isChecked()){
                    sum2=sum2+200;
                    sum1=sum1;
                    values.put(SECOND_COLUMN,String.valueOf(200));
                }
                else if (tichu2.isChecked()){
                    sum2=sum2+tp2+100;
                    values.put(SECOND_COLUMN,String.valueOf(tp2+100));
                }
                else if (grand2.isChecked()){
                    sum2=sum2+tp2+200;
                    values.put(SECOND_COLUMN,String.valueOf(tp2+200));
                }
                else if (lostt2.isChecked()){
                    sum2=sum2+tp2-100;
                    values.put(SECOND_COLUMN,String.valueOf(tp2-100));
                }
                else if (lostg2.isChecked()){
                    sum2=sum2+tp2-200;
                    values.put(SECOND_COLUMN,String.valueOf(tp2-200));
                }
                else{
                    if (onetwo1.isChecked()){
                        sum2=sum2;
                        values.put(SECOND_COLUMN,String.valueOf(0));
                    }
                    else{
                        sum2=sum2+tp2;
                        values.put(SECOND_COLUMN,team2);
                    }
                }

                list.add(values);
                AdapterActivity adapter=new AdapterActivity(MainActivity.this,list);
                show.setAdapter(adapter);
                score1.setText(Integer.toString(sum1));
                score2.setText(Integer.toString(sum2));
                team1points.setText("");
                team2points.setText("");

                if (sum1 >= 1000) {
                    Context context = getApplicationContext();
                    CharSequence text = "TEAM1 WON";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if (sum2>=1000){
                    Context context = getApplicationContext();
                    CharSequence text = "TEAM2 WON";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                if (tichu1.isChecked()){
                    tichu1.toggle();
                }
                if (grand1.isChecked()){
                    grand1.toggle();
                }
                if (tichu2.isChecked()){
                    tichu2.toggle();
                }
                if (grand2.isChecked()){
                    grand2.toggle();
                }
                if (lostt1.isChecked()){
                    lostt1.toggle();
                }
                if (lostg1.isChecked()){
                    lostg1.toggle();
                }
                if (lostt2.isChecked()){
                    lostt2.toggle();
                }
                if (lostg2.isChecked()){
                    lostg2.toggle();
                }
                if(onetwo1.isChecked()){
                    onetwo1.toggle();
                }
                if (onetwo2.isChecked()){
                    onetwo2.toggle();
                }
                team1points.setText(String.valueOf(0));
                team2points.setText(String.valueOf(0));
                show.setSelection(show.getAdapter().getCount()-1);
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String team1=team1points.getText().toString();
                String team2=team2points.getText().toString();
                Integer tp1 = Integer.valueOf(team1);
                Integer tp2 = Integer.valueOf(team2);
                sum1 = sum1 - tp1;
                sum2 = sum2 - tp2;
                score1.setText(Integer.toString(sum1));
                score2.setText(Integer.toString(sum2));
                HashMap<String,String> values= new HashMap<String, String>();
                values.put(FIRST_COLUMN,team1);
                values.put(SECOND_COLUMN,team2);
                list.add(values);
                AdapterActivity adapter=new AdapterActivity(MainActivity.this,list);
                show.setAdapter(adapter);
                team1points.setText("");
                team2points.setText("");
                team1points.setText(String.valueOf(0));
                team2points.setText(String.valueOf(0));
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sum1=0;
                sum2=0;
                score1.setText(Integer.toString(sum1));
                score2.setText(Integer.toString(sum2));
                list.removeAll(list);
                AdapterActivity adapter=new AdapterActivity(MainActivity.this,list);
                show.setAdapter(adapter);
                team1points.setText("");
                team2points.setText("");
                team1points.setText(String.valueOf(0));
                team2points.setText(String.valueOf(0));
                if (tichu1.isChecked()){
                    tichu1.toggle();
                }
                if (grand1.isChecked()){
                    grand1.toggle();
                }
                if (tichu2.isChecked()){
                    tichu2.toggle();
                }
                if (grand2.isChecked()){
                    grand2.toggle();
                }
                if (lostt1.isChecked()){
                    lostt1.toggle();
                }
                if (lostg1.isChecked()){
                    lostg1.toggle();
                }
                if (lostt2.isChecked()){
                    lostt2.toggle();
                }
                if (lostg2.isChecked()){
                    lostg2.toggle();
                }
                if(onetwo1.isChecked()){
                    onetwo1.toggle();
                }
                if (onetwo2.isChecked()){
                    onetwo2.toggle();
                }
            }

        });
    }
}
