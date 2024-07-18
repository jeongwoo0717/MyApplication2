package com.example.myapplication2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Intent1 extends AppCompatActivity {
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);

        // Retrofit 초기화
        Retrofit retrofit = RetrofitClient.getClient("http://183.99.154.178:9999/");
        apiService = retrofit.create(ApiService.class);

        // 버튼 클릭 이벤트 처리
        Button buttonYes = findViewById(R.id.button_yes);
        Button buttonNo = findViewById(R.id.button_no);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordMealTime(true);
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordMealTime(false);
            }
        });

        // 버튼 클릭 이벤트 조회
        getMealTimes();
    }

    private void recordMealTime(boolean isYes) {
        String timeValue = isYes ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()).format(new Date()) : "-";
        MealTimeCreate mealTimeCreate = new MealTimeCreate(timeValue);

        apiService.createMealTime(mealTimeCreate).enqueue(new Callback<MealTime>() {
            @Override
            public void onResponse(Call<MealTime> call, Response<MealTime> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "Meal time recorded successfully");
                    Toast.makeText(Intent1.this, "성공적으로 전송되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("MainActivity", "Failed to record meal time");
                    Toast.makeText(Intent1.this, "서버 전송에 실패했습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MealTime> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                Toast.makeText(Intent1.this, "서버 전송에 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMealTimes() {
        apiService.getMealTimes().enqueue(new Callback<List<MealTime>>() {
            @Override
            public void onResponse(Call<List<MealTime>> call, Response<List<MealTime>> response) {
                if (response.isSuccessful()) {
                    List<MealTime> mealTimes = response.body();
                    // 클릭 이벤트 리스트를 처리
                    for (MealTime mealTime : mealTimes) {
                        Log.d("MainActivity", "Date: " + mealTime.getDate() +
                                ", Morning: " + mealTime.getMorningTime() +
                                ", Lunch: " + mealTime.getLunchTime() +
                                ", Dinner: " + mealTime.getDinnerTime());
                    }
                } else {
                    Log.e("MainActivity", "Failed to get meal times");
                }
            }

            @Override
            public void onFailure(Call<List<MealTime>> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }
}
