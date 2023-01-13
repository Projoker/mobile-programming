package com.nabakulin.lab4_1;

import android.content.ContentValues;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VaccineModel {
    private List<Vaccine> vaccines = new ArrayList<>();

    public VaccineModel() {
        init();
    }

    private void init() {
        this.vaccines.add(new Vaccine("Грипп", "АнтиГрипп"));
        this.vaccines.add(new Vaccine("Коровавирус", "Спутник"));
    }

    public void load(LoadCallback callback) {
        LoadTask loadTask = new LoadTask(callback);
        loadTask.execute();
    }

    public void add(ContentValues contentValues, CompleteCallback callback) {
        AddTask addCatTask = new AddTask(callback);
        addCatTask.execute(contentValues);
    }

    class LoadTask extends AsyncTask<Void, Void, List<Vaccine>> {
        private final LoadCallback callback;

        public LoadTask(LoadCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<Vaccine> doInBackground(Void... voids) {
            return vaccines;
        }

        @Override
        protected void onPostExecute(List<Vaccine> cats) {
            callback.onLoad(cats);
        }
    }

    interface LoadCallback {
        void onLoad(List<Vaccine> cats);
    }

    class AddTask extends AsyncTask<ContentValues, Void, Void> {
        private final CompleteCallback callback;

        public AddTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(ContentValues... contentValues) {
            Vaccine vaccine = new Vaccine();
            vaccine.setTitle(contentValues[0].get("TITLE").toString());
            vaccine.setType(contentValues[0].get("TYPE").toString());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            vaccines.add((Vaccine) vaccine);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            callback.onComplete();
        }
    }

    interface CompleteCallback {
        void onComplete();
    }
}
