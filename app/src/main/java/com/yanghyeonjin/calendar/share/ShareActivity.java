package com.yanghyeonjin.calendar.share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.yanghyeonjin.calendar.R;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "공유하고 싶은 text");

        Intent chooser = Intent.createChooser(intent, "공유하기");
        startActivity(chooser);
    }
}