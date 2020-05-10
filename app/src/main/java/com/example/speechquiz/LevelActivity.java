package com.example.speechquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LevelActivity extends AppCompatActivity {
    public String NYAWA = "1";
    private TextView txtNilai, txtQuiz, txtLevel, txtNyawa;
    private ImageView btnVoice, imgQuiz, imgNyawa;
    private String levelQuiz, mCurrentScore, mCurrentSoal, mcurrentLife;
    int kesempatan = 3;
    int skor = 0;
    int indeks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        txtNyawa = findViewById(R.id.kesempatan);
        txtNyawa.setText(String.format("X %s", Integer.toString(kesempatan)));

        imgNyawa = findViewById(R.id.nyawa);

        imgQuiz = findViewById(R.id.img_quiz);
        imgQuiz.setImageResource(R.drawable.soal1);

        txtNilai = findViewById(R.id.skor);
        txtNilai.setText(Integer.toString(skor));

        txtQuiz = findViewById(R.id.soal_quiz);
        txtQuiz.setText(R.string.soal1);


        btnVoice = findViewById(R.id.voice);
        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeechInput();
            }
        });
    }

    public void getSpeechInput() {
        String ID_ModelBahasaIndonesia = "id";
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, ID_ModelBahasaIndonesia);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, ID_ModelBahasaIndonesia);
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, ID_ModelBahasaIndonesia);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, ID_ModelBahasaIndonesia);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi, Katakan Sesuatu");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        } else {
            Toast.makeText(this, "Perangkatnya Gak Support Voice Nih", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String[] soal = getResources().getStringArray(R.array.array_soal);
        int[] myImageList = new int[]{R.drawable.soal1, R.drawable.soal2, R.drawable.soal3, R.drawable.soal4, R.drawable.soal5,
                R.drawable.soal6, R.drawable.soal7, R.drawable.soal8, R.drawable.soal9, R.drawable.soal10,
                R.drawable.soal11, R.drawable.soal12, R.drawable.soal13, R.drawable.soal14, R.drawable.soal15,
                R.drawable.soal16, R.drawable.soal17, R.drawable.soal18, R.drawable.soal19, R.drawable.soal20,
                R.drawable.soal21, R.drawable.soal22, R.drawable.soal23, R.drawable.soal24, R.drawable.soal25,
                R.drawable.soal26, R.drawable.soal27, R.drawable.soal28, R.drawable.soal29, R.drawable.soal30
        };

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.get(0).equals(txtQuiz.getText().toString().toLowerCase())) {
                        skor += 100;

                        txtNilai.setText(Integer.toString(skor));
                        indeks += 1;

                        String output = soal[indeks];
                        txtQuiz.setText(output);

                        imgQuiz.setImageResource(myImageList[indeks]);
                    }
                    else {
                        int nyawa = kesempatan -1;
                        Toast.makeText(this, "Jawaban Kamu Salah, Kesempatan kamu sisa: " + nyawa, Toast.LENGTH_SHORT).show();
                        txtNyawa.setText(String.format("X %s", Integer.toString(nyawa)));
                        kesempatan = nyawa;
                        if (nyawa == 0){
                            String totalSkor = (String) txtNilai.getText().toString();
                            Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                            intent.putExtra("SKORE", totalSkor);
                            startActivity(intent);
                            super.onDestroy();
                        }
                }
                break;
                }
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);  // "Hide" your current Activity
    }

}
