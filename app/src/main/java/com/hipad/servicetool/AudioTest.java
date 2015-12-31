package com.hipad.servicetool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by sanndy on 2015/12/29.
 */
public class AudioTest extends Activity {
    private static final String TAG = "AudioTest";
    private Button mReceiver_test_button;
    private MediaPlayer mPlayer;
    private AudioManager mAudioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_test_instruction);
    }
    public void onBackPressed() {
        setContentView(R.layout.audio_test_receiver);

        mReceiver_test_button= (Button) findViewById(R.id.btn_audio_receiver_test);

        mReceiver_test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.audiotesting_receiver_hint_page);
                receiverTest();
            }
        });

    }

    private void receiverTest() {
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        }
        mAudioManager.setMode(mAudioManager.MODE_IN_CALL);
        if(mAudioManager.isWiredHeadsetOn()) {
            AlertDialog.Builder headsetDialog =new AlertDialog.Builder(this)
                    .setMessage(R.string.audio_headset_hint);
            headsetDialog.setPositiveButton(R.string.Pass_msg, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    receiverTest();
                }
            });
             headsetDialog.show();
        }
        else {

        }


    }
    private void startPlay(int resid) {
        AssetFileDescriptor afd = this.getResources().openRawResourceFd(resid);
        if (afd == null) return;
        stop();
        this.mPlayer = new MediaPlayer();
        try {
            this.mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            this.mPlayer.setOnCompletionListener(this);
            this.mPlayer.setOnErrorListener(this);
            this.mPlayer.setVolume(150.0F, 150.0F);
            this.mPlayer.prepare();
            this.mPlayer.start();
            return;
        }
        catch (Throwable localThrowable)
        {
            Log.i(this.TAG, "startPlayback .... SDCARD_ACCESS_ERROR - IOException:" + localThrowable.toString());
            this.mPlayer = null;
        }
    }


}

