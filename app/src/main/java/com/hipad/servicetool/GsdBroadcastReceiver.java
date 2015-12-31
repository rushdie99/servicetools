package com.hipad.servicetool;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
/**
 * Created by sanndy on 2015/12/28.
 */
public class GsdBroadcastReceiver extends BroadcastReceiver {
    private static String SECRET_CODE_ACTION = "android.provider.Telephony.SECRET_CODE";
    @Override
    public void onReceive(Context paramContext, Intent paramIntent) {
        if (paramIntent.getAction().equals(SECRET_CODE_ACTION)) {
            Intent localIntent = new Intent("android.intent.action.MAIN");
            localIntent.setClass(paramContext, LegalPage.class);

            paramContext.startActivity(localIntent);
        }
    }
}
