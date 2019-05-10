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

public class Adapter1 extends RecyclerView.Adapter<Adapter1.Adapter1ViewHolder> {
    Context c;
    ArrayList<Res> arrayList;

    public Adapter1(Context c, ArrayList<Res> res) {
        this.c = c;
        this.arrayList = res;
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
    public Adapter1ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list__layout, viewGroup, false);
        return new Adapter1ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter1ViewHolder adapter1ViewHolder, final int i) {
        final Res res = arrayList.get(i);
        adapter1ViewHolder.t1.setText(res.getName().toUpperCase());
        adapter1ViewHolder.t2.setText(res.getInfo());
        adapter1ViewHolder.t3.setText(res.getRating());
        Picasso.get().load(res.getImage()).into(adapter1ViewHolder.i1);
        adapter1ViewHolder.c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, CardViewRestaurant.class);
                // Log.v("hello","entered");
                intent.putExtra("name_text", arrayList.get(i).name);
                intent.putExtra("image_url", arrayList.get(i).image);
                intent.putExtra("info_text", arrayList.get(i).info);
                intent.putExtra("rating_text", arrayList.get(i).rating);
                c.startActivity(intent);

            }
        });

    }

    public class Adapter1ViewHolder extends RecyclerView.ViewHolder {

        public TextView t1;
        public TextView t2;
        public ImageView i1;
        public CardView c1;
        public TextView t3;

        public Adapter1ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.name_text);
            t2 = itemView.findViewById(R.id.info_text);
            t3 = itemView.findViewById(R.id.rating_text);
            i1 = itemView.findViewById(R.id.eatery_image);
            c1 = itemView.findViewById(R.id.rescard);
        }
    }
}