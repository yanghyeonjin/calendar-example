package com.yanghyeonjin.calendar.weekcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.yanghyeonjin.calendar.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TodoDetailActivity extends AppCompatActivity {
    private EditText etTodoDetail;
    private Button btnModify;

    private InputMethodManager imm;
    private GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        // 아이디 연결
        etTodoDetail = findViewById(R.id.etTodoDetail);
        btnModify = findViewById(R.id.btnModify);



        // 소프트 키보드 매니저
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);



        // 제스쳐 감지기
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                // 단순 클릭일 때에만 수정모드로 전환되도록
                // 버튼 상태 변경
                btnModify.setVisibility(View.VISIBLE);

                // EditText 쓰기모드로 변경
                etTodoDetail.setFocusableInTouchMode(true);
                etTodoDetail.setClickable(true);
                etTodoDetail.setFocusable(true);

                // 키보드 띄우기
                showKeyboard(etTodoDetail);
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });



        // 처음엔 읽기모드
        etTodoDetail.setClickable(false);
        etTodoDetail.setFocusable(false);


        // EditText 터치 이벤트 리스너
        etTodoDetail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });



        // 수정완료 클릭
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 상태 변경
                btnModify.setVisibility(View.INVISIBLE);

                // EditText 읽기모드로 변경
                etTodoDetail.setClickable(false);
                etTodoDetail.setFocusable(false);

                // 키보드 숨기기
                hideKeyboard(etTodoDetail);
            }
        });
    }


    // 소프트 키보드 show
    private void showKeyboard(EditText editText) {
        imm.showSoftInput(editText, 0);
    }

    // 소프트 키보드 hide
    private void hideKeyboard(EditText editText) {
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}