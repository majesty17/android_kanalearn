package com.majesty.kana;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class JapanTextView extends TextView {
    public JapanTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public JapanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JapanTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode() && StudyActivity.tf!=null) {
            setTypeface(StudyActivity.tf);
        }
    }
}
