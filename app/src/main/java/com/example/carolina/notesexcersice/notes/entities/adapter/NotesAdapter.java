package com.example.carolina.notesexcersice.notes.entities.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carolina.notesexcersice.R;
import com.example.carolina.notesexcersice.notes.entities.Notes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by carolina on 29/04/17.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{
    private Context context;
    private List<Notes> data;
    private OnItemClickListener onItemClickListener;


    public NotesAdapter(Context context, DatabaseReference databaseReference) {
        this.context = context;

        final NotesAdapter adapter = this;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.data.clear();
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Notes note = data.getValue(Notes.class);
                    adapter.data.add(note);
                }

                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public NotesAdapter() {
        this.data = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Notes notes = data.get(position);
        String note = notes.getTitle();
        holder.textView.setText(note);
        String note2 = notes.getContent();
        holder.textView2.setText(note2);

        if (this.onItemClickListener != null){
            holder.setOnItemClickListener(notes,this.onItemClickListener);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addElement (Notes element){
        data.add(0, element);
        notifyDataSetChanged();
    }

    public void clear (){
        data.clear();
        notifyDataSetChanged();
    }


    public void setData(List<Notes> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        @Bind(R.id.textViewTitle)
        TextView textView;
        @Bind(R.id.textViewContent)
        TextView textView2;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        /*public void setTitle(String title){
            textView.setText(title);
        }

        public void setContent(String content){
            textView2.setText(content);
        }*/


        public void setOnItemClickListener(final Notes notes, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    listener.onItemClick(notes);

                }
            });

        }


    }



}
