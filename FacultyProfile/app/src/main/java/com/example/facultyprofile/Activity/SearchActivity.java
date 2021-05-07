package com.example.facultyprofile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.facultyprofile.R;
import com.google.firebase.FirebaseApp;

import java.util.Arrays;


public class SearchActivity extends BaseActivity {
    EditText univ,stream;
    AutoCompleteTextView dep,name,interest;
    Spinner s1;
    Button but;
    String[] dept ={"CSE"};
    String[] names ={"Anisha Radhakrishnan","Anupa Vijai","Archanaa rajendran","Bagavathi Sivakumar P.","Baskar A","Bindu K R","C.Arunkumar","D Venkataraman","Dhanya M Dhanalakshmy","Dhanya N.M.","Dr. Senthilkumar Mathi","GOVINDARAJAN JAYAPRAKASH","Ganesh Neelakanta Iyer","Gowtham Ramesh","Jevitha KP","Jeyakumar","Latha Parameswaran","M Sethumadhavan","M.Prathilothamai","Malathi P","N Harini","N Radhika","Padmavathi S","Prakash Periyasamy","Prashant R. Nair","Priyanka Kumar","RR sathiya","Raghesh Krishnan K","Rajathilagam B","Ramya gr","Ritwik Murali","Saba Rish","Senthilkumar Thangavel","Shanmuga Priya S","Shriram K Vasudevan","Shunmuga Velayutham","Sikha o.k","Sini Raj Pulari","Swapna T R","Vamsee Krishna Kiran M","Vidhya Balasubramanian","gayathri v"};
    String[] interests={"Evolutionary computing", "Image Processing", "Computer Vision", "Nature Inspired Algorithms", "Machine learning", "Predictive Analytics", "IoT", "Time Series Analysis and Forecasting", "Machine Learning & Pattern Recognition", "Artificial intelligence", "Internet of things", "Information Retreival", "Machine Learning", "Bioinformatics", "Neural Networks", "Image processing", "Differential Evolution", "Next Generation IP Mobility", "Vehicular Networks", "Internet of Things", "Information Security", "Wireless Networks", "Cloud Computing", "Game Theory", "Software Engineering", "Edge Computing", "Web Security", "Information Retrieval", "Semantic Web", "Web and Mobile Application Security", "Formal methods", "Evolutionary Computing", "Parallel and Distributed Models of Evolutionary Algorithm.", "Multimedia security", "Information retrieval", "Cryptography", "Boolean Functions", "Multimedia Security", "Paring Based Cryptography", "Big Data", "Image Steganography", "cryptography and security", "materials", "Image analysis", "Pattern recognition", "Data structures", "Computer graphics", "Application of IT tools for Supply Chain Management", "Management Information Systems", "Electronic Business", "Cyber Security", "Parallel and Distributed System", "Blockchain Technology", "Data mining", "Bigdata", "cloud computing", "Biomedical Image Analysis", "Compiler Design", "Computer Programming (in 'C')", "Formal Languages and Automata", "Signal Processing", "Predictive Analytics and Machine Learning", "Malware Analysis", "Threat Modeling", "Privacy Preservation (Vulnerability Analysis) on Social Networks", "Botnets", "Data Mining", "Trajectory mining", "WSN", "Video Processing", "Cloud computing", "Big Data Analytics", "Networking", "Embedded Systems", "Operating Systems", "Evolutionary Computation", "Computer Science Education", "image processing", "parellel computing", "Reccomender Systems", "Natural Language Processing", "Computational Intelligence and Medical Imaging","Recommender systems", "Cloud-IaaS", "Pervasive Systems", "Cyber-physical systems", "Distributed Systems"};

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());
        adapter = new ArrayAdapter<>(SearchActivity.this,android.R.layout.simple_selectable_list_item,names);
        adapter1 = new ArrayAdapter<>(SearchActivity.this,android.R.layout.simple_selectable_list_item,dept);
        adapter2 = new ArrayAdapter<>(SearchActivity.this,android.R.layout.simple_selectable_list_item,interests);

        univ= findViewById(R.id.UNIVERSITYNAME);
        dep= findViewById(R.id.DEPARTMENT);
        name= findViewById(R.id.FACULTYNAME);
        stream=findViewById(R.id.STREAM);
        interest=findViewById(R.id.INTEREST);

        but=findViewById(R.id.search);

        dep.setVisibility(View.GONE);
        interest.setVisibility(View.GONE);

        univ.setEnabled(false);
        stream.setEnabled(false);
        s1=findViewById(R.id.course_name);

        dep.setThreshold(1);
        name.setThreshold(1);
        interest.setThreshold(1);
        dep.setAdapter(adapter1);
        name.setAdapter(adapter);
        interest.setAdapter(adapter2);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.showDropDown();
            }
        });
        dep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dep.showDropDown();
            }
        });
        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               interest.showDropDown();
            }
        });

        ArrayAdapter<CharSequence> ada = ArrayAdapter.createFromResource(this,
                R.array.course, android.R.layout.simple_spinner_item);
        ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(ada);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    dep.setText("");
                    interest.setText("");
                    name.setVisibility(View.VISIBLE);
                    dep.setVisibility(View.GONE);
                    interest.setVisibility(View.GONE);
                }
                else if(position==1){
                    name.setText("");
                    interest.setText("");
                    dep.setVisibility(View.VISIBLE);
                    name.setVisibility(View.GONE);
                    interest.setVisibility(View.GONE);
                }
                else {
                    name.setText("");
                    dep.setText("");
                    interest.setVisibility(View.VISIBLE);
                    dep.setVisibility(View.GONE);
                    name.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasInternetConnection()){
                    if(name.getText().toString().matches("")&&dep.getText().toString().matches("")&&interest.getText().toString().matches("")){
                        Toast.makeText(SearchActivity.this,"Please Enter Some Value",Toast.LENGTH_LONG).show();
                    }
                    else if(dep.getText().toString().matches("")&&interest.getText().toString().matches("")){
                        if(Arrays.asList(names).contains(name.getText().toString())&&validateallstring(name.getText().toString())){
                        Intent intent = new Intent(SearchActivity.this,ProfessorListActivity.class);
                        intent.putExtra("flag","0");
                        intent.putExtra("name",name.getText().toString());
                        startActivity(intent);}
                        else{
                            name.setText("");
                            Toast.makeText(SearchActivity.this,"Enter a valid value!!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(name.getText().toString().matches("")&&dep.getText().toString().matches("")){
                        if(Arrays.asList(interests).contains(interest.getText().toString())&&validateallstring(interest.getText().toString())){
                            Intent intent = new Intent(SearchActivity.this,ProfessorListActivity.class);
                            intent.putExtra("flag","2");
                            intent.putExtra("interest",interest.getText().toString());
                            startActivity(intent);
                        }
                        else{
                            interest.setText("");
                            Toast.makeText(SearchActivity.this,"Enter a valid value!!!",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Intent intent = new Intent(SearchActivity.this,ProfessorListActivity.class);
                        intent.putExtra("flag","1");
                        intent.putExtra("dep",dep.getText().toString());
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(SearchActivity.this,"No Internet!!!",Toast.LENGTH_LONG).show();

                }

            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public boolean validateallstring(String name){
        return name.matches("^[ a-zA-Z.]+$");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item3:
                Intent i=new Intent(this, list.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
