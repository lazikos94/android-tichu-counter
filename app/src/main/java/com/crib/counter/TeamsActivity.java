package com.crib.counter;

import android.content.Context;
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

public class TeamsActivity extends Activity {
    EditText name1,name2;
    Button done;

    protected void onCreate(Bundle savedInstanceState){
         requestWindowFeature(Window.FEATURE_NO_TITLE);
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_teams);

         name1=(EditText)findViewById(R.id.teamname1);
         name2=(EditText)findViewById(R.id.teamname2);
         done=(Button) findViewById(R.id.done);
         name1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    name2.requestFocus();
                }
                return false;
            }
        });

         done.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(TeamsActivity.this, MainActivity.class);
                 String tname1=name1.getText().toString();
                 String tname2=name2.getText().toString();
                 intent.putExtra("teamname1",tname1);
                 intent.putExtra("teamname2",tname2);
                 startActivity(intent);

             }
         });
    }


}
