package com.example.amacle.ui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.amacle.R;

public class Popup extends AppCompatDialogFragment {
    private EditText editTexttopic;
    private EditText editTextdescription;
    private PopupListner listner;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view)
                .setTitle("DailyTask")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {


                    }
                })
                .setPositiveButton(
                        "ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                String topic= editTexttopic.getText()  .toString();
                                String description= editTextdescription.getText().toString();
                                listner.applyTexts(topic,description);

                            }
                        }
                );
        editTexttopic=view.findViewById(R.id.editTexttopic);
        editTextdescription=view.findViewById(R.id.Edittextdescription);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listner=(PopupListner) context;
        } catch (ClassCastException e) {
        }
    }

    public interface PopupListner{
        void applyTexts(String topic, String description);
    }
}
