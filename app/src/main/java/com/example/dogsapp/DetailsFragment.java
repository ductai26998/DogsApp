package com.example.dogsapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogsapp.databinding.FragmentDetailsBinding;
import com.example.dogsapp.model.DogBreed;

import java.util.List;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    private List<DogBreed> dogBreeds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentDetailsBinding.inflate(getLayoutInflater());

        Intent intent = null;
        Bundle bundle = intent.getExtras();
        DogBreed dogBreed = (DogBreed) bundle.getSerializable("dogBreed");
        binding.setDogBreed(dogBreed);
        binding.setImageUrl(dogBreed.getAvatar());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }
}