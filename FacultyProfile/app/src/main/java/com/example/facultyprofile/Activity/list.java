package com.example.facultyprofile.Activity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.facultyprofile.Listeners.OnObjectFetchListener;
import com.example.facultyprofile.Models.Professors;
import com.example.facultyprofile.R;
import com.example.facultyprofile.managers.database;
import com.example.facultyprofile.managers.databasehelper;

import java.util.ArrayList;

public class list extends BaseActivity {
    private database db;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView userlist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        db=new database(this);
        listItem=new ArrayList<>();
        userlist=findViewById(R.id.listv);
        viewData();
        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                databasehelper db1;
                db1=new databasehelper();
                showLoadingDialog();
                db1.fetchoneprofessors(userlist.getItemAtPosition(position).toString(), new OnObjectFetchListener() {
                    @Override
                    public void onDataFetched(Object object) {
                        cancelLoading();
                        Intent intent2 = new Intent(list.this,ProfileActivity.class);
                        intent2.putExtra("Obj", (Professors)object);
                        startActivity(intent2);
                    }
                });
            }
        });
        userlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                showWarningDialog("Do you want to remove item from bookmark",userlist.getItemAtPosition(position).toString());

                return true;
            }
        });
    }

    private void viewData() {
        Cursor cursor= db.list();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                listItem.add(cursor.getString(0));

            }

            adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            userlist.setAdapter(adapter);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(list.this,SearchActivity.class);
        startActivity(intent);
    }


}
