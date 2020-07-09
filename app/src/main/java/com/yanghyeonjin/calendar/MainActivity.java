package com.yanghyeonjin.calendar;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.yanghyeonjin.calendar.cosmocalendar.CosmoCalendarActivity;
import com.yanghyeonjin.calendar.kizitonwose.CalendarViewActivity;
import com.yanghyeonjin.calendar.materialcalendarview.MaterialCalendarActivity;
import com.yanghyeonjin.calendar.share.ShareActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCosmo, btnCalendarView, btnMaterial, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 키 해시 알아내기
        getHashKey();

        // 아이디 연결
        btnCosmo = findViewById(R.id.btnCosmoCalendar);
        btnCalendarView = findViewById(R.id.btnCalendarView);
        btnMaterial = findViewById(R.id.btnMaterialCalendar);
        btnShare = findViewById(R.id.btnShare);



        // 클릭 리스너
        btnCosmo.setOnClickListener(this);
        btnCalendarView.setOnClickListener(this);
        btnMaterial.setOnClickListener(this);
        btnShare.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCosmoCalendar:
                Intent cosmoIntent = new Intent(MainActivity.this, CosmoCalendarActivity.class);
                startActivity(cosmoIntent);
                break;
            case R.id.btnCalendarView:
                Intent calendarViewIntent = new Intent(MainActivity.this, CalendarViewActivity.class);
                startActivity(calendarViewIntent);
                break;
            case R.id.btnMaterialCalendar:
                Intent materialIntent = new Intent(MainActivity.this, MaterialCalendarActivity.class);
                startActivity(materialIntent);
                break;
            case R.id.btnShare:
                Intent shareIntent = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(shareIntent);
                break;
        }
    }

    private void getHashKey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.yanghyeonjin.calendar", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("HASH KEY","key_hash="+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}