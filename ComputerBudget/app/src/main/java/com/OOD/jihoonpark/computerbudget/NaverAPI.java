package com.OOD.jihoonpark.computerbudget;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class NaverAPI {
    String clientId = "PnknnLL6iuuWWx3xsHer";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "gQrkBHJjHr";//애플리케이션 클라이언트 시크릿값";
    HttpURLConnection con;

    HashMap<String, String> productList = new HashMap<>();
    HashMap<String, Integer> finalPriceList= new HashMap<>();
    HashMap<String, String> imageList = new HashMap<>();


    public void setup(String model){
        try {
            String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=";
            URL url = new URL(apiURL + model);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void shoppingRequest(String productKind) {
        try {
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            JSONArray jsonArray = new JSONObject(response.toString()).getJSONArray("items");
            JSONObject temp = jsonArray.getJSONObject(0);
            finalPriceList.put(productKind , Integer.parseInt(temp.optString("lprice")));
            imageList.put(productKind ,temp.optString("image"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void parseCPU(int price) {
        if (price <= 5) {
            setup("인텔 펜티엄 골드 G5600");
            productList.put("cpu", "인텔 펜티엄 골드 G5600");
        }
        else if (price <= 15) {
            setup("i3 - 8100");
            productList.put("cpu", "i3 8100");
        }
        else if (price <= 25) {
            setup("i5 - 8500");
            productList.put("cpu", "i5 8500");
        }
        else {
            setup("i7 - 8700");
            productList.put("cpu", "i7 8700");
        }
        shoppingRequest("cpu");
    }

    public void parseGPU(int price){
        if(price <= 10) {
            setup("rx - 560 2gb");
            productList.put("gpu", "rx - 560 2gb");
        }
        else if(price <= 20){

            setup("gtx - 1050 ti 4gb");
            productList.put("gpu", "gtx - 1050 ti 4gb");
        }
        else if(price <= 30){
            setup("gtx - 1060 3gb");
            productList.put("gpu", "gtx - 1060 3gb");
        }
        else{
            setup("gtx - 1070 8gb");
            productList.put("gpu", "gtx - 1070 8gb");
        }
        shoppingRequest("gpu");
    }

    public void parseRAM(int price) {
        if (price <= 10) {
            setup("samsung ddr4 8gb - 21300");
            productList.put("ram", "samsung ddr4 8gb - 21300");
        }
        else if(price <= 20){
            setup("samsung ddr4 16gb - 21300");
            productList.put("ram", "samsung ddr4 16gb - 21300");
        }
        else{
            setup("rx - 560 2gb");
            productList.put("ram", "rx - 560 2gb");
        }
        shoppingRequest("ram");
    }
    public void parseDisk(int price){
        if(price <= 5) {
            setup("ADATA Ultimate SU800 STCOM (128GB)");
            productList.put("disk", "ADATA Ultimate SU800 STCOM (128GB)");
        }else if(price <= 10){
            setup("ADATA Ultimate SU800 M.2 2280 STCOM (256GB)");
            productList.put("disk", "ADATA Ultimate SU800 M.2 2280 STCOM (256GB)");
        }else if(price <= 15){
            setup("ADATA Ultimate SU800 STCOM (512GB)");
            productList.put("disk", "ADATA Ultimate SU800 STCOM (512GB)");
        }else{
            setup("삼성전자 970 PRO M.2 2280 (512GB)");
            productList.put("disk", "삼성전자 970 PRO M.2 2280 (512GB)");
        }
        shoppingRequest("disk");
    }

    public void parsePower(int price){
        if(price <= 5) {
            setup("마이크로닉스 Classic II 500W +12V Single Rail 85+");
            productList.put("power", "마이크로닉스 Classic II 500W +12V Single Rail 85+");
        }
        else if(price <= 10) {
            setup("마이크로닉스 Performance II PV 700W 80Plus Bronze Surge 4K");
            productList.put("power", "마이크로닉스 Performance II PV 700W 80Plus Bronze Surge 4K");
        }
        else if(price <= 15) {
            setup("마이크로닉스 Performance II HV 850W Bronze");
            productList.put("power", "마이크로닉스 Performance II HV 850W Bronze");
        }
        else{
            setup("마이크로닉스 Performance II HV 1000W Bronze");
            productList.put("power", "마이크로닉스 Performance II HV 1000W Bronze");
        }
        shoppingRequest("power");

    }
    public void parseMainBoard(String cpumodel,int price){
        if(price <= 5) {
            setup("GIGABYTE GA-H110M-DS2V 듀러블에디션 피씨디렉트");
            productList.put("main", "GIGABYTE GA-H110M-DS2V 듀러블에디션 피씨디렉트");
        }
        else if(price <= 10) {
            setup("GIGABYTE H310 D3 듀러블에디션 피씨디렉트");
            productList.put("main", "GIGABYTE H310 D3 듀러블에디션 피씨디렉트");
        }
        else if(price <= 15) {
            setup("ASUS PRIME H370-PLUS STCOM");
            productList.put("main", "ASUS PRIME H370-PLUS STCOM");
        }
        else{
            setup("마이크로닉스 Performance II HV 1000W Bronze");
            productList.put("main", "마이크로닉스 Performance II HV 1000W Bronze");
        }
        shoppingRequest("main");
    }

    public void parseCooler(int price){
        if(price <= 5) {
            setup("쿨러마스터 HYPER 212 LED Turbo RED");
            productList.put("cooler", "쿨러마스터 HYPER 212 LED Turbo RED");
        }
        else if(price <= 10) {
            setup("쿨러마스터 MASTERAIR MA610P RGB");
            productList.put("cooler", "쿨러마스터 MASTERAIR MA610P RGB");
        }
        else{
            setup("NOCTUA NH-D15");
            productList.put("cooler", "NOCTUA NH-D15");
        }
        shoppingRequest("cooler");
    }

    public void parseCase(int price){
        if(price <= 5){
            setup("ABKO NCORE 아수라 풀 아크릴 블랙");
            productList.put("case", "ABKO NCORE 아수라 풀 아크릴 블랙");
        }
        else if(price <= 10){
            setup("ABKO SUITMASTER 513G 이지스 강화유리 HALO");
            productList.put("case", "ABKO SUITMASTER 513G 이지스 강화유리 HALO");
        }
        else if(price <= 15){
            setup("NZXT H500 Matte Black");
            productList.put("case", "NZXT H500 Matte Black");
        }
        else{
            setup("Fractal Design Define S2 Blackout 강화유리");
            productList.put("case", "Fractal Design Define S2 Blackout 강화유리");
        }
        shoppingRequest("case");
    }

    public void parseAll(HashMap<String, Integer> priceList){
        parseCPU(priceList.get("cpu"));
        if(!priceList.get("gpu").equals(0)){
            parseGPU(priceList.get("gpu"));
        }else{
           finalPriceList.put("gpu" , 0);
        }
        parseRAM(priceList.get("ram"));
        parseDisk(priceList.get("disk"));

        if(!priceList.get("gpu").equals(0)) {
            parseCooler(priceList.get("cooler"));
        }else{
            finalPriceList.put("cooler" , 0);
        }
        parseMainBoard(productList.get("cpu"), priceList.get("main"));
        parsePower(priceList.get("power"));
        parseCase(priceList.get("case"));
    }

}