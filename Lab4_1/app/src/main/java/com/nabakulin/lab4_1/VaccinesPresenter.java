package com.nabakulin.lab4_1;

import android.content.ContentValues;

import java.util.List;

public class VaccinesPresenter {
    private VaccinesActivity vaccinesActivity;
    private final VaccineModel vaccineModel;

    public VaccinesPresenter(VaccineModel vaccineModel) {
        this.vaccineModel = vaccineModel;
    }

    public void attachView(VaccinesActivity activity) {
        this.vaccinesActivity = activity;
    }

    public void detachView() {
        vaccinesActivity = null;
    }

    public void loadVaccines() {
        vaccineModel.load(new VaccineModel.LoadCallback() {
            @Override
            public void onLoad(List<Vaccine> vaccines) {
                vaccinesActivity.showVaccines(vaccines);
            }
        });
    }

    public void viewIsReady() {
        loadVaccines();
    }

    public void add() {
        Vaccine vaccine = vaccinesActivity.getVaccineData();
        if (vaccine.getTitle().isEmpty() || vaccine.getType().isEmpty()) {
            vaccinesActivity.showToast("Не заполнены поля");
            return;
        }

        ContentValues contentValues = new ContentValues(1);
        contentValues.put("TYPE", vaccine.getType());
        contentValues.put("TITLE", vaccine.getTitle());
        vaccinesActivity.showProgress();

        vaccineModel.add(contentValues, new VaccineModel.CompleteCallback() {
            @Override
            public void onComplete() {
                vaccinesActivity.hideProgress();
                loadVaccines();
            }
        });
    }
}
