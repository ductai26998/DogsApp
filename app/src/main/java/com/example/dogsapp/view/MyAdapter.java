package com.example.dogsapp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogsapp.DetailsFragment;
import com.example.dogsapp.ListFragmentDirections;
import com.example.dogsapp.R;
import com.example.dogsapp.databinding.ItemDogBinding;
import com.example.dogsapp.model.DogBreed;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<DogBreed> dogBreeds;
    private Context mContext;

    ItemDogBinding binding;

    public MyAdapter(List<DogBreed> dogBreeds, Context mContext) {
        this.dogBreeds = dogBreeds;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = (View) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_dog, parent, false);

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_dog, parent, false);

        MyViewHolder vh = new MyViewHolder(binding);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        binding.setDogBreed(dogBreeds.get(position));
        binding.setImageUrl(dogBreeds.get(position).getAvatar());


    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ItemDogBinding binding;

        public MyViewHolder(@NonNull ItemDogBinding binding) {
            super(binding.getRoot());
            binding.layoutDog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    Intent intent = new Intent(mContext, DetailsFragment.class);
                    DogBreed dogBreed = dogBreeds.get(position);
                    NavDirections action = ListFragmentDirections.actionListToDetails();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("dogBreed", dogBreed);
                    intent.putExtras(bundle);

                    mContext.startService(intent);
                    Navigation.findNavController(v).navigate(action);

                }
            });

        }
    }
}
