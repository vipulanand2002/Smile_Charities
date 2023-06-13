package com.example.smilecharities.ui.predictions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.smilecharities.MainActivity;
import com.example.smilecharities.R;
import com.example.smilecharities.activities.HomeActivity;
import com.example.smilecharities.activities.LoginActivity;
import com.example.smilecharities.activities.Mypredictionsapp;

public class MyPredictionsFragment extends Fragment {

    Activity context;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            context = getActivity();

            // Inflate the layout for this fragment
            View root = inflater.inflate(R.layout.fragment_my_predictions, container, false);
            return root;
        }

    @Override
    public void onStart() {
        super.onStart();
        Button btn = (Button) context.findViewById(R.id.nextactivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Mypredictionsapp.class);
                startActivity(intent);
            }
        });
    }
}
