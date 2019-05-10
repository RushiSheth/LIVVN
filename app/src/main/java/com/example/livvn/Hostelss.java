package com.example.livvn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Hostelss extends Fragment {

    RecyclerView rv;
    View v;
    DatabaseReference db;
    FirebaseRecyclerOptions<Hos> opt;
    FirebaseRecyclerAdapter<Hos, HViewHolder> adapt;
    EditText hosname;
    ArrayList<Hos> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_hostelss, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstancestate) {
        super.onViewCreated(view, savedInstancestate);
        this.v = view;
        init();
    }

    public void init() {
        rv = getView().findViewById(R.id.reli);
        rv.setHasFixedSize(true);
        arrayList = new ArrayList<>();
        hosname = getView().findViewById(R.id.search_hostel);
        hosname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    search(s.toString());
                } else {
                    search("");
                }
            }
        });
        db = FirebaseDatabase.getInstance().getReference().child("Hostels");
        opt = new FirebaseRecyclerOptions.Builder<Hos>()
                .setQuery(db, Hos.class).build();

        adapt = new FirebaseRecyclerAdapter<Hos, HViewHolder>(opt) {
            @Override
            protected void onBindViewHolder(@NonNull HViewHolder holder, final int position, @NonNull Hos model) {
                Picasso.get().load(model.getImage()).into(holder.i1, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
                holder.t1.setText(model.getName());
                holder.t2.setText(model.getInfo());
                holder.t3.setText(model.getRating());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), CardViewHostel.class);
                        // Log.v("hello","entered");
                        Hos hos = getItem(position);
                        intent.putExtra("name_text", hos.name.toUpperCase());
                        intent.putExtra("image_url", hos.image);
                        intent.putExtra("info_text", hos.info);
                        intent.putExtra("rating_text", hos.rating);
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public HViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hostel_layout, parent, false);
                return new HViewHolder(view);
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        adapt.startListening();
        rv.setAdapter(adapt);

    }

    private void search(String s) {
        s.toLowerCase();

        Query query = db.orderByChild("name")
                .startAt(s)
                .endAt(s + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    arrayList.clear();
                    for (DataSnapshot des : dataSnapshot.getChildren()) {
                        final Hos hes = des.getValue(Hos.class);
                        arrayList.add(hes);
                    }
                    Adapter2 adapter2 = new Adapter2(getContext(), arrayList);
                    rv.setAdapter(adapter2);
                    adapter2.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapt != null)
            adapt.startListening();
    }

    @Override
    public void onStop() {
        if (adapt != null)
            adapt.stopListening();
        super.onStop();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapt != null)
            adapt.startListening();
    }


}



