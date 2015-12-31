package com.hipad.servicetool;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LegalPage extends Activity {
    private static String TAG = "ServiceTool.LegalPage";
    Button mBtnAccept ;
    Button mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legal_page);
        mBtnAccept = (Button)findViewById( R.id.Button_instruct);
        mBtnCancel = (Button)findViewById( R.id.Button_Cancel);
        mBtnAccept.setOnClickListener(mBtnAcceptOnClick);
        mBtnCancel.setOnClickListener(mBtnCancelOnClick);
    }
    private View.OnClickListener  mBtnCancelOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LegalPage.this.finish();
            finish();
        }
    };
    private View.OnClickListener  mBtnAcceptOnClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(LegalPage.this, FunctionList.class);
            startActivity(intent);
            LegalPage.this.finish();
            finish();
        }
    };
    public void closeApp(View paramView) {
        finish();
    }
}
