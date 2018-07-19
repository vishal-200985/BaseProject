package com.vishal.baseproject.view.baseClasses.recyclerViewBase;

public interface RecyclerCallback {

    void onItemClick(int position);

    void onChildItemClick(int parentIndex, int childIndex);

    void itemAction(String type, int position);

}
