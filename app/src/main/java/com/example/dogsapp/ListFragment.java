package com.example.dogsapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.AbsListViewBindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.loader.content.AsyncTaskLoader;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AndroidException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dogsapp.databinding.FragmentListBinding;
import com.example.dogsapp.databinding.ItemDogBinding;
import com.example.dogsapp.model.DogBreed;
import com.example.dogsapp.view.MyAdapter;
import com.example.dogsapp.viewmodel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ListFragment extends Fragment {

//    public View view;

    private DogsApiService apiService;

    private RecyclerView rvDogBreeds;

    private MyAdapter myAdapter;

    private ArrayList<DogBreed> mdogBreeds;

    private FragmentListBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_list);

        mdogBreeds = new ArrayList<DogBreed>();

        apiService = new DogsApiService();
        apiService.getAllDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
                        Log.d("DEBUG","DDDDDDDDDDDDDD");

//                        rvDogBreeds = rootView.findViewById(R.id.rv_dogs);
                        binding.rvDogs.setLayoutManager(new GridLayoutManager(getContext(),2));
                        myAdapter = new MyAdapter(mdogBreeds, getContext());

                        binding.rvDogs.setAdapter(myAdapter);

                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                for (DogBreed dogBreed:
                                        dogBreeds) {
                                    mdogBreeds.add(dogBreed);

                                }

                                myAdapter.notifyDataSetChanged();
                            }
                        });


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG","CAN'T MATCH DATA");
                    }
                });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        button = view.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavDirections action = ListFragmentDirections.actionListToDetails();
//                Navigation.findNavController(view).navigate(action);
//            }
//        });
//        Navigation.setViewNavController(view, myAdapter);

    }
}