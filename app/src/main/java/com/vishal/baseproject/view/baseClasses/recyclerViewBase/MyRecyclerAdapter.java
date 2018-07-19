package com.vishal.baseproject.view.baseClasses.recyclerViewBase;



import android.util.Log;

import java.util.ArrayList;

public class MyRecyclerAdapter extends BindingBaseAdapter {

    private int resource;
    private ArrayList<?> objects;
    private RecyclerCallback recyclerCallback;

    public MyRecyclerAdapter(int resource,
                             ArrayList<?> objects,
                             RecyclerCallback recyclerCallback) {
        this.resource = resource;
        this.objects = objects;
        this.recyclerCallback = recyclerCallback;

        Log.e("insideBaseAdapter",""+resource);
        Log.e("insideBaseAdapter",""+objects);
        Log.e("insideBaseAdapter",""+recyclerCallback);
    }

    @Override
    public int getItemCount() {
        if(objects != null){
            return objects.size();
        } else {
            return 0;
        }
    }

    @Override
    protected Object getObjForPosition(int position) {
        return objects.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return resource;
    }

    @Override
    protected RecyclerCallback getRecyclerCallback() {
        return recyclerCallback;
    }

}
