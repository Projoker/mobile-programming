package com.nabakulin.lab5_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFragmentAddVaccineDialog extends DialogFragment {

    EditText editTitle;
    EditText editType;
    EditText editId;

    APIInterface apiInterface;

    Context parentContext;

    public void setParentContext(Context context) {
        this.parentContext = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_vaccine_dialog_layout, null);
        builder.setView(view);

        editId = view.findViewById(R.id.fg_add_id);
        editType = view.findViewById(R.id.fg_add_type);
        editTitle = view.findViewById(R.id.fg_add_title);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        builder.setMessage("Добавить вакцину");
        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id = 0;
                try {
                    id = Integer.parseInt(editId.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(parentContext, "Введите целое число", Toast.LENGTH_SHORT).show();
                    return;
                }

                String type = editType.getText().toString();
                String title = editTitle.getText().toString();

                Call<Vaccine> call = apiInterface.addVaccine(new Vaccine(id, type, title));
                call.enqueue(new Callback<Vaccine>() {
                    @Override
                    public void onResponse(Call<Vaccine> call, Response<Vaccine> response) {
                        Toast.makeText(parentContext, "Вакцина добавлена", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Vaccine> call, Throwable t) {
                        Toast.makeText(getContext(), "Не удалось добавить вакцину", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println("Отмена");
            }
        });

        return builder.create();
    }
}
