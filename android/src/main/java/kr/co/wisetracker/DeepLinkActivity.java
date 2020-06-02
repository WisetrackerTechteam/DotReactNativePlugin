package kr.co.wisetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sdk.wisetracker.new_dot.open.DOT;

/**
 * 플러그인에서 딥링크 사용시 SDK 호출 처리해주는 Activity
 * 고객사 안드로이드 프로젝트의 AndroidManifest.xml 파일에 등록해놓은 스키마와 호스트 정보로 해당 Activity 진입시 SDK 호출 후 메인 화면으로 이동
 */
public class DeepLinkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSDK(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setSDK(intent);
    }

    private void setSDK(Intent intent) {
        try {
            Log.i("[wisetracker]", "set sdk by deep link!!");
            if (intent == null || intent.getData() == null) {
                Log.i("[wisetracker]", "intent is null !!");
                return;
            }
            DOT.setDeepLink(intent);
            moveToMainActivity();
        } catch (Exception e) {
            Log.e("[wisetracker]", "set sdk error !!", e);
        }
    }

    private void moveToMainActivity() {
        try {
            Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.i("[wisetracker]", "move to main activity error !!", e);
        }
    }

}