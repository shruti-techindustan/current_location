package com.techindustan.myfirstlibrary;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyDialog extends Dialog {
    MyCustomAdapter adapter;
    RecyclerView lv;
    List<String> alList = new ArrayList<>();
    SearchView etSearch;
    Context context;

    LinearLayout llParent;
    int bg_color, dialog_list_color, dialog_title_color, dialog_title_bg_color, list_item_color;
    TextView tvTitle;
    String dialogTextTitle = "Dialog Title";
    boolean dialog_full_screen = true, show_dialog_title, isDivider, isSearchBarShowing;


    public MyDialog(@NonNull Context context, int themeResId, TypedArray attrs, ArrayList<String> alList) {
        super(context, themeResId);
        this.context = context;
        this.alList = alList;

        bg_color = attrs.getColor(R.styleable.customlibrary_dialog_bg_color, ContextCompat.getColor(context, R.color.dialog_bg_color));
        dialog_full_screen = attrs.getBoolean(R.styleable.customlibrary_dialog_full_screen, true);
        dialog_list_color = attrs.getColor(R.styleable.customlibrary_dialog_list_color, ContextCompat.getColor(context, R.color.dialog_list_color));
        show_dialog_title = attrs.getBoolean(R.styleable.customlibrary_show_dialog_title, true);
        dialogTextTitle = attrs.getString(R.styleable.customlibrary_dialog_title);
        dialog_title_color = attrs.getColor(R.styleable.customlibrary_dialog_title_color, ContextCompat.getColor(context, R.color.white));
        dialog_title_bg_color = attrs.getColor(R.styleable.customlibrary_dialog_title_bg_color, ContextCompat.getColor(context, R.color.dialog_default_title));
        isDivider = attrs.getBoolean(R.styleable.customlibrary_is_divider, true);
        isSearchBarShowing = attrs.getBoolean(R.styleable.customlibrary_show_search_bar, true);
        list_item_color = attrs.getColor(R.styleable.customlibrary_list_item_color, ContextCompat.getColor(context, R.color.dialog_default_title));


        showFullScreen();
    }


    void showFullScreen() {

        if (dialog_full_screen) {
            WindowManager.LayoutParams params = this.getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            getWindow().setAttributes(params);
        } else {
            WindowManager.LayoutParams params = this.getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getWindow().setAttributes(params);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        //getWindow().setAttributes(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        if (dialog_full_screen) {
            WindowManager.LayoutParams params = this.getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            getWindow().setAttributes(params);
        } else {
            WindowManager.LayoutParams params = this.getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getWindow().setAttributes(params);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_selected_item);
        llParent = findViewById(R.id.llParent);
        etSearch = findViewById(R.id.etSearch);
        tvTitle = findViewById(R.id.tvTitle);

        etSearch.setIconifiedByDefault(true);
        etSearch.setFocusable(true);
        etSearch.setIconified(false);
        etSearch.requestFocusFromTouch();

        lv = findViewById(R.id.lv);
        lv.setBackgroundColor(dialog_list_color);
        tvTitle.setText(dialogTextTitle);
        tvTitle.setTextColor(dialog_title_color);
        tvTitle.setBackgroundColor(dialog_title_bg_color);


        if (isSearchBarShowing) {
            etSearch.setVisibility(View.VISIBLE);
        } else {

            etSearch.setVisibility(View.GONE);
        }
        if (show_dialog_title) {
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }


        llParent.setBackgroundColor(bg_color);
        etSearch.onActionViewExpanded();

        etSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) {
                    adapter.filterData(newText.toString());
                }

                return false;
            }
        });


        adapter = new MyCustomAdapter(alList, context, MyDialog.this,list_item_color);
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(getContext()));
        if (isDivider)
            lv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

    }

}
