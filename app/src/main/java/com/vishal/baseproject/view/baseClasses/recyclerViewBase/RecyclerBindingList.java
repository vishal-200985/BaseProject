package com.vishal.baseproject.view.baseClasses.recyclerViewBase;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.vishal.baseproject.BR;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerBindingList<T> extends BaseObservable implements Serializable {

    @Bindable
    private MyRecyclerAdapter adapter;
    private ArrayList<T> itemsList;

    @Bindable
    public MyRecyclerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MyRecyclerAdapter adapter) {
        this.adapter = adapter;
       // notifyPropertyChanged(BR.adapter);
    }

    @Bindable
    public ArrayList<T> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<T> itemsList) {
        this.itemsList = itemsList;
        Log.e("RecyclerBindingList",""+itemsList.size());
       // notifyPropertyChanged(BR.itemsList);
    }

}
