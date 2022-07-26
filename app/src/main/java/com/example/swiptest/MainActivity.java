package com.example.swiptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.swiptest.adapter.RecyclerAdapter;
import com.example.swiptest.adapter.SwipeAdapter;
import com.example.swiptest.databinding.ActivityMainBinding;
import com.example.swiptest.javabean.Test;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
/*    private SwipeAdapter adapter;
    private SwipeAdapter.ClickListener listener;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setAdapter();
    }

    private void setAdapter(){
        List<Object> list = new ArrayList<>();
        list.add(new Test(1, "第一个"));
        list.add(new Test(2, "第二个"));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.slide.setLayoutManager(manager);
        RecyclerAdapter adapter = new RecyclerAdapter(this);
        adapter.setList(list);
        binding.slide.setAdapter(adapter);
/*        listener = new SwipeAdapter.ClickListener() {
            @Override
            public void itemClick(int pos) {
                Log.e("TAG", "itemClick: "+pos);
            }

            @Override
            public void itemSwipe(int pos) {
                Log.e("TAG", "itemSwipe: "+pos);
            }
        };

        adapter = new SwipeAdapter(this, list);
        adapter.setListener(listener);
        binding.listview.setAdapter(adapter);*/
    }
}