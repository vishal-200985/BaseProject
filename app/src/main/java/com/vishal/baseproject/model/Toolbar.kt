package com.vishal.baseproject.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.vishal.baseproject.BR

class Toolbar(var _title: String, var _isBack: Boolean, var _isVisible: Boolean) : BaseObservable() {

    var title: String
        @Bindable get() = _title
        set(value) {
            _title = value
            notifyPropertyChanged(BR.title)
        }

    var isBack: Boolean
        @Bindable get() = _isBack
        set(value) {
            _isBack = value
            notifyPropertyChanged(BR.back)
        }

    var isVisible: Boolean
        @Bindable get() = _isVisible
        set(value) {
            _isVisible = value
            notifyPropertyChanged(BR.visible)
        }
}