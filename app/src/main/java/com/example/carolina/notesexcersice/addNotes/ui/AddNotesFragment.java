package com.example.carolina.notesexcersice.addNotes.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.carolina.notesexcersice.R;
import com.example.carolina.notesexcersice.addNotes.AddNotesPresenter;
import com.example.carolina.notesexcersice.addNotes.AddNotesPresenterImpl;
import com.example.carolina.notesexcersice.addNotes.events.AddNotesEvent;
import com.example.carolina.notesexcersice.notes.entities.Notes;

import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by carolina on 26/04/17.
 */

public class AddNotesFragment extends DialogFragment implements AddNotesView, DialogInterface.OnShowListener {
    @Bind(R.id.editTitle)
    EditText editTitle;
    @Bind(R.id.editContent)
    EditText editContent;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    private AddNotesPresenter addNotesPresenter;

    public AddNotesFragment() {
        addNotesPresenter = new AddNotesPresenterImpl(this);
    }

    public AddNotesFragment(Notes note) {
        addNotesPresenter = new AddNotesPresenterImpl(this);
        Bundle arguments = new Bundle();
        arguments.putString("title", note.getTitle());
        arguments.putString("content", note.getContent());

        this.setArguments(arguments);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

           AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addnotes_message_title)
                .setPositiveButton(R.string.addnotes_message_add, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .setNegativeButton(R.string.addnotes_message_cancel, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        LayoutInflater i = getActivity().getLayoutInflater();
        View view = i.inflate(R.layout.fragment_add_notes, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "THIS IS FRAGMENT2 BEING CLICKED!!! PRESS BACK TO GO TO FRAGMENT1", Toast.LENGTH_SHORT).show();
            }
        });
        ButterKnife.bind(this, view);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);

        Bundle args = getArguments();
        if (args != null && args.getString("title") != null && args.getString("content") != null){
            dialog.setTitle(R.string.editnotes_message_title);
            editTitle.setText(args.getString("title"));
            editContent.setText(args.getString("content"));
        }

        return dialog;

    }



    @Override
    public void onShow(DialogInterface dialogInterface) {

        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null){
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    addNotesPresenter.addNotes(editTitle.getText().toString(),
                            editContent.getText().toString());
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
        addNotesPresenter.onShow();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        addNotesPresenter.onDestroy();
    }

    @Override
    public void showInput() {
        editTitle.setVisibility(View.VISIBLE);
        editContent.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideInput() {
        editTitle.setVisibility(View.GONE);
        editContent.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Subscribe
    @Override
    public void noteAdded(AddNotesEvent event) {
        Toast.makeText(getActivity(), R.string.addnotes_message_contactadded, Toast.LENGTH_SHORT);
        dismiss();

    }

    @Override
    public void noteNotAdded() {
        editTitle.setText("");
        editTitle.setError(getString(R.string.addnotes_error_message));
        editContent.setText("");
        editContent.setError(getString(R.string.addnotes_error_message));
    }


}
