package com.example.marzia.everydaycostapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by marzia on 2/13/2018.
 */

public class AdapterDetailCost extends ArrayAdapter {

    Context context;
    ArrayList<CostCalculation> list;

    //int layout=R.layout.detail_record_row_layout;

        public AdapterDetailCost(@NonNull Context context, ArrayList<CostCalculation> list) {
            super(context, R.layout.detail_record_row_layout);
            this.context = context;
            this.list = list;
        }

    @Override
    public int getCount() {
        return list.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.detail_record_row_layout,parent,false);

        TextView DateTxt=(TextView)view.findViewById(R.id.date_txt);
        TextView TransportCostTxt=(TextView)view.findViewById(R.id.transport_txt);
        TextView FoodCostTxt=(TextView)view.findViewById(R.id.food_txt);
        TextView OtherCostTxt=(TextView)view.findViewById(R.id.other_txt);
        TextView TotalCostTxt=(TextView)view.findViewById(R.id.total_txt);
        TextView RemainSalaryTxt=(TextView)view.findViewById(R.id.remainSalary_txt);

        //Button UpdateBTN=(Button)view.findViewById(R.id.udate_btn);

        DateTxt.setText(list.get(position).getDate());
        TransportCostTxt.setText(list.get(position).getTransportCost());
        FoodCostTxt.setText(list.get(position).getFoodCost());
        OtherCostTxt.setText(list.get(position).getOtherCost());
        TotalCostTxt.setText(list.get(position).getCostAddString());
        RemainSalaryTxt.setText(list.get(position).getRemainSalaryMethod());



//       UpdateBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////
////                DataBaseHelperCost dataBaseHelperCost=new DataBaseHelperCost(context);
////                if(dataBaseHelperCost.updateData()){
////
////                    Toast.makeText(context, "save successfully", Toast.LENGTH_SHORT).show();
////                }else {
////                    Toast.makeText(context, "Sorry try again", Toast.LENGTH_SHORT).show();
////
////                }
//
//            }
//        });


        return view;
    }
}
