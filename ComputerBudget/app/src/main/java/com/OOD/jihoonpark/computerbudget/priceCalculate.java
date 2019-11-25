package com.OOD.jihoonpark.computerbudget;

import java.util.HashMap;

public class priceCalculate {
    final int entry = 500000;
    final int middle = 1000000;
    final int high = 1500000;
    final int maximum = 2000000;

    final String cpu = "cpu";
    final String gpu = "gpu";
    final String ram = "ram";
    final String disk = "disk";
    final String power = "power";
    final String mainboard = "main";
    final String cooler = "cooler";
    final String ccase = "case";

    HashMap<String, Integer> priceList = new HashMap();

    public HashMap<String, Integer> moneyAnalysis(int money){
        if(money <= entry){
            priceList.put(cpu, 15);
            priceList.put(gpu, 0);
            priceList.put(ram, 10);
            priceList.put(disk, 10);
            priceList.put(power, 5);
            priceList.put(mainboard, 5);
            priceList.put(cooler, 0);
            priceList.put(ccase, 5);
        }
        else if(money <= middle){
            priceList.put(cpu, 25);
            priceList.put(gpu, 15);
            priceList.put(ram, 20);
            priceList.put(disk, 20);
            priceList.put(power, 5);
            priceList.put(mainboard, 5);
            priceList.put(cooler, 5);
            priceList.put(ccase, 5);
        }
        else if(money <= high){
            priceList.put(cpu, 35);
            priceList.put(gpu, 35);
            priceList.put(ram, 25);
            priceList.put(disk, 30);
            priceList.put(power, 10);
            priceList.put(mainboard, 10);
            priceList.put(cooler, 5);
            priceList.put(ccase, 5);
        }
        else{
            priceList.put(cpu, 50);
            priceList.put(gpu, 50);
            priceList.put(ram, 30);
            priceList.put(disk, 30);
            priceList.put(power, 10);
            priceList.put(mainboard, 20);
            priceList.put(cooler, 10);
            priceList.put(ccase, 10);
        }
        return priceList;
    }

    public void targetAnalysis(String target){
        if(target.equals("간단한 사무용, 동영상 감상")){
            priceList.remove(gpu);
            priceList.remove(cooler);
            priceList.put(gpu, 0);
            priceList.put(cooler, 0);
        }
        else if(target.equals("가벼운 게임, 적당한 활용")){
            priceList.remove(cpu);
            priceList.put(cpu, 10);
        }
        else if(target.equals("고오급 게임, 그래픽 작업")){

        }
        else{//코딩용.
            priceList.remove(gpu);
            priceList.put(gpu, 0);
        }
    }

}
