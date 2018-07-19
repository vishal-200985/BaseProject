package com.vishal.baseproject.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.vishal.baseproject.R


open class ButtonProgress(context: Context) : RelativeLayout(context) {

    private val UNDEFINED = -1

    private var btn_Parent: LinearLayout? = null
    private var textTV: Button? = null
    private var progressBar: ProgressBar? = null

    private var buttonText: String? = null
    private var textColor: Int = 0
    private var textSize: Int = 0
    private var progressColor: Int = 0
    private var progressWidth: Int = 0
    private var bgColor: Int = 0
    private var bgDrawable: Drawable? = null
    private var progressVisible: String? = "visible"


    private var inProgress: Boolean = false

    /*constructor (context: Context) : this(context) {
        initializeViews(context)
    }*/

    constructor(context: Context, attrs: AttributeSet) : this(context) {
        readAttrs(attrs)
        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : this(context, attrs) {
        readAttrs(attrs)
        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : this(context, attrs, defStyleAttr) {
        readAttrs(attrs)
        initializeViews(context)
    }

    private fun initializeViews(context: Context) {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_progress_button, this)
    }

    private fun readAttrs(attrs: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ProgressButtonComponent,
                0, 0)

        try {
            buttonText = a.getString(R.styleable.ProgressButtonComponent_buttonText)
            textColor = a.getColor(R.styleable.ProgressButtonComponent_buttonTextColor, Color.RED)
            textSize = a.getDimensionPixelSize(R.styleable.ProgressButtonComponent_buttonTextSize, UNDEFINED)
            progressColor = a.getColor(R.styleable.ProgressButtonComponent_progressColor, Color.WHITE)
            progressWidth = a.getDimensionPixelSize(R.styleable.ProgressButtonComponent_progressWidth, UNDEFINED)
            bgColor = a.getColor(R.styleable.ProgressButtonComponent_bgColor, Color.TRANSPARENT)
            bgDrawable = a.getDrawable(R.styleable.ProgressButtonComponent_bgDrawable)
            progressVisible = a.getString(R.styleable.ProgressButtonComponent_progressVisible)
        } finally {
            a.recycle()
        }
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        textTV = findViewById<Button>(R.id.button)
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        btn_Parent = findViewById<View>(R.id.btn_Parent) as LinearLayout

        textTV!!.text = buttonText

        textTV!!.setTextColor(textColor)

        if (bgColor != UNDEFINED)
            btn_Parent!!.setBackgroundColor(bgColor)

        if (textSize != UNDEFINED)
            textTV!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())


        if (bgDrawable != null)
            btn_Parent!!.setBackgroundDrawable(bgDrawable)

        if (progressVisible == "visible") {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.INVISIBLE
        }

//        if (progressWidth != UNDEFINED)
//            progressWheel!!.setBarWidth(progressWidth)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        //set progress wheel width and height based on parent height
        val size = h - paddingTop - paddingBottom
        val params = progressBar!!.getLayoutParams()
        params.width = size
        params.height = size
        progressBar!!.setLayoutParams(params)

    }

    fun setInProgress(inProgress: Boolean) {
        this.inProgress = inProgress
        setViewValues()
    }

    fun isInProgress(): Boolean {
        return inProgress
    }

    private fun setViewValues() {
        isEnabled = !inProgress
        progressBar!!.setVisibility(if (inProgress) View.VISIBLE else View.GONE)
    }

    private fun setText(text: String) {
        this.buttonText = text
        textTV!!.text = buttonText
    }

    private fun getText(): String? {
        return buttonText
    }

    fun getTextColor(): Int {
        return textColor
    }

    fun setTextColor(textColor: Int) {
        this.textColor = textColor
        textTV!!.setTextColor(textColor)
    }

    fun getTextSize(): Int {
        return textSize
    }

    fun setTextSize(pxTextSize: Int) {
        this.textSize = pxTextSize
        textTV!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    fun getProgressColor(): Int {
        return progressColor
    }

    fun setProgressColor(progressColor: Int) {
        this.progressColor = progressColor
        //progressBar!!.setBarColor(progressColor)
    }

    fun getProgressWidth(): Int {
        return progressWidth
    }

    fun setProgressWidth(progressWidth: Int) {
        this.progressWidth = progressWidth
        // progressBar!!.setBarWidth(progressWidth)
    }

    fun setBgColor(bgColor: Int) {
        this.bgColor = bgColor
    }

    fun setBgDrawable(bgDrawable: Drawable) {
        this.bgDrawable = bgDrawable
    }

    fun showProgress() {
        progressBar!!.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar!!.visibility = View.GONE
    }


    fun setProgressVisible(progressVisible: String) {
        this.progressVisible = progressVisible
    }
}