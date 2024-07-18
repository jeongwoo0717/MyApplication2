package com.example.myapplication2;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/meal-times")
    Call<MealTime> createMealTime(@Body MealTimeCreate mealTimeCreate);

    @GET("/meal-times")
    Call<List<MealTime>> getMealTimes();

    @POST("/medication-times")
    Call<MedicationTime> createMedicationTime(@Body MedicationTimeCreate medicationTimeCreate);

    @GET("/medication-times")
    Call<List<MedicationTime>> getMedicationTimes();
}




