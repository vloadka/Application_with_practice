// ButtonConstruct.java
package com.example.mytestapplication;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;

import java.util.concurrent.atomic.AtomicBoolean;

public class ButtonConstruct {

    public static final int BUTTON_TYPE_NORMAL = 0;
    public static final int BUTTON_TYPE_PRESSABLE = 1;
    public static final int BUTTON_TYPE_DISABLED = 2;

    private Context context;

    public ButtonConstruct(Context context) {
        this.context = context;
    }

    public void configureButton(Button button, ButtonModel buttonModel) {
        int buttonType = buttonModel.getButtonType();
        switch (buttonType) {
            case BUTTON_TYPE_NORMAL:
                button.setEnabled(true);
                button.setBackgroundColor(ContextCompat.getColor(context, R.color.white_spec));
                break;

            case BUTTON_TYPE_PRESSABLE:
                button.setEnabled(true);
                int normalColor = ContextCompat.getColor(context, R.color.white_spec);
                int pressedColor = ContextCompat.getColor(context, R.color.green_spec);

                AtomicBoolean isPressed = new AtomicBoolean(false);

                button.setBackgroundColor(normalColor);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isPressed.get()) {
                            button.setBackgroundColor(pressedColor);
                        } else {
                            button.setBackgroundColor(normalColor);
                        }
                        isPressed.set(!isPressed.get());
                    }
                });
                break;

            case BUTTON_TYPE_DISABLED:
                button.setEnabled(false);
                button.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_spec));
                break;
        }
    }

    public void configureButton(ImageButton button, ButtonModel buttonModel) {
        int buttonType = buttonModel.getButtonType();
        switch (buttonType) {
            case BUTTON_TYPE_NORMAL:
                button.setEnabled(true);
                button.setBackgroundColor(ContextCompat.getColor(context, R.color.blue));
                break;

            case BUTTON_TYPE_PRESSABLE:
                button.setEnabled(true);
                int normalColor = ContextCompat.getColor(context, R.color.blue);
                int pressedColor = ContextCompat.getColor(context, R.color.green);

                AtomicBoolean isPressed = new AtomicBoolean(false);

                button.setBackgroundColor(normalColor);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isPressed.get()) {
                            button.setBackgroundColor(pressedColor);
                        } else {
                            button.setBackgroundColor(normalColor);
                        }
                        isPressed.set(!isPressed.get());
                    }
                });
                break;

            case BUTTON_TYPE_DISABLED:
                button.setEnabled(false);
                button.setBackgroundColor(ContextCompat.getColor(context, R.color.silver));
                break;
        }
    }

}
