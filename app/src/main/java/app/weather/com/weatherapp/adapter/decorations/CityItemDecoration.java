package app.weather.com.weatherapp.adapter.decorations;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.weather.com.weatherapp.App;
import app.weather.com.weatherapp.R;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class CityItemDecoration extends RecyclerView.ItemDecoration {

    private final int padding;
    private final int botPadding;

    public CityItemDecoration() {
        Resources resources = App.getContext().getResources();
        this.padding = resources.getDimensionPixelOffset(R.dimen.view_padding);
        this.botPadding = resources.getDimensionPixelOffset(R.dimen.small_view_padding);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = padding;
        }
        outRect.left = padding;
        outRect.right = padding;
        outRect.bottom = botPadding;
    }
}
