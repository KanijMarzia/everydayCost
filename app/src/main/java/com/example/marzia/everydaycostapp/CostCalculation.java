package com.example.marzia.everydaycostapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by marzia on 2/12/2018.
 */

public class CostCalculation {
   // Context context;
    private String date,transportCost,foodCost,otherCost,totalCost,salaryRemain;
    private int id;
    private double transportDouble,foodDouble,otherDouble,salary=0.0,salaryRemainDouble,totalcostDouble;
    private static double addSalaryDouble;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(String transportCost) {
        this.transportCost = transportCost;
    }

    public String getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(String foodCost) {
        this.foodCost = foodCost;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String CostAdd(double transportDouble,double foodDouble,double otherDouble){

        System.out.printf("\n-------------Adda Salary Paisi-----------trans "+transportDouble+"\n\n food "+foodDouble+"\n\n other "+otherCost+"\n\n");
        this.transportDouble=transportDouble;
        this.foodDouble=foodDouble;
        this.otherDouble=otherDouble;

        double result=this.transportDouble+this.foodDouble+this.otherDouble;
        this.totalcostDouble=result;


        return Double.toString(result);

    }

    public String costAddString(){
        String totalcost=CostAdd(transportDouble,foodDouble,otherDouble);
        return totalcost;

    }
    public void setCostAddString(String totalCost){

        this.totalCost=totalCost;
    }
    public String getCostAddString(){
        return totalCost;
    }


    public void AddSalaryMethod(double salary){
        System.out.printf("\n-------------Adda Salary Paisi-----------"+salary+"\n\n");
//        Toast.makeText(context,"costCalculationer"+addSalaryDouble,Toast.LENGTH_SHORT).show();
        this.addSalaryDouble=salary+this.addSalaryDouble;
        //this.addSalaryDouble=salary;
        System.out.printf("\n-------------Adda Salary Paisi salary-----------"+salary+"\n\n");
    }


    public String strRemainSalaryMethod(){
       // salaryRemainDouble=salary;
        salaryRemainDouble=this.addSalaryDouble-totalcostDouble;
        this.addSalaryDouble=salaryRemainDouble;
        salaryRemain=Double.toString(salaryRemainDouble);
        return salaryRemain;

    }

    public void setRemainSalaryMethod(String salaryRemain){
        this.salaryRemain=salaryRemain;
    }

    public  String getRemainSalaryMethod(){
        return salaryRemain;
    }
}
