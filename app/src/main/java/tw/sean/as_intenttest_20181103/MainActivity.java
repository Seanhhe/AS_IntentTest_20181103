package tw.sean.as_intenttest_20181103;

/*
 * 01.官網文件：https://developer.android.com/guide/components/intents-common?hl=zh-tw
 *
 * 02.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private UIHandler uiHandler;
    private Parcelable thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test1(View view) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, "OK")
                .putExtra(AlarmClock.EXTRA_HOUR, 12)
                .putExtra(AlarmClock.EXTRA_MINUTES, 0)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);//跳出通知視窗
        //把intent解析是否有人能處理
        //設定取得權限<uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void test2(View view) {

            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, "聚餐")
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, "漢來")
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis()+(1000*60*60))
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

    }


    public void test3(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            thumbnail = data.getParcelableExtra("data");
            Uri fullPhotoUri = data.getData();
            Log.v("brad", fullPhotoUri.toString());
            if (thumbnail != null){
                uiHandler.sendEmptyMessage(0);
            }else{
                Log.v("brad","null");
            }

            File file = new File(fullPhotoUri.getPath());
            Log.v("brad", getPath(fullPhotoUri));
        }
    }

    public String getPath(Uri uri){

    }


}

Private class UIHandler extends Handler {

}
