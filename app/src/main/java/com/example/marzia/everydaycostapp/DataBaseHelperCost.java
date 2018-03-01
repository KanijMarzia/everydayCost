package com.example.marzia.everydaycostapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by marzia on 2/13/2018.
 */

public class DataBaseHelperCost extends SQLiteOpenHelper{

    static String DATABASE_NAME="EveryDay_Cost_db";
    static int DATABASE_VERSION=1;



    static String TABLE_NAME="EveryDay_Cost_tbl";


    static String col_Id="ID";
    static String col_Date="Date";
    static String col_Transport_Cost="Transport_Cost";
    static String col_Food_Cost="Food_Cost";
    static String col_Other_Cost="Other_Cost";
    static String col_Total_Cost="Total_Cost";
    static String col_Remain_Salary="Remain_Salary";



    public DataBaseHelperCost(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_EveryDayCost_TABLE = "CREATE TABLE " + TABLE_NAME + "("+
                col_Id + " INTEGER PRIMARY KEY," +
                col_Date + " TEXT," +
                col_Transport_Cost + " TEXT," +
                col_Food_Cost + " TEXT," +
                col_Other_Cost + " TEXT," +
                col_Total_Cost + " TEXT"  +
                col_Remain_Salary + " TEXT"  +")";
        sqLiteDatabase.execSQL(CREATE_EveryDayCost_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }


    public boolean addEveryDayCost(CostCalculation costCalculation){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(col_Date,costCalculation.getDate());
        contentValues.put(col_Transport_Cost,costCalculation.getTransportCost());
        contentValues.put(col_Food_Cost,costCalculation.getFoodCost());
        contentValues.put(col_Other_Cost,costCalculation.getOtherCost());
        contentValues.put(col_Total_Cost,costCalculation.costAddString());
        contentValues.put(col_Remain_Salary,costCalculation.getRemainSalaryMethod());

        db.insert(TABLE_NAME,null,contentValues);
        db.close();

        return true;

    }

//    public boolean updateData(CostCalculation costCalculation) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        //contentValues.put(col_Id,costCalculation.getId());
//        contentValues.put(col_Date,costCalculation.getDate());
//        contentValues.put(col_Transport_Cost,costCalculation.getTransportCost());
//        contentValues.put(col_Food_Cost,costCalculation.getFoodCost());
//        contentValues.put(col_Other_Cost,costCalculation.getOtherCost());
//        contentValues.put(col_Total_Cost,costCalculation.costAddString());
//        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { col_Id });
//        return true;
//    }


    public ArrayList<CostCalculation> getAllCostRecord(){
        ArrayList<CostCalculation> CostRecordArray=new ArrayList<>();

        String selectQuery="SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do{
                CostCalculation costCalculation=new CostCalculation();
                costCalculation.setId(cursor.getInt(0));
                costCalculation.setDate(cursor.getString(1));
                costCalculation.setTransportCost(cursor.getString(2));
                costCalculation.setFoodCost(cursor.getString(3));
                costCalculation.setOtherCost(cursor.getString(4));
                costCalculation.setCostAddString(cursor.getString(5));
                costCalculation.setRemainSalaryMethod(cursor.getString(6));

                CostRecordArray.add(costCalculation);

            }while (cursor.moveToNext());
        }

        return CostRecordArray;
    }

}
