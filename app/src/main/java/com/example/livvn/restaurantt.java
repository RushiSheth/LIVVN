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
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class restaurantt extends Fragment {

    RecyclerView rv;
    FloatingActionMenu fam;
    FloatingActionButton resfil;
    View v;
    DatabaseReference db;
    FirebaseRecyclerOptions<Res> opt;
    FirebaseRecyclerAdapter<Res, ViewHolder> adapt;
    EditText resname;
    ArrayList<Res> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_restaurantt, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstancestate) {
        super.onViewCreated(view, savedInstancestate);
        this.v = view;
        init();
    }

    public void init() {
        rv = getView().findViewById(R.id.rel);
        fam = getView().findViewById(R.id.se);
        resfil = getView().findViewById(R.id.filterrate);
        rv.setHasFixedSize(true);
        arrayList = new ArrayList<>();
        resname = getView().findViewById(R.id.searchr);
        resfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resfilter();
            }
        });
//        fam.setOnMenuButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                lp.addRule(RelativeLayout.BELOW,R.id.searchr);
//                if(fam.isOpened()) {
//                    lp.setMargins(0, 180, 0, 0);
//                }
//                else
//                    lp.setMargins(0, 40, 0, 0);
//                rv.setLayoutParams(lp);
//            }
//        });
       /*if(fam.isOpened()){
           rv.setVisibility(View.GONE);

        }
        else{
            rv.setVisibility(View.VISIBLE);

        }*/
        resname.addTextChangedListener(new TextWatcher() {
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
        db = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        opt = new FirebaseRecyclerOptions.Builder<Res>()
                .setQuery(db, Res.class).build();

        adapt = new FirebaseRecyclerAdapter<Res, ViewHolder>(opt) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, final int position, @NonNull Res model) {
                Picasso.get().load(model.getImage()).into(holder.i1, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
                holder.t1.setText(model.getName().toUpperCase());
                holder.t2.setText(model.getInfo());
                holder.t3.setText(model.getRating());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), CardViewRestaurant.class);
                        // Log.v("hello","entered");
                        Res res = getItem(position);
                        intent.putExtra("name_text", res.name.toUpperCase());
                        intent.putExtra("image_url", res.image);
                        intent.putExtra("info_text", res.info);
                        intent.putExtra("rating_text", res.rating);
                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list__layout, parent, false);
                return new ViewHolder(view);
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
                        final Res res = des.getValue(Res.class);
                        arrayList.add(res);
                    }
                    Adapter1 adapter1 = new Adapter1(getContext(), arrayList);
                    rv.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void resfilter() {


        Query query = db.orderByChild("info").equalTo("Gujarati");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    arrayList.clear();
                    for (DataSnapshot des : dataSnapshot.getChildren()) {
                        final Res res = des.getValue(Res.class);
                        arrayList.add(res);
                    }
                    Adapter1 adapter1 = new Adapter1(getContext(), arrayList);
                    rv.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
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

