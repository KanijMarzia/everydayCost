package com.example.marzia.everydaycostapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowRecordActivity extends AppCompatActivity {

    ViewStub DetailListStub,TableListStub;
    ListView Detailshowlist,Tableshowlist;
    AdapterDetailCost adapterCost;
    AdapterTableCost adapterTableCost;
    ArrayList<CostCalculation> list;

    int currentViewMode=0;

    static final int view_Mode_Detail=0;
    static final int view_Mode_Table=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);

        DetailListStub=(ViewStub)findViewById(R.id.stub_detail_list);
        TableListStub=(ViewStub)findViewById(R.id.stub_table_list);

        DetailListStub.inflate();
        TableListStub.inflate();

        Detailshowlist=(ListView)findViewById(R.id.detail_list_view_id);
        Tableshowlist=(ListView)findViewById(R.id.table_list_view_id);

        DataBaseHelperCost dataBaseHelperCost=new DataBaseHelperCost(ShowRecordActivity.this);
        list=dataBaseHelperCost.getAllCostRecord();

        SharedPreferences sharedPreferences=getSharedPreferences("ViewMode",MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentviewmode",view_Mode_Detail);

        switchView();

        Detailshowlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AlertDialog.Builder detaildialog=new AlertDialog.Builder(ShowRecordActivity.this);

                detaildialog.setCancelable(false);
                detaildialog.setTitle("Edit");
                detaildialog.setMessage("Click for Edit or Delete");

                detaildialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });


                detaildialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog detailalertDialog= detaildialog.create();
                detailalertDialog.show();
            }
        });


        Tableshowlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AlertDialog.Builder dialog=new AlertDialog.Builder(ShowRecordActivity.this);

                dialog.setCancelable(false);
                dialog.setTitle("Edit");
                dialog.setMessage("Click for Edit or Delete");

                dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });


                dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog= dialog.create();
                alertDialog.show();
            }
        });


    }

    private void switchView() {
        if(view_Mode_Detail==currentViewMode){
            DetailListStub.setVisibility(View.VISIBLE);

            TableListStub.setVisibility(View.GONE);
        }
        else
        {

            DetailListStub.setVisibility(View.GONE);

            TableListStub.setVisibility(View.VISIBLE);
        }
        setAdapter();
    }

    private void setAdapter() {

        if(view_Mode_Detail==currentViewMode){
            adapterCost=new AdapterDetailCost(ShowRecordActivity.this,list);
            Detailshowlist.setAdapter(adapterCost);
        }
        else
        {
            adapterTableCost=new AdapterTableCost(ShowRecordActivity.this,list);
            Tableshowlist.setAdapter(adapterTableCost);

            TableListStub.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflaite the menu on the top
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.table_view_menu:
                if(view_Mode_Detail==currentViewMode){
                   currentViewMode=view_Mode_Table;
                }
                switchView();
                SharedPreferences sharedPreferencesTable=getSharedPreferences("ViewMode",MODE_PRIVATE);
                SharedPreferences.Editor editorTable=sharedPreferencesTable.edit();
                editorTable.putInt("currentviewmode",currentViewMode);
                editorTable.commit();
                break;
            case R.id.detail_view_menu:

                if(view_Mode_Table==currentViewMode){
                    currentViewMode=view_Mode_Detail;
                }
                switchView();
                SharedPreferences sharedPreferences=getSharedPreferences("ViewMode",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("currentviewmode",currentViewMode);
                editor.commit();
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
