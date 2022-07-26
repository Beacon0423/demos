package com.example.swiptest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiptest.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemHold> {

    private Context context;
    private List<Object> list;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ItemHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);
        return new ItemHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHold holder, int position) {
        holder.hide.setOnClickListener(v -> {
            Log.e("TAG", "Click Hide" );
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ItemHold extends RecyclerView.ViewHolder {

        private LinearLayout item;
        private TextView id;
        private TextView content;
        private TextView hide;

        public ItemHold(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            id = itemView.findViewById(R.id.tv_id);
            content = itemView.findViewById(R.id.tv_content);
            hide = itemView.findViewById(R.id.tv_show);
        }
    }
}
