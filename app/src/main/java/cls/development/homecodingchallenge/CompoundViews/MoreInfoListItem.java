package cls.development.homecodingchallenge.CompoundViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import cls.development.homecodingchallenge.R;

public class MoreInfoListItem extends LinearLayout {
    private String text;
    public MoreInfoListItem(Context context, String s) {
        super(context);
        this.text = s;
        init();
    }

    public MoreInfoListItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MoreInfoListItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }


    void init(){
        inflate(getContext(), R.layout.more_info_item,this);
        TextView textViewInfo = findViewById(R.id.text_more_info_item);
        textViewInfo.setText(text);
        
    }


}
