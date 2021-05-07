package com.example.facultyprofile.Viewholders;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyprofile.Adapters.professorlistadapter;
import com.example.facultyprofile.Models.Professors;
import com.example.facultyprofile.R;
import com.example.facultyprofile.managers.database;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class professorlistviewholder extends RecyclerView.ViewHolder {

    private TextView professorname,NOCcount,Hindexcount;
    private ToggleButton tg;
    private ImageView professorImageView;
    private ProgressBar progressBar;
    private database db;


    public professorlistviewholder(@NonNull View itemView, final professorlistadapter.CallBack callBack, final Context context, Activity activity) {
        super(itemView);
        professorname = itemView.findViewById(R.id.professorname);
        NOCcount = itemView.findViewById(R.id.NOCcount);
        Hindexcount = itemView.findViewById(R.id.hindexcount);
        professorImageView = itemView.findViewById(R.id.professorimage);
        progressBar = itemView.findViewById(R.id.progressofimage);
        tg =itemView.findViewById(R.id.toggle);
        db=new database(context);







        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (callBack != null && position != RecyclerView.NO_POSITION) {
                    callBack.onItemClick(position, v);
                }
            }
        });


        tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(tg.isChecked())
                {
                    db.addName(professorname.getText().toString());
                    db.select();
                }
                else
                {
                    Log.d("myApp", "remove");
                    db.delete(professorname.getText().toString());
                }
            }
        });

    }

    public void bindData(Professors professors) {
        professorname.setText(professors.getname());
        NOCcount.setText(String.valueOf(professors.getCitedby()));
        Hindexcount.setText(String.valueOf(professors.getHindex()));
        if(db.check(professorname.getText().toString())){
            tg.setChecked(true);
        }
        else
            tg.setChecked(false);
        setImageOfUser(professors.getUrl_picture());
    }

    private void setImageOfUser(String imageUrl) {
        if (imageUrl == null) {
            progressBar.setVisibility(View.INVISIBLE);
            professorImageView.setImageResource(R.drawable.circular_person_image_background);
        } else {
            Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .into(professorImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError(Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            professorImageView.setImageResource(R.drawable.circular_person_image_background);
                        }
                    });

        }
    }
}
