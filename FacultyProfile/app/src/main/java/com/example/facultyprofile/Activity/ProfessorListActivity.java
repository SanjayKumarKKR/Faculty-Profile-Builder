package com.example.facultyprofile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyprofile.Adapters.professorlistadapter;
import com.example.facultyprofile.Listeners.OnObjectFetchListener;
import com.example.facultyprofile.Listeners.OnObjectListFetchListener;
import com.example.facultyprofile.Models.Professors;
import com.example.facultyprofile.R;
import com.example.facultyprofile.managers.databasehelper;

import java.util.ArrayList;

public class ProfessorListActivity extends BaseActivity {
    databasehelper db = new databasehelper();
    professorlistadapter Professorlistadapter;
    RecyclerView professorrecylerview;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        String str = intent.getStringExtra("flag");
        setContentView(R.layout.professors_show_list);
        professorrecylerview = findViewById(R.id.professorlistRecyclerView);
        Professorlistadapter = new professorlistadapter(this,this);
        Professorlistadapter.setCallBack(new professorlistadapter.CallBack() {
            @Override
            public void onItemClick(int position, View view) {
                Professors professors = Professorlistadapter.getItemByPosition(position);
                Intent intent1 = new Intent(ProfessorListActivity.this,ProfileActivity.class);
                intent1.putExtra("Obj", professors);
                startActivity(intent1);
            }
        });
        professorrecylerview.setAdapter(Professorlistadapter);
        layoutManager=new LinearLayoutManager(ProfessorListActivity.this);
        professorrecylerview.setHasFixedSize(true);
        professorrecylerview.setLayoutManager(layoutManager);

        final OnObjectListFetchListener ob = new OnObjectListFetchListener() {
            @Override
            public void onListChanged(ArrayList list, boolean isEmpty) {
                if (isEmpty){
                    cancelLoading();
                    Intent i = new Intent(ProfessorListActivity.this,SearchActivity.class);
                    startActivity(i);
                }
                else {
                    cancelLoading();
                    Professorlistadapter.setProfessorsArrayList(list);

                }
            }};
        showLoadingDialog();
        if(str.equals("0")){
            db.fetchoneprofessors(intent.getStringExtra("name"), new OnObjectFetchListener() {
                @Override
                public void onDataFetched(Object object) {
                    cancelLoading();
                    Intent intent2 = new Intent(ProfessorListActivity.this,ProfileActivity.class);
                    intent2.putExtra("Obj", (Professors)object);
                    Log.e("hello",((Professors) object).getname());
                    startActivity(intent2);
                }
            });
        }
        else if(str.equals("2")){
            db.fetchoninterest(intent.getStringExtra("interest"), ob);
        }
        else{
            db.fetchallprofessors(ob);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfessorListActivity.this,SearchActivity.class);
        startActivity(intent);
    }
}
