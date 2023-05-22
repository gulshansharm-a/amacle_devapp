package com.example.amacle.ui;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.amacle.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Popup extends AppCompatDialogFragment {
    private EditText editTexttopic;
    private EditText editTextdescription;
    private PopupListner listner;
    @SuppressLint("MissingInflatedId")
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view)
                .setTitle("DailyTask")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {}})
                .setPositiveButton(
                        "ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                String topic= editTexttopic.getText()  .toString();
                                String description= editTextdescription.getText().toString();

                                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                DatabaseReference todayTaskDBref = FirebaseDatabase.getInstance().getReference().child("users").child(uid)
                                        .child("todayTasks").push();
                                TextView topic_text_view = view.findViewById(R.id.editexttp);
                                TextView gitLinkText = view.findViewById(R.id.gitlink);
                                TextView des = view.findViewById(R.id.Edittextds);
                                todayTaskDBref.child("topic").setValue(topic_text_view.getText().toString());
                                todayTaskDBref.child("des").setValue(des.getText().toString());}});
        editTexttopic=view.findViewById(R.id.editexttp);
        editTextdescription=view.findViewById(R.id.Edittextds);
        return builder.create();}
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listner=(PopupListner) context;
        } catch (ClassCastException e) {}}
    public interface PopupListner{ void applyTexts(String topic, String description);}
}

