package com.nabakulin.lab5_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFragment extends Fragment implements MyFragmentAdapter.ItemClickListener {
    MyFragmentAdapter adapter;
    List<Vaccine> items = new ArrayList<>();

    Button addNewVaccine;
    Button refreshVaccineList;

    APIInterface apiInterface;

    MyFragment() {
        super(R.layout.my_fragment_layout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_fragment_layout, container, false);

        addNewVaccine = rootView.findViewById(R.id.f2_bac);
        addNewVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyFragmentAddVaccineDialog addVaccineMyFragment = new MyFragmentAddVaccineDialog();
                addVaccineMyFragment.setParentContext(getContext());
                addVaccineMyFragment.show(getActivity().getSupportFragmentManager(), "ADD_VACCINE");
            }
        });

        refreshVaccineList = rootView.findViewById(R.id.f2_brc);
        refreshVaccineList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new MyFragmentAdapter(this.getContext(), items);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Vaccine vaccine = items.get(position);
        Toast.makeText(getActivity(), "ID: " + vaccine.id + " Название: " + vaccine.title + " Тип: " + vaccine.type, Toast.LENGTH_SHORT).show();
    }

    public void getData() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<Vaccine>> call = apiInterface.getVaccines();

        call.enqueue(new Callback<List<Vaccine>>() {
            @Override
            public void onResponse(Call<List<Vaccine>> call, Response<List<Vaccine>> response) {
                List<Vaccine> vaccines = response.body();
                items.clear();
                items.addAll(vaccines);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Vaccine>> call, Throwable t) {
                Toast.makeText(getActivity(), "Невозможность соединиться с сервеоом", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
