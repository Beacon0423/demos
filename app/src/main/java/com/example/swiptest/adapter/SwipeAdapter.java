package com.example.swiptest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.swiptest.R;
import com.example.swiptest.javabean.Test;

import java.util.List;

public class SwipeAdapter extends BaseAdapter {

    private List<Object> list;
    private ClickListener listener;
    private LayoutInflater inflater;
    float downX = 0f;
    float upX = 0f;
    boolean result;

    public SwipeAdapter(Context context, List<Object> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (list != null) {
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ItemContent hold;
        if (v == null) {
            v = inflater.inflate(R.layout.listview_item, viewGroup, false);
            hold = new ItemContent();
            hold.item = v.findViewById(R.id.item);
            hold.id = v.findViewById(R.id.tv_id);
            hold.content = v.findViewById(R.id.tv_content);
            v.setTag(hold);
        }
        hold = (ItemContent) v.getTag();
        Test test = (Test) list.get(i);
        hold.id.setText(String.valueOf(test.getId()));
        hold.content.setText(test.getContent());
        hold.item.setOnClickListener(v1 -> listener.itemClick(i));

        hold.item.setOnTouchListener((v1, event) -> {
            switch (event.getAction()) {
                case (MotionEvent.ACTION_DOWN): {
                    downX = event.getX();
                    result = true;
                    Log.e("TAG", "getView: DOWN");

                    return true;
                }
                case (MotionEvent.ACTION_MOVE): {
                    result = true;
                    Log.e("TAG", "getView: MOVE");

                    return true;
                }
                case (MotionEvent.ACTION_UP): {
                    upX = event.getX();
                    result = true;
                    Log.e("TAG", "getView: UP");

                    return true;
                }
                default: {
                    Log.e("TAG", "getView: DEFAULT");
                }
            }
            return false;
        });

        return v;
    }

    static class ItemContent {
        LinearLayout item;
        TextView id;
        TextView content;
    }

    public interface ClickListener {
        void itemClick(int pos);

        void itemSwipe(int pos);
    }
}
