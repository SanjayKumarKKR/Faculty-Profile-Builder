package com.example.facultyprofile.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyprofile.Models.Professors;
import com.example.facultyprofile.R;
import com.example.facultyprofile.Viewholders.professorlistviewholder;

import java.util.ArrayList;
import java.util.List;


public class professorlistadapter extends RecyclerView.Adapter<professorlistviewholder> {

    private ArrayList<Professors> professorsArrayList=new ArrayList<>();
    private Context context;
    private Activity activity;
    private CallBack callBack;

    public professorlistadapter(Context context, Activity activity){
        this.context=context;
        this.activity=activity;
    }

    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }

    @NonNull
    @Override
    public professorlistviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.professors_list,viewGroup,false);
        return new professorlistviewholder(view,callBack,context,activity);
    }

    @Override
    public void onBindViewHolder(@NonNull professorlistviewholder professorlistviewholder, int i) {
        professorlistviewholder.bindData(professorsArrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return professorsArrayList.size();
    }

    public void setProfessorsArrayList(List<Professors> list){
//        participantsUserArrayList.clear();
        professorsArrayList.addAll(list);
        notifyDataSetChanged();
    }

    public Professors getItemByPosition(int position){
        return professorsArrayList.get(position);
    }

    public void removeAnItem(int position) {
        professorsArrayList.remove(position);
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onItemClick(int position,View view);
    }
}
