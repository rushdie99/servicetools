package com.hipad.servicetool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by sanndy on 2015/12/29.
 */
public class More extends Activity {
    private static String TAG = "ServiceTool.More";
    Button mBtnBack;
    Button mBtnAbout;
    Button mBtnRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more);

        mBtnBack=(Button) findViewById(R.id.btn_back);
        mBtnAbout=(Button) findViewById(R.id.btn_about_more);

        mBtnAbout.setOnClickListener(mBtnAboutOnClick);
        mBtnBack.setOnClickListener(mBtnBackOnClick);


    }
    private View.OnClickListener  mBtnAboutOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(More.this, About.class);
            startActivity(intent);
            More.this.finish();
            finish();
        }
    };
    private View.OnClickListener  mBtnBackOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(More.this, FunctionList.class);
            startActivity(intent);
            More.this.finish();
            finish();
        }
    };
}
