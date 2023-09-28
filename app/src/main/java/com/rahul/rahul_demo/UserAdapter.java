package com.rahul.rahul_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahul.rahul_demo.pojo.Student;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<Student> userList;

    Context context;

    public UserAdapter(Context context, List<Student> userList) {
        this.context = context;
        this.userList=userList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student currentItem = userList.get(position);

        holder.studentID.setText(userList.get(position).getUsersId());
        holder.studentName.setText(userList.get(position).getFullname());
        holder.studentEmail.setText(userList.get(position).getEmailId());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(currentItem.isSelected());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentItem.setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentName,studentEmail,studentID;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            studentID = itemView.findViewById(R.id.studentID);
            studentName = itemView.findViewById(R.id.etStudentName);
            studentEmail = itemView.findViewById(R.id.etEmail);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
