package com.techindustan.myfirstlibrary;

import android.content.Context;
import android.content.res.TypedArray;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyView extends LinearLayout {


    List<String> alList = new ArrayList<>();
    TextView tvSelect;
    CardView cardView;
    int textColor, bg_color;
    String hintText;
    int backgroundColor, hintTextColor, iconSize;
    int icon;
    boolean isRounded = false;
    float textsize = 12.0f;

    AttributeSet attrs;
    TypedArray a;
    float bg_elevation;
    int bg_radius;
    //ItemClick itemClick;
    int position = 0;


    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        tvSelect.setBackgroundColor(backgroundColor);
    }


    public AttributeSet getAttribute() {
        return attrs;
    }

    int dialogTheme = R.style.Theme_AppCompat;

    public MyView(Context context) {
        super(context);
        initialize(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        a = context.obtainStyledAttributes(attrs,
                R.styleable.customlibrary, 0, 0);
        this.attrs = attrs;

        textColor = a.getColor(R.styleable.customlibrary_titleText, ContextCompat.getColor(context, R.color.text_color));
        hintTextColor = a.getColor(R.styleable.customlibrary_hint_text_color, ContextCompat.getColor(context, R.color.text_color));
        textsize = a.getFloat(R.styleable.customlibrary_text_size, 20.0f);
        backgroundColor = a.getColor(R.styleable.customlibrary_bg_color, ContextCompat.getColor(context, R.color.bg_color));
        isRounded = a.getBoolean(R.styleable.customlibrary_bg_color, false);
        bg_elevation = a.getFloat(R.styleable.customlibrary_bg_elevation, 0.0f);
        bg_radius = a.getInt(R.styleable.customlibrary_bg_radius, 10);

        // Log.e("backgtoundColor", a.getBoolean(R.styleable.customlibrary_dialog_full_screen, false) + "");
        // bg_color =a.getInt(R.drawable.my_custom_color, 0);


        // textColor = context.getResources().getColor(R.color.text_color);
        initialize(context);


    }

    private void initialize(final Context context) {
        View v = inflate(context, R.layout.first_library_layout, this);
        tvSelect = v.findViewById(R.id.tvSelect);
        cardView = v.findViewById(R.id.cardView);
        tvSelect.setTextColor(textColor);
        cardView.setCardElevation(bg_elevation);
        cardView.setRadius(bg_radius);

        tvSelect.setTextSize(textsize);

        cardView.setBackgroundColor(backgroundColor);

        tvSelect.setHintTextColor(hintTextColor);
        tvSelect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //0Log.e("backgtoundColor", a.getBoolean(R.styleable.customlibrary_dialog_full_screen, false) + "");

                MyDialog dialog = new MyDialog(context, R.style.MyDialogTheme, a, (ArrayList<String>) alList);
                dialog.show();


            }
        });
        //a.recycle();

    }


    public void setHintTheme(String hintText) {
        this.hintText = hintText;
        tvSelect.setHint(hintText);
    }

  public   void setArrayList(ArrayList<String> alList) {

        this.alList.addAll(alList);

    }


    public int getDialogTheme() {

        return dialogTheme;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        tvSelect.setTextColor(textColor);
    }

    public void setDialogTheme(int dialogTheme) {
        this.dialogTheme = dialogTheme;
    }


}
