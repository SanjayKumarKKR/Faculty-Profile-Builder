package com.example.facultyprofile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyprofile.Models.Publications;
import com.example.facultyprofile.R;
import com.example.facultyprofile.Viewholders.publicationlistviewholder;

import java.util.ArrayList;
import java.util.List;


public class publicationlistadapter extends RecyclerView.Adapter<publicationlistviewholder> {

    private ArrayList<Publications> publicationsArrayList=new ArrayList<>();
    private Context context;
    private CallBack callBack;

    public publicationlistadapter(Context context){
        this.context=context;
    }

    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }

    @NonNull
    @Override
    public publicationlistviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.publications_show_list,viewGroup,false);
        return new publicationlistviewholder(view,callBack,context);
    }


    @Override
    public void onBindViewHolder(@NonNull publicationlistviewholder publicationlistviewholder, int i) {
        publicationlistviewholder.bindData(publicationsArrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return publicationsArrayList.size();
    }

    public void setProfessorsArrayList(List<Publications> list){
//        participantsUserArrayList.clear();
        publicationsArrayList.addAll(list);
        notifyDataSetChanged();
    }

    public Publications getItemByPosition(int position){
        return publicationsArrayList.get(position);
    }

    public void removeAnItem(int position) {
        publicationsArrayList.remove(position);
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onItemClick(int position,View view);
    }
}
