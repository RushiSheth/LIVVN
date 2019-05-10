package com.example.livvn;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView t1;
    TextView t3;
    TextView t2;
    ImageView i1;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = itemView.findViewById(R.id.name_text);
        t2 = itemView.findViewById(R.id.info_text);
        i1 = itemView.findViewById(R.id.eatery_image);
        t3 = itemView.findViewById(R.id.rating_text);
    }
}
