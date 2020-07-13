package com.yanghyeonjin.calendar.share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import com.yanghyeonjin.calendar.R;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Button btnShareDynamicLink = findViewById(R.id.btnShareDynamicLink);
        btnShareDynamicLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDynamicLinkClick();
            }
        });



        // 이메일 앱으로 공유하기
        Button btnShareEmail = findViewById(R.id.btnShareEmail);
        btnShareEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String deviceAppVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"guswls7081@gmail.com"});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "<" + getString(R.string.app_name) + " 문의 및 건의사항>");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "앱 버전 (AppVersion):" + deviceAppVersion + "\n기기명 (Device):\n안드로이드 OS (Android OS):\n내용 (Content):\n");
                    emailIntent.setType("message/rfc822");
                    startActivity(emailIntent);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Deep Link 생성
    // 사용자가 앱을 실행했을 때 실행시켜줄 딥링크 정의
    private Uri getPromotionDeepLink() {
        String promotionCode = "DF3DY1";

        // https://calendar.page.link/?code=DF3DY
        return Uri.parse("https://devyang.page.link/?code=" + promotionCode);
    }

    // Dynamic Link 생성
    private void onDynamicLinkClick() {
        FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(getPromotionDeepLink())
                .setDomainUriPrefix("https://devyang.page.link")
                .setAndroidParameters(
                        new DynamicLink.AndroidParameters.Builder("com.yanghyeonjin.calendar")
                                .setMinimumVersion(125)
                                .build())
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            Uri shortLink = task.getResult().getShortLink();
                            try {
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
                                intent.setType("text/plain");

                                startActivity(Intent.createChooser(intent, "공유하기"));
                            } catch (ActivityNotFoundException ignored) {

                            }
                        } else {
                            Log.e("ShareActivity", task.toString());
                        }
                    }
                });
    }
}