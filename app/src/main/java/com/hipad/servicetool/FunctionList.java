package com.hipad.servicetool;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import android.os.Handler;
import android.os.HandlerThread;


public class FunctionList extends Activity{
    private static String TAG = "ServiceTool.FunctionList";
    public static final int RESULT_FAIL = 3;
    public static final int RESULT_PASS = 2;
    public static final int RESULT_TIMEOUT = 4;
    public static final int FUNCTIONLIST = 1000;

    Button mBtnAbout;
    Button mBtnSelectAll;
    Button mBtnRun;
    Button mBtnMore;
    List<Field> IDList= new ArrayList<>();
    List<CheckBox>  CheckBoxList= new ArrayList<>();
    List<String> RunTaskList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.functionlist);

        setCheckBoxList();
        mBtnAbout =(Button)findViewById(R.id.btn_about);
        mBtnSelectAll = (Button)findViewById(R.id.btn_select_all);
        mBtnMore = (Button)findViewById(R.id.btn_more);
        mBtnRun = (Button)findViewById(R.id.btn_run);

        mBtnRun.setOnClickListener(mmBtnRunOnClick);
        mBtnAbout.setOnClickListener(mBtnAboutOnClick);
        mBtnSelectAll.setOnClickListener(mBtnSelectAllOnClick);
        mBtnMore.setOnClickListener(mBtnMoreOnClick);


        Toast.makeText(this.getBaseContext(),R.string.function_instruct,
                    Toast.LENGTH_SHORT).show();


    }

    private Handler mHandler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case FUNCTIONLIST:
                    //處理少量資訊或UI

                    break;
            }
        }
    };
    Intent Currentintent=null;
    Runnable task = new Runnable() {
        public void run(){
            for (String ActionName:RunTaskList
                 ) {

                Currentintent = new Intent();
                Currentintent.setClassName(getPackageName(), getPackageName() +"."+  ActionName);
                startActivityForResult(Currentintent, FUNCTIONLIST);

                Message msg = new Message();
                msg.what = FUNCTIONLIST;
                mHandler.sendMessage(msg);


            }
        }
    };



    @Override
    protected void onDestroy(){

        super.onDestroy();
        if(mHandler!=null) {
            mHandler.removeCallbacks(task);
        }



    }
    private  void setCheckBoxList() {

        IDList.clear();
            Field[] fIDs =Tool._DumpAllResourceIDs(R.id.class);

            for (Field fid:fIDs) {
                    if(fid.getName().contains("_FLTitle"))
                        {
                            IDList.add(fid);
                        }
                    }
        for (Field fid :IDList
             ) {
            try {
                int nid = fid.getInt(null);
                CheckBox checkbox = (CheckBox) findViewById(nid);
                CheckBoxList.add(checkbox);
            }
            catch(Exception e) {
                return;
            }

        }


   }
    private static final int MSG_UPLOAD_OK=1;

    private View.OnClickListener  mmBtnRunOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            for (final CheckBox chkbox:CheckBoxList) {
                if(chkbox.isChecked()) {
                    RunTaskList.add(chkbox.getText().toString().replace(" ", ""));
                }
            }
            mHandler.post(task);
            mBtnSelectAll.setText("Select All");


        }
    };

    private View.OnClickListener  mBtnMoreOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(FunctionList.this, More.class);
            startActivity(intent);
            FunctionList.this.finish();
            finish();
        }
    };


    private View.OnClickListener  mBtnSelectAllOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(mBtnSelectAll.getText().equals("Select All")) {
                try {
                    for (Field f : IDList) {
                        int nid = f.getInt(null);
                        CheckBox checkbox = (CheckBox) findViewById(nid);
                        checkbox.setChecked(true);
                    }
                    mBtnSelectAll.setText("UnSelect All");

                    return;
                } catch (Exception e) {

                    Log.d("exception",this.getClass().getName()+":"+e.getMessage());
                    return;
                }
            }
            else {
                    try {
                        for (Field f : IDList) {
                            int nid = f.getInt(null);
                            CheckBox checkbox = (CheckBox) findViewById(nid);
                            checkbox.setChecked(false);
                        }
                        mBtnSelectAll.setText("Select All");
                        return;
                    }catch (Exception e) {
                        Log.d("exception",this.getClass().getName()+":"+e.getMessage());
                        return;
                    }

                    }
            }

    };
    private View.OnClickListener  mBtnAboutOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(FunctionList.this, About.class);
            startActivity(intent);
            FunctionList.this.finish();
            finish();
        }
    };



}
