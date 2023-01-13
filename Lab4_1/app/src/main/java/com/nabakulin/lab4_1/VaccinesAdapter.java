package com.nabakulin.lab4_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VaccinesAdapter extends RecyclerView.Adapter<VaccinesAdapter.VaccineHolder> {
    List<Vaccine> data = new ArrayList();

    @NonNull
    @Override
    public VaccineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccine_item_view, parent, false);
        return new VaccineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Vaccine> cats) {
        data.clear();
        data.addAll(cats);
        notifyDataSetChanged();
    }

    static class VaccineHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView title;

        public VaccineHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.vaccine_item_view_type);
            title = itemView.findViewById(R.id.vaccine_item_view_title);
        }

        void bind(Vaccine vaccine) {
            type.setText(vaccine.getType());
            title.setText(vaccine.getTitle());
        }
    }
}
