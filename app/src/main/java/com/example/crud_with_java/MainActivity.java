package com.example.crud_with_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.crud_with_java.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DbHelper d;
    private UserAdapter u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,adduseractivity.class);
                startActivity(intent);
            }
        });
        d=new DbHelper(MainActivity.this);
        u=new UserAdapter(d.getAllUsers(),this);
        binding.l1.setLayoutManager(new LinearLayoutManager(this));
        binding.l1.setAdapter(u);
    }
    protected void onResume(){
        super.onResume();
        u.refreshdata(d.getAllUsers());
    }
}

