package com.rahul.rahul_demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahul.rahul_demo.pojo.Subject;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    List<Subject> subjectList;
    Context context;

    public SubjectAdapter(Context context, List<Subject> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_subject_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.subjectId.setText(subjectList.get(position).getSubjectId());
        holder.subjectName.setText(subjectList.get(position).getSubjectName());

        // Set click listener for the subject item
        final int itemPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjectName = subjectList.get(itemPosition).getSubjectName();
                openFaceRecognition(subjectName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subjectId,subjectName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectId = itemView.findViewById(R.id.subjectId);
            subjectName = itemView.findViewById(R.id.etsubjectName);
        }
    }

    private void openFaceRecognition(String subjectName) {
        // Start the faceRecognition activity and pass the subject name
        Intent intent = new Intent(context, faceRecognition.class);
        intent.putExtra("subjectName", subjectName);
        context.startActivity(intent);
    }
}
