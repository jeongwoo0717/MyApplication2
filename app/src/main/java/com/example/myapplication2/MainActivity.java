package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;

    private TextView mTextView;
    private Handler mHandler = new Handler();
    private Runnable mUpdateTimeTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextView = findViewById(R.id.textView);

        updateDateTime();
        mUpdateTimeTask = new Runnable() {
            public void run() {
                updateDateTime();
                mHandler.postDelayed(this, 1000); // 1초마다 실행
            }
        };
        mHandler.postDelayed(mUpdateTimeTask, 1000);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(MainActivity.this,Intent1.class);
                startActivity(Intent);
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent_2 = new Intent(MainActivity.this,Intent2.class);
                startActivity(Intent_2);
            }
        });

    }
    private void updateDateTime() {
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

        String dateStr = dateFormat.format(currentDate);
        String timeStr = timeFormat.format(currentDate);

        mTextView.setText("날짜: " + dateStr + "\n시간: " + timeStr);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

}







