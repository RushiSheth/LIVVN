package com.example.livvn;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HViewHolder extends RecyclerView.ViewHolder {
    TextView t1;
    TextView t2;
    ImageView i1;
    TextView t3;
    TextView t4;

    public HViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = itemView.findViewById(R.id.hostel_name);
        t2 = itemView.findViewById(R.id.hostel_info);
        t3 = itemView.findViewById(R.id.hostel_rating);
        i1 = itemView.findViewById(R.id.hostel_image);
    }
}