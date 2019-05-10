package com.example.livvn;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.Adapter2ViewHolder> {
    Context c;
    ArrayList<Hos> arrayList;

    public Adapter2(Context c, ArrayList<Hos> hes) {
        this.c = c;
        this.arrayList = hes;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Adapter2ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hostel_layout, viewGroup, false);
        return new Adapter2ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2ViewHolder adapter2ViewHolder, final int i) {
        Hos hes = arrayList.get(i);
        adapter2ViewHolder.t1.setText(hes.getName());
        adapter2ViewHolder.t2.setText(hes.getInfo());
        Picasso.get().load(hes.getImage()).into(adapter2ViewHolder.i1);
        adapter2ViewHolder.c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, CardViewHostel.class);
                // Log.v("hello","entered");
                intent.putExtra("name_text", arrayList.get(i).name);
                intent.putExtra("image_url", arrayList.get(i).image);
                intent.putExtra("info_text", arrayList.get(i).info);
                intent.putExtra("rating_text", arrayList.get(i).rating);
                c.startActivity(intent);

            }
        });

    }

    public class Adapter2ViewHolder extends RecyclerView.ViewHolder {

        public TextView t1;
        public TextView t2;
        public TextView t3;
        public ImageView i1;
        public CardView c2;

        public Adapter2ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.hostel_name);
            t2 = itemView.findViewById(R.id.hostel_info);
            i1 = itemView.findViewById(R.id.hostel_image);
            t3 = itemView.findViewById(R.id.hostel_rating);
            c2 = itemView.findViewById(R.id.hoscard);
        }
    }
}