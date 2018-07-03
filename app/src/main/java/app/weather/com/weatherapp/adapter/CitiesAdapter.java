package app.weather.com.weatherapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.weather.com.weatherapp.R;
import app.weather.com.weatherapp.data.models.CityItem;
import app.weather.com.weatherapp.utils.StringUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

/**
 * Adapter used to display cities with current temperature in {@link app.weather.com.weatherapp.screens.MainActivity}
 */
public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private List<CityItem> items = new ArrayList<>();
    private final OnCityClickListener listener;

    public CitiesAdapter(OnCityClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        CityViewHolder holder = new CityViewHolder(view);
        attachItemClickListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<CityItem> newItems) {
        int fromPosition = items.size();
        items.addAll(newItems);
        if (fromPosition != 0) {
            notifyItemRangeInserted(fromPosition, newItems.size());
        } else {
            notifyDataSetChanged();
        }
    }

    private void attachItemClickListener(RecyclerView.ViewHolder holder) {
        holder.itemView.setOnClickListener(view -> {
            int selectedPosition = holder.getAdapterPosition();
            if (selectedPosition != RecyclerView.NO_POSITION && items != null) {
                listener.onCityClick(items.get(selectedPosition));
            }
        });
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_city_name)
        TextView tvCity;
        @BindView(R.id.tv_item_city_temperature)
        TextView tvTemp;

        public CityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CityItem item) {
            tvCity.setText(item.getCityName());
            tvTemp.setText(StringUtils.format(R.string.item_city_temperature, item.getTemp()));
        }
    }
}
