package com.example.livvn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CardViewRestaurant extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_restaurant);
        findViewById(R.id.map).setOnClickListener(this);
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("name_text") && getIntent().hasExtra("info_text") && getIntent().hasExtra("rating_text")) {
            String imageUrl = getIntent().getStringExtra("image_url");
            String namer = getIntent().getStringExtra("name_text");
            String infor = getIntent().getStringExtra("info_text");
            String rating = getIntent().getStringExtra("rating_text");

            setImage(imageUrl, namer, infor, rating);
        }
    }

    private void setImage(String imageUrl, String name_text, String infor, String rating) {

        TextView name = findViewById(R.id.card_resname);
        name.setText(name_text);
        TextView info = findViewById(R.id.card_resinfo);
        info.setText(infor);
        TextView rate = findViewById(R.id.card_resrating);
        rate.setText(rating);
        ImageView image = findViewById(R.id.card_resimage);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

    }

    public void passmap() {
        Intent intent = new Intent(CardViewRestaurant.this, Map.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map:
                passmap();
                break;
        }
    }
}
