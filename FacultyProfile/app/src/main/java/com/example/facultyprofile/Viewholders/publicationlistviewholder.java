package com.example.facultyprofile.Viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyprofile.Adapters.publicationlistadapter;
import com.example.facultyprofile.Models.Publications;
import com.example.facultyprofile.R;

public class publicationlistviewholder extends RecyclerView.ViewHolder {

    private TextView publicationtitle,citescount,publisher,year;

    public publicationlistviewholder(@NonNull View itemView, final publicationlistadapter.CallBack callBack, final Context context) {
        super(itemView);
        publicationtitle = itemView.findViewById(R.id.publicationname);
        citescount = itemView.findViewById(R.id.citescount);
        publisher = itemView.findViewById(R.id.publishername);
        year = itemView.findViewById(R.id.yearname);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (callBack != null && position != RecyclerView.NO_POSITION) {
                    callBack.onItemClick(position, v);
                }
            }
        });

    }

    public void bindData(Publications publications) {
        publicationtitle.setText(publications.getTitle());
        citescount.setText(String.valueOf(publications.getCitedby()));
        publisher.setText(String.valueOf(publications.getPublisher()));
        year.setText(String.valueOf(publications.getYear()));
    }
}
