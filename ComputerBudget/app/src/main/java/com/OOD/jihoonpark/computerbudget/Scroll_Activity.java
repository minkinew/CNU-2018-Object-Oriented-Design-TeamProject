package com.OOD.jihoonpark.computerbudget;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Scroll_Activity extends AppCompatActivity {
    Bitmap CPUbitmap;
    Bitmap GPUbitmap;
    Bitmap RAMbitmap;
    Bitmap DISKbitmap;
    Bitmap Powerbitmap;
    Bitmap MainBoardbitmap;
    Bitmap Coolerbitmap;
    Bitmap Casebitmap;
    ImageView cpuImage;
    ImageView gpuImage;
    ImageView ramImage;
    ImageView diskImage;
    ImageView powerImage;
    ImageView mainBoardImage;
    ImageView coolerImage;
    ImageView caseImage;


    HashMap<String,String> titleList;
    HashMap<String,String> priceList;
    HashMap<String,String> imageURLList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity);

        Intent intent = getIntent();
        titleList = (HashMap)intent.getSerializableExtra("title");
        priceList = (HashMap)intent.getSerializableExtra("price");
        imageURLList = (HashMap)intent.getSerializableExtra("imageURL");

        TextView cpuTitle = findViewById(R.id.textView_CPUTitle);
        TextView cpuPrice = findViewById(R.id.textView_CPUPrice);
        cpuTitle.setText(titleList.get("cpu"));
        cpuPrice.setText(String.valueOf(priceList.get("cpu")));

        TextView gpuTitle = findViewById(R.id.textView_GPUTitle);
        TextView gpuPrice = findViewById(R.id.textView_GPUPrice);
        gpuTitle.setText(titleList.get("gpu"));
        gpuPrice.setText(String.valueOf(priceList.get("gpu")));

        TextView ramTitle = findViewById(R.id.textView_RAMTitle);
        TextView ramPrice = findViewById(R.id.textView_RAMPrice);
        ramTitle.setText(titleList.get("ram"));
        ramPrice.setText(String.valueOf(priceList.get("ram")));

        TextView diskTitle = findViewById(R.id.textView_DiskTitle);
        TextView diskPrice = findViewById(R.id.textView_DiskPrice);
        diskTitle.setText(titleList.get("disk"));
        diskPrice.setText(String.valueOf(priceList.get("disk")));

        TextView powerTitle = findViewById(R.id.textView_PowerTitle);
        TextView powerPrice = findViewById(R.id.textView_PowerPrice);
        powerTitle.setText(titleList.get("power"));
        powerPrice.setText(String.valueOf(priceList.get("power")));

        TextView mainboardTitle = findViewById(R.id.textView_MainBoardTitle);
        TextView mainboardPrice = findViewById(R.id.textView_MainBoardPrice);
        mainboardTitle.setText(titleList.get("main"));
        mainboardPrice.setText(String.valueOf(priceList.get("main")));

        TextView coolerTitle = findViewById(R.id.textView_CoolerTitle);
        TextView coolerPrice = findViewById(R.id.textView_CoolerPrice);
        coolerTitle.setText(titleList.get("cooler"));
        coolerPrice.setText(String.valueOf(priceList.get("cooler")));

        TextView caseTitle = findViewById(R.id.textView_CaseTitle);
        TextView casePrice = findViewById(R.id.textView_CasePrice);
        caseTitle.setText(titleList.get("case"));
        casePrice.setText(String.valueOf(priceList.get("case")));

        cpuImage = findViewById(R.id.imageView_CPU);
        gpuImage = findViewById(R.id.imageView_GPU);
        ramImage = findViewById(R.id.imageView_RAM);
        diskImage = findViewById(R.id.imageView_Disk);
        powerImage = findViewById(R.id.imageView_Power);
        mainBoardImage = findViewById(R.id.imageView_MainBoard);
        coolerImage = findViewById(R.id.imageView_Cooler);
        caseImage = findViewById(R.id.imageView_Case);

        Thread imageThread = new Thread() {
            public void run() {
                try {
                    String apiURL = imageURLList.get("cpu");
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.connect();

                    InputStream inputStream = con.getInputStream();
                    CPUbitmap = BitmapFactory.decodeStream(inputStream);

                    if(imageURLList.get("gpu") != null) {
                        apiURL = imageURLList.get("gpu");
                        url = new URL(apiURL);
                        con = (HttpURLConnection) url.openConnection();
                        con.setDoInput(true);
                        con.connect();
                    }
                    inputStream = con.getInputStream();
                    GPUbitmap = BitmapFactory.decodeStream(inputStream);

                    apiURL = imageURLList.get("ram");
                    url = new URL(apiURL);
                    con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.connect();

                    inputStream = con.getInputStream();
                    RAMbitmap = BitmapFactory.decodeStream(inputStream);

                    apiURL = imageURLList.get("disk");
                    url = new URL(apiURL);
                    con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.connect();

                    inputStream = con.getInputStream();
                    DISKbitmap = BitmapFactory.decodeStream(inputStream);

                    apiURL = imageURLList.get("power");
                    url = new URL(apiURL);
                    con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.connect();

                    inputStream = con.getInputStream();
                    Powerbitmap = BitmapFactory.decodeStream(inputStream);

                    apiURL = imageURLList.get("main");
                    url = new URL(apiURL);
                    con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.connect();

                    inputStream = con.getInputStream();
                    MainBoardbitmap = BitmapFactory.decodeStream(inputStream);
                    if(imageURLList.get("cooler") != null) {
                        apiURL = imageURLList.get("cooler");
                        url = new URL(apiURL);
                        con = (HttpURLConnection) url.openConnection();
                        con.setDoInput(true);
                        con.connect();
                    }

                    inputStream = con.getInputStream();
                    Coolerbitmap = BitmapFactory.decodeStream(inputStream);

                    apiURL = imageURLList.get("case");
                    url = new URL(apiURL);
                    con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.connect();

                    inputStream = con.getInputStream();
                    Casebitmap = BitmapFactory.decodeStream(inputStream);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };

        imageThread.start();

        try{
            imageThread.join();
            cpuImage.setImageBitmap(CPUbitmap);
            if(GPUbitmap != null)
                gpuImage.setImageBitmap(GPUbitmap);
            ramImage.setImageBitmap(RAMbitmap);
            diskImage.setImageBitmap(DISKbitmap);
            powerImage.setImageBitmap(Powerbitmap);
            mainBoardImage.setImageBitmap(MainBoardbitmap);
            if(Coolerbitmap != null)
                coolerImage.setImageBitmap(Coolerbitmap);
            caseImage.setImageBitmap(Casebitmap);
        }catch (Exception e){

        }

        Button shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dirName = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Download");  //디렉토리를 지정합니다.
                String fileString = "abc.jpg"; //공유할 이미지 파일 명
                File file = new File(dirName, fileString); //image 파일의 경로를 설정합니다.
                Uri mSaveImageUri = Uri.fromFile(file); //file의 경로를 uri로 변경합니다.
                Intent intent = new Intent(Intent.ACTION_SEND); //전송 메소드를 호출합니다. Intent.ACTION_SEND
                intent.setType("image/jpg"); //jpg 이미지를 공유 하기 위해 Type을 정의합니다.
                intent.putExtra(Intent.EXTRA_STREAM, mSaveImageUri); //사진의 Uri를 가지고 옵니다.
                startActivity(Intent.createChooser(intent, "Choose")); //Activity를 이용하여 호출 합니다.
            }
        });
    }



}
