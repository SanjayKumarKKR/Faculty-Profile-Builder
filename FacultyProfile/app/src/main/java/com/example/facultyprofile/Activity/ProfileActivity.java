package com.example.facultyprofile.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyprofile.Adapters.publicationlistadapter;
import com.example.facultyprofile.Models.Professors;
import com.example.facultyprofile.Models.Publications;
import com.example.facultyprofile.R;
import com.example.facultyprofile.managers.database;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class ProfileActivity extends BaseActivity {
    RecyclerView publicationrecyclerview;
    private RecyclerView.LayoutManager layoutManager;
    publicationlistadapter Publicationlistadapter;
    Professors professors;
    ImageView profilepic;
    TextView name,hindex,i10index,cites;
    Spinner s1;
    ProgressBar progressBar;
    database db;
    Menu m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        progressBar=findViewById(R.id.progressofimage);
        progressBar.setVisibility(View.VISIBLE);
        s1=findViewById(R.id.selection);
        db=new database(this);
        professors= (Professors) getIntent().getSerializableExtra("Obj");
        publicationrecyclerview = findViewById(R.id.publicationslistview);
        Publicationlistadapter = new publicationlistadapter(this);
        Publicationlistadapter.setCallBack(new publicationlistadapter.CallBack() {
            @Override
            public void onItemClick(int position, View view) {
                Publications publications = Publicationlistadapter.getItemByPosition(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(publications.getUrl()));
                startActivity(i);
            }
        });
        final ArrayAdapter<String> ad = new ArrayAdapter<String>(this,R.layout.areaofshowlist,R.id.aoi,professors.getInterests());
        final ListView listView = findViewById(R.id.areaofinterestlistview);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.publication, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    publicationrecyclerview.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    listView.setAdapter(ad);
                }
                else{
                    listView.setVisibility(View.INVISIBLE);
                    publicationrecyclerview.setVisibility(View.VISIBLE);
                    publicationrecyclerview.setAdapter(Publicationlistadapter);
                    publicationrecyclerview.setHasFixedSize(true);
                    layoutManager=new LinearLayoutManager(ProfileActivity.this);
                    publicationrecyclerview.setLayoutManager(layoutManager);
                    Publicationlistadapter.setProfessorsArrayList(professors.getPublications());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        profilepic = findViewById(R.id.profileimageview);
        name = findViewById(R.id.profilename);
        hindex = findViewById(R.id.Hindexvalue);
        i10index = findViewById(R.id.I10indexvalue);
        cites = findViewById(R.id.citedbyvalue);


        Picasso.get()
                .load(professors.getUrl_picture())
                .fit()
                .into(profilepic, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progressBar.setVisibility(View.INVISIBLE);
                        profilepic.setImageResource(R.drawable.circular_person_image_background);
                    }
                });
        name.setText(professors.getname());
        hindex.setText(Integer.toString(professors.getHindex()));
        Boolean b=validateallnumber(Integer.toString(professors.getHindex()));
        i10index.setText(Integer.toString(professors.getI10index()));
        cites.setText(Integer.toString(professors.getCitedby()));
    }


    public boolean validateallnumber(String name){
        return name.matches("^[0-9]+$");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this,SearchActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listmenu, menu);
        m=menu;
        MenuItem item4 = m.findItem(R.id.item4);
        MenuItem item5 = m.findItem(R.id.item5);
        if(db.check(professors.getname())){
            item4.setVisible(false);
            item5.setVisible(true);
        }
        else {
            item5.setVisible(false);
            item4.setVisible(true);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MenuItem item4 = m.findItem(R.id.item4);
        MenuItem item5 = m.findItem(R.id.item5);
        switch (id){
            case R.id.item4:
                db.addName(professors.getname());
                item4.setVisible(false);
                item5.setVisible(true);
                return true;
            case R.id.item5:
                db.delete(professors.getname());
                item5.setVisible(false);
                item4.setVisible(true);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
