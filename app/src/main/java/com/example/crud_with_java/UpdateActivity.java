package com.example.crud_with_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.crud_with_java.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {
    private ActivityUpdateBinding binding;
    private DbHelper d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i=getIntent();
        int noteid=i.getIntExtra("noteId",-1);
        String name=i.getStringExtra("name");
        String email=i.getStringExtra("email");
        String mob=i.getStringExtra("mob");
        binding.ed1.setText(name);
        binding.ed2.setText(email);
        binding.ed3.setText(mob);
        d=new DbHelper(UpdateActivity.this);
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newname=binding.ed1.getText().toString();
                String newemail=binding.ed2.getText().toString();
                String newmob=binding.ed3.getText().toString();
                if(!newname.isEmpty() && !newemail.isEmpty() && !newmob.isEmpty()){
                    Note note=new Note(noteid,newname,newemail,newmob);
                    d.updateuser(note);
                    Toast.makeText(UpdateActivity.this,"User Updated Successfully...!",Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(UpdateActivity.this,"Please Fill Data First...!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}