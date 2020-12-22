package cls.development.homecodingchallenge;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MoreInfoListItem extends LinearLayout {
    private TextView textView;
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

    public MoreInfoListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }
    void init(){
        inflate(getContext(),R.layout.more_info_item,this);
        textView = findViewById(R.id.text_more_info_item);
        textView.setText(text);
        
    }


}
