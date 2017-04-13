package io.github.yusukeiwaki.navdrawerplayground;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class NavLayout extends ScrimInsetsFrameLayout {

    private NestedScrollView container;

    public NavLayout(Context context) {
        super(context);
        initialize(context, null);
    }

    public NavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public NavLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        setBackgroundColor(Color.WHITE);

        int maxWidth = context.getResources().getDimensionPixelSize(R.dimen.def_navLayout_maxWidth);
        int contentLayout = 0;
        if (attrs != null) {
            TypedArray array =
                    context.getTheme().obtainStyledAttributes(attrs, R.styleable.NavLayout, 0, 0);
            maxWidth = array.getDimensionPixelSize(R.styleable.NavLayout_maxWidth, maxWidth);
            contentLayout = array.getResourceId(R.styleable.NavLayout_contentLayout, 0);
            array.recycle();
        }

        container = new NestedScrollView(context);
        addView(container, maxWidth, LayoutParams.MATCH_PARENT);

        container.setPadding(0, getStatusBarHeight(), 0, 0);
        View child = LayoutInflater.from(context).inflate(contentLayout, container, false);
        container.addView(child, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    private int getStatusBarHeight() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return 0;
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
