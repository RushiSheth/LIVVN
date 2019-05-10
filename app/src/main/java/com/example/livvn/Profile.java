package com.example.livvn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends Fragment {
    CardView c1;
    View v;
    Navigation nav;
    String uid;
    TextView t1;
    FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstancestate) {
        super.onViewCreated(view, savedInstancestate);
        this.v = view;
        init();
    }

    public void init() {
        c1 = getView().findViewById(R.id.logout);
        t1 = getView().findViewById(R.id.profile_id);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getEmail();
        t1.setText(uid);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainActivity.class));

            }
        });

    }
}
