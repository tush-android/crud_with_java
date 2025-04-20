package com.example.crud_with_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.crud_with_java.databinding.ActivityAdduseractivityBinding;

public class adduseractivity extends AppCompatActivity {
    private ActivityAdduseractivityBinding binding;
    private DbHelper d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityAdduseractivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        d=new DbHelper(this);
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.e1.getText().toString();
                String email=binding.e2.getText().toString();
                String mobile=binding.e3.getText().toString();
                if(!name.isEmpty() && !email.isEmpty() && !mobile.isEmpty()){
                    Note note=new Note(0,name,email,mobile);
                    d.insert(note);
                    Toast.makeText(adduseractivity.this,"Data Inserted Successfully...!",Toast.LENGTH_LONG).show();
                    //binding.e1.setText("");
                    //binding.e2.setText("");
                    //binding.e3.setText("");
                    finish();
                }
                else {
                    Toast.makeText(adduseractivity.this,"Please Fill Data",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}