package com.yanghyeonjin.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.yanghyeonjin.calendar.cosmocalendar.CosmoCalendarActivity;
import com.yanghyeonjin.calendar.kizitonwose.CalendarViewActivity;
import com.yanghyeonjin.calendar.materialcalendarview.MaterialCalendarActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCosmo, btnCalendarView, btnMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 아이디 연결
        btnCosmo = findViewById(R.id.btnCosmoCalendar);
        btnCalendarView = findViewById(R.id.btnCalendarView);
        btnMaterial = findViewById(R.id.btnMaterialCalendar);



        // 클릭 리스너
        btnCosmo.setOnClickListener(this);
        btnCalendarView.setOnClickListener(this);
        btnMaterial.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.e("Main", String.valueOf(view.getId()));

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
        }
    }
}