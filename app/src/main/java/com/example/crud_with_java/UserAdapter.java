package com.example.crud_with_java;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.USerViewHolder> {
    private List<Note> userList;
    private DbHelper d;

    public UserAdapter(List<Note> noteList, Context context){
        this.userList=userList;
        this.d=new DbHelper(context);
    }

    public class USerViewHolder extends RecyclerView.ViewHolder{
        TextView name,email,mob;
        ImageView e,d;
        public USerViewHolder(View itemview){
            super(itemview);
            name=itemview.findViewById(R.id.name);
            email=itemview.findViewById(R.id.email);
            mob=itemview.findViewById(R.id.mob);
            e=itemview.findViewById(R.id.u1);
            d=itemview.findViewById(R.id.d1);
        }
    }

    @NonNull
    @Override
    public UserAdapter.USerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list,parent,false);
        return new USerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.USerViewHolder holder, int position) {
        Note note=userList.get(position);
        holder.name.setText(note.name);
        holder.email.setText(note.email);
        holder.mob.setText(note.mobile);
        holder.e.setOnClickListener(v->{
            Intent i=new Intent(holder.itemView.getContext(),UpdateActivity.class);
            i.putExtra("noteId",note.id);
            i.putExtra("name",note.name);
            i.putExtra("email",note.email);
            i.putExtra("mob",note.mobile);
            holder.itemView.getContext().startActivity(i);
        });
        holder.d.setOnClickListener(v -> {
            AlertDialog.Builder builder=new AlertDialog.Builder(holder.itemView.getContext());
            builder.setTitle("Delete?");
            builder.setMessage("Are You Sure YouWant To Delete This User?");
            builder.setPositiveButton("Yes",((dialog, which) -> {
                DbHelper d=new DbHelper(holder.itemView.getContext());
                d.deletenote(note.id);
                userList=d.getAllUsers();
                notifyDataSetChanged();
                Toast.makeText(holder.itemView.getContext(),"User Deleted Successfully",Toast.LENGTH_LONG).show();
            }));
            builder.setNegativeButton("Cancel",((dialog, which) -> {
                dialog.dismiss();
            }));
            AlertDialog dialog=builder.create();
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public void refreshdata(List<Note> newUserList){
        this.userList=newUserList;
        notifyDataSetChanged();
    }
}
