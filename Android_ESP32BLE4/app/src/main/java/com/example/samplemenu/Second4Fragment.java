package com.example.samplemenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

//sean import com.example.samplemenu.databinding.FragmentSecond4Binding;
import com.hishri.fnarduino.R;
import com.hishri.fnarduino.databinding.FragmentSecond4Binding;


public class Second4Fragment extends Fragment {

   // public class Second4Fragment extends Fragment {

        private FragmentSecond4Binding binding;

        @Override
        public View onCreateView(
                @NonNull LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState
        ) {

            binding = FragmentSecond4Binding.inflate(inflater, container, false);
            return binding.getRoot();

        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            binding.buttonSecond.setOnClickListener(v ->
                    NavHostFragment.findNavController(Second4Fragment.this)
                            .navigate(R
                                    .id.action_Second4Fragment_to_First4Fragment)
            );
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
 //   }
}