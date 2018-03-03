package com.example.marzia.everydaycostapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    EditText dateEDT,transportEDT,foodEDT,otherEDT,SalaryEDT;
    TextView showTotal,addSalaryTXT;
    Button totalCal,showRecordBTN,addBTN,AddSalaryBTN;
    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateEDT=(EditText)findViewById(R.id.date_edt);
        dateEDT.setOnClickListener(new View.OnClickListener() {

            Calendar rightNow = Calendar.getInstance();
            int year = rightNow.get(Calendar.YEAR);
            int month = rightNow.get(Calendar.MONTH) + 1;
            int day = rightNow.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dateEDT.setText(dayOfMonth + "/" + month + "/" + year);
                }
            };

            @Override
            public void onClick(View view) {
                DatePickerDialog datepicker = new DatePickerDialog(MainActivity.this, dateSetListener, year, month, day);

                datepicker.show();

            }

        });
        transportEDT=(EditText)findViewById(R.id.transport_edt);
        foodEDT=(EditText)findViewById(R.id.food_cost_edt);
        otherEDT=(EditText)findViewById(R.id.other_cost_edt);
        SalaryEDT=(EditText)findViewById(R.id.salary_edt);

        showTotal=(TextView)findViewById(R.id.total_show_txt);
        //addSalaryTXT=(TextView)findViewById(R.id.add_salary_txt);

        totalCal=(Button)findViewById(R.id.total_btn);
        addBTN=(Button)findViewById(R.id.add_record_btn);
        showRecordBTN=(Button)findViewById(R.id.show_record_btn);
        AddSalaryBTN=(Button)findViewById(R.id.add_salary_btn);



        totalCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dateEDT.getText().toString().equals("") && !transportEDT.getText().toString().equals("") && !foodEDT.getText().toString().equals("") && !otherEDT.getText().toString().equals("") ){

                    CostCalculation calculation=new CostCalculation();

                    double trnsport,food,other;

                    trnsport=Double.parseDouble(transportEDT.getText().toString());
                    food=Double.parseDouble(foodEDT.getText().toString());
                    other=Double.parseDouble(otherEDT.getText().toString());
                    showTotal.setText(calculation.CostAdd(trnsport,food,other));
                }else{

                    final AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);

                    dialog.setCancelable(false);
                    dialog.setTitle("Warning!!!");
                    dialog.setMessage("Please give proper Date, Cost must have at least 0 input");


                    dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    AlertDialog alertDialog= dialog.create();
                    alertDialog.show();

                }

            }
        });

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!dateEDT.getText().toString().equals("") && !transportEDT.getText().toString().equals("") && !foodEDT.getText().toString().equals("") && !otherEDT.getText().toString().equals("") ){

                    final AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);

                    dialog.setCancelable(false);
                    dialog.setTitle("Save!!!");
                    dialog.setMessage("Are you sure you want to save the record???");


                    dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            double trnsport,food,other;

                            trnsport=Double.parseDouble(transportEDT.getText().toString());
                            food=Double.parseDouble(foodEDT.getText().toString());
                            other=Double.parseDouble(otherEDT.getText().toString());

                            String datestr=dateEDT.getText().toString().trim();
                            String transportStr=transportEDT.getText().toString().trim();
                            String foodStr=foodEDT.getText().toString().trim();
                            String otherStr=otherEDT.getText().toString().trim();

                            //String totalStr=;

                            CostCalculation calculation=new CostCalculation();
                            calculation.setDate(datestr);
                            calculation.setTransportCost(transportStr);
                            calculation.setFoodCost(foodStr);
                            calculation.setOtherCost(otherStr);
                            calculation.setCostAddString(calculation.CostAdd(trnsport,food,other));
                            calculation.strRemainSalaryMethod();

                            System.out.printf("\n\n--------------------------------------------------------trans "+trnsport+"\n\nfood "+food+
                                    "\n\nother "+other+"\n\ntotal "+totalCal+"\n\n");

                            //showTotal.setText(calculation.CostAdd(trnsport,food,other));

                            DataBaseHelperCost dataBaseHelperCost=new DataBaseHelperCost(MainActivity.this);
                            if(dataBaseHelperCost.addEveryDayCost(calculation)){

                                Toast.makeText(MainActivity.this, "save successfully", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "Sorry try again", Toast.LENGTH_SHORT).show();

                            }

                            dateEDT.setText("");
                            transportEDT.setText("");
                            foodEDT.setText("");
                            otherEDT.setText("");
                            showTotal.setText("");

                        }
                    });


                    dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    AlertDialog alertDialog= dialog.create();
                    alertDialog.show();







                }else{

                    final AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);

                    dialog.setCancelable(false);
                    dialog.setTitle("Warning!!!");
                    dialog.setMessage("Please give proper Date, Cost must have at least 0 input");


                    dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    AlertDialog alertDialog= dialog.create();
                    alertDialog.show();


//                    double addSalary=0.0;
//
//                    //addSalary=Double.parseDouble(transportEDT.getText().toString());
//                    CostCalculation costCalculation=new CostCalculation();
//                    costCalculation.AddSalaryMethod(addSalary);


                }






            }
        });

        showRecordBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentshow=new Intent(MainActivity.this,ShowRecordActivity.class);
                startActivity(intentshow);
            }
        });




       SalaryEDT.setVisibility(GONE);
//        addSalaryTXT.setVisibility(GONE);
        //txt.setVisibility(VISIBLE);



        //if the textView is shown, the btn click will hide it and show editText
        //instead, and same happens for editText
     AddSalaryBTN.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if (SalaryEDT.getVisibility() == View.VISIBLE) {
                 CostCalculation calculation=new CostCalculation();
                 double s=Double.parseDouble(SalaryEDT.getText().toString());
                 calculation.AddSalaryMethod(s);
                 System.out.printf("\n\n--------------------------------------------------------"+SalaryEDT.getText().toString()+"\n\n");
                 Toast.makeText(MainActivity.this,"Your Salary is "+ SalaryEDT.getText().toString(), Toast.LENGTH_SHORT).show();
                 SalaryEDT.setVisibility(View.INVISIBLE);

             } else {
                 SalaryEDT.setText(null);
                 SalaryEDT.setVisibility(View.VISIBLE);

             }
         }
     });

    }
}
