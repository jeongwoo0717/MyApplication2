package com.example.myapplication2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Intent2 extends AppCompatActivity {
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        // Retrofit 초기화
        Retrofit retrofit = RetrofitClient.getClient("http://183.99.154.178:9999/");
        apiService = retrofit.create(ApiService.class);

        // 버튼 클릭 이벤트 처리
        Button buttonYes = findViewById(R.id.button_yes);
        Button buttonNo = findViewById(R.id.button_no);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordMedicationTime(true);
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordMedicationTime(false);
            }
        });

        // 버튼 클릭 이벤트 조회
        getMedicationTimes();
    }

    private void recordMedicationTime(boolean isYes) {
        String timeValue = isYes ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()).format(new Date()) : "-";
        MedicationTimeCreate medicationTimeCreate = new MedicationTimeCreate(timeValue);

        apiService.createMedicationTime(medicationTimeCreate).enqueue(new Callback<MedicationTime>() {
            @Override
            public void onResponse(Call<MedicationTime> call, Response<MedicationTime> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "Medication time recorded successfully");
                    Toast.makeText(Intent2.this, "성공적으로 전송되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("MainActivity", "Failed to record medication time: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Intent2.this, "서버 전송에 실패했습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MedicationTime> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                Toast.makeText(Intent2.this, "서버 전송에 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMedicationTimes() {
        apiService.getMedicationTimes().enqueue(new Callback<List<MedicationTime>>() {
            @Override
            public void onResponse(Call<List<MedicationTime>> call, Response<List<MedicationTime>> response) {
                if (response.isSuccessful()) {
                    List<MedicationTime> medicationTimes = response.body();
                    // 클릭 이벤트 리스트를 처리
                    for (MedicationTime medicationTime : medicationTimes) {
                        Log.d("MainActivity", "Date: " + medicationTime.getDate() +
                                ", Morning: " + medicationTime.getMorningMedTime() +
                                ", Afternoon: " + medicationTime.getAfternoonMedTime() +
                                ", Evening: " + medicationTime.getEveningMedTime());
                    }
                } else {
                    Log.e("MainActivity", "Failed to get medication times");
                }
            }

            @Override
            public void onFailure(Call<List<MedicationTime>> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }
}




