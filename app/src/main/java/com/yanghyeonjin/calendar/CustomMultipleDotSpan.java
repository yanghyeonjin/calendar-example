package com.yanghyeonjin.calendar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import androidx.annotation.NonNull;

public class CustomMultipleDotSpan implements LineBackgroundSpan {

    private final float radius;
    private int[] color = new int[0];
    private static final float DEFAULT_RADIUS = 5f;

    public CustomMultipleDotSpan() {
        this.radius = DEFAULT_RADIUS;
        this.color[0] = 0;
    }

    public CustomMultipleDotSpan(int color) {
        this.radius = DEFAULT_RADIUS;
        this.color[0] = 0;
    }

    public CustomMultipleDotSpan(float radius) {
        this.radius = radius;
        this.color[0] = 0;
    }

    public CustomMultipleDotSpan(float radius, int[] color) {
        this.radius = radius;
        this.color = color;
    }



    @Override
    public void drawBackground(@NonNull Canvas canvas, @NonNull Paint paint, int left, int right, int top, int baseline, int bottom, @NonNull CharSequence charSequence, int start, int end, int lineNum) {
        int total = Math.min(color.length, 5); // color.length > 5 ? 5 : color.length;
        int leftMost = (total - 1) * -10;

        for (int i = 0; i < total; i++) {
            int oldColor = paint.getColor();
            if (color[i] != 0) {
                paint.setColor(color[i]);
            }
            canvas.drawCircle((left + right) / 2 - leftMost, bottom + radius, radius, paint);
            paint.setColor(oldColor);
            leftMost = leftMost + 20;
        }
    }
}
