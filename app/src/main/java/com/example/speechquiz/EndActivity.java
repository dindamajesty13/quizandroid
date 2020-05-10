package com.example.speechquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    private TextView txtSkor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        String skor = getIntent().getStringExtra("SKORE");

        txtSkor = findViewById(R.id.total_skor);
        txtSkor.setText(skor);
    }
}
