package com.vishal.baseproject.view.baseClasses.recyclerViewBase;

/**
 * Created by shiva on 25/5/18.
 */

import android.databinding.BindingAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CustomBindingAdapter {

    String[] arr = {"recyclerLinearAdapter", "layout", "onItemClickListener"};

    @BindingAdapter(value ={"recyclerLinearAdapter", "layout", "onItemClickListener"}, requireAll = false)
    public static void setRecyclerLinearAdapter(RecyclerView view,
                                                RecyclerBindingList object,
                                                int layout,
                                                final RecyclerCallback callback) {

        LinearLayoutManager layoutManager  = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(layout,object.getItemsList(),callback);
        view.setAdapter(adapter);
        object.setAdapter(adapter);
    }
}