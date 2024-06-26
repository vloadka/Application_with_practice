// MainActivity.java
package com.example.mytestapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private View square;
    private View arrow;
    private int screenWidth;
    private int squareWidth;

    private Button myButton;
    private Button myButton2;
    private Button myButton3;
    private ButtonConstruct buttonConstruct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainLayout = findViewById(R.id.main);

        ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        square = findViewById(R.id.square);
        arrow = findViewById(R.id.arrow);
        SeekBar seekBar = findViewById(R.id.seekBar);

        screenWidth = getResources().getDisplayMetrics().widthPixels - 35;
        squareWidth = getResources().getDimensionPixelSize(R.dimen.square_size);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                long duration = 2000 - (progress * 20L);
                startSquareAnimation(duration);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        startSquareAnimation(2000);

        myButton = findViewById(R.id.my_button);
        myButton2 = findViewById(R.id.my_button2);
        myButton3 = findViewById(R.id.my_button3);

        buttonConstruct = new ButtonConstruct(this);

        ButtonModel buttonModel1 = new ButtonModel(ButtonConstruct.BUTTON_TYPE_PRESSABLE);
        ButtonModel buttonModel2 = new ButtonModel(ButtonConstruct.BUTTON_TYPE_PRESSABLE);
        ButtonModel buttonModel3 = new ButtonModel(ButtonConstruct.BUTTON_TYPE_DISABLED);

        buttonConstruct.configureButton(myButton, buttonModel1);
        buttonConstruct.configureButton(myButton2, buttonModel2);
        buttonConstruct.configureButton(myButton3, buttonModel3);
    }

    private void startSquareAnimation(long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(square, "translationX", -20, screenWidth - squareWidth);
        animator.setDuration(duration);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();

            if (animatedValue < 0) {
                animatedValue = 0;
            } else if (animatedValue > screenWidth - squareWidth) {
                animatedValue = screenWidth - squareWidth;
            }

            square.setTranslationX(animatedValue);

            float squareCenterX = animatedValue + square.getWidth() / 2f;
            float arrowTopX = arrow.getX() + arrow.getWidth() / 2f;
            float arrowTopY = arrow.getY();

            float dx = squareCenterX - arrowTopX;

            float scaleCoefficient = 0.5f;

            float angle = -(float) Math.toDegrees(Math.atan2(dx * scaleCoefficient, arrow.getHeight()));

            arrow.setPivotX(arrow.getWidth() / 2f);
            arrow.setPivotY(0);
            arrow.setRotation(angle);
        });
        animator.start();
    }
}
