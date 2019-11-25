package com.OOD.jihoonpark.computerbudget;

import android.animation.Animator;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ThemedSpinnerAdapter;

import com.airbnb.lottie.Cancellable;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Spinner orderSpinner;
    Spinner priceSpinner;
    LottieAnimationView animationView;
    HashMap<String,Integer> priceList = new HashMap();
    HashMap<String,String> imageURLList = new HashMap();
    HashMap<String,String> titleList = new HashMap();

    String targetText;
    int budgetText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button showNextActivityButton = findViewById(R.id.showNextActivityButton);

        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setVisibility(View.GONE);

        orderSpinner = findViewById(R.id.OrderSpinner);
        priceSpinner = findViewById(R.id.PriceSpinner);

        targetText = "간단한 사무용, 동영상 감상";
        budgetText = 500000;

        showNextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new shoppingTask().execute();
                }catch (Exception e){

                }
            }
        });

        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                targetText = orderSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                targetText = orderSpinner.getSelectedItem().toString();
            }
        });

        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(priceSpinner.getSelectedItem().toString().equals("50만원 미만")){
                    budgetText = 500000;
                }else if(priceSpinner.getSelectedItem().toString().equals("50만원 이상 100만원 미만")){
                    budgetText = 1000000;

                }else if(priceSpinner.getSelectedItem().toString().equals("100만원 이상 150만원 미만")){
                    budgetText = 1500000;
                }else{
                    budgetText = 2000000;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public class shoppingTask extends AsyncTask{
        @Override protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                animationView.loop(true);

                priceCalculate calculate = new priceCalculate();
                priceList = calculate.moneyAnalysis(budgetText);
                calculate.targetAnalysis(targetText);

                NaverAPI apiUser = new NaverAPI();
                apiUser.parseAll(priceList);

                titleList = apiUser.productList;
                imageURLList = apiUser.imageList;
                priceList = apiUser.finalPriceList;
            }
            catch (Exception e){
                System.out.println("   ????????????????          :        " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            animationView.cancelAnimation();

            Intent intent = new Intent(MainActivity.this, Scroll_Activity.class);
            intent.putExtra("title", titleList);
            intent.putExtra("price", priceList);
            intent.putExtra("imageURL", imageURLList);
            startActivity(intent);
        }
    }

}
