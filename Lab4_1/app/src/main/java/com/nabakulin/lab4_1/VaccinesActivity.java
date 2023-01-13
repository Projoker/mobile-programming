package com.nabakulin.lab4_1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VaccinesActivity extends AppCompatActivity {
    private VaccinesPresenter vaccinesPresenter;
    private VaccinesAdapter vaccinesAdapter;
    private ProgressDialog progressDialog;

    EditText editTextVaccineTitle;
    EditText editTextVaccineType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        editTextVaccineTitle = findViewById(R.id.add_vaccine_name);
        editTextVaccineType = findViewById(R.id.add_vaccine_type);

        findViewById(R.id.button_add_vaccine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaccinesPresenter.add();
            }
        });

        vaccinesAdapter = new VaccinesAdapter();
        RecyclerView vaccinesList = findViewById(R.id.vaccine_list);
        vaccinesList.setLayoutManager(layoutManager);
        vaccinesList.setAdapter(vaccinesAdapter);

        VaccineModel vaccineModel = new VaccineModel();
        vaccinesPresenter = new VaccinesPresenter(vaccineModel);
        vaccinesPresenter.attachView(this);
        vaccinesPresenter.viewIsReady();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vaccinesPresenter.detachView();
    }

    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", "Добавление вакцины...");
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showVaccines(List<Vaccine> vaccines) {
        vaccinesAdapter.setData(vaccines);
    }

    public Vaccine getVaccineData() {
        Vaccine vaccine = new Vaccine();
        vaccine.setType(editTextVaccineType.getText().toString());
        vaccine.setTitle(editTextVaccineTitle.getText().toString());
        return vaccine;
    }
}
