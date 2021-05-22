package com.gw.doit.adapters;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gw.doit.R;
import com.gw.doit.model.SingleTime;

import java.util.List;

public class SingleTimesAdapter extends RecyclerView.Adapter<SingleTimesAdapter.ViewHolder> {

    List<SingleTime> times;

   public SingleTimesAdapter(List<SingleTime> times){
       this.times = times;
   }

    @NonNull
    @Override
    public SingleTimesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.times_recycle_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SingleTimesAdapter.ViewHolder holder, int position) {
       holder.tvTime.setText(times.get(position).toString());
       holder.ibDeleteTime.setOnClickListener( v -> {
           Snackbar.make(v,"Apagando o tempo: "+times.get(position).toString(),Snackbar.LENGTH_SHORT).show();
       });
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView tvTime;
        public ImageButton ibDeleteTime;

        public ViewHolder(View v) {
            super(v);
            tvTime = (TextView) v.findViewById(R.id.tvTime);
            ibDeleteTime = (ImageButton) v.findViewById(R.id.ibDeleteTime);
        }
    }
}
