package com.example.facultyprofile.managers;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.facultyprofile.Listeners.OnObjectFetchListener;
import com.example.facultyprofile.Listeners.OnObjectListFetchListener;
import com.example.facultyprofile.Models.Professors;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class databasehelper {
    private FirebaseFirestore db;
    public databasehelper(){
        db = FirebaseFirestore.getInstance();
    }

    public void fetchallprofessors(final OnObjectListFetchListener onObjectListFetchListener){
        db.collection("professors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult()==null){
                                onObjectListFetchListener.onListChanged(null,true);
                            }
                            else {
                                ArrayList<Professors> list = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    list.add(document.toObject(Professors.class));
                                }
                                onObjectListFetchListener.onListChanged(list,false);
                            }
                        } else {
                            onObjectListFetchListener.onListChanged(null,true);
                        }
                    }
                });
    }

    public void fetchoneprofessors(String name, final OnObjectFetchListener onObjectFetchListener){
        db.collection("professors").document(name)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Professors professors = document.toObject(Professors.class);
                        onObjectFetchListener.onDataFetched(professors);
                    } else {
                        onObjectFetchListener.onDataFetched(null);
                    }
                } else {
                    onObjectFetchListener.onDataFetched(null);
                }
            }
        });
    }

    public void fetchoninterest(final String interest, final OnObjectListFetchListener onObjectListFetchListener){
        db.collection("professors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult()==null){
                                onObjectListFetchListener.onListChanged(null,true);
                            }
                            else {
                                ArrayList<Professors> list = new ArrayList<>();
                                int flag = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Professors professors = document.toObject(Professors.class);
                                    if (professors.getInterests().contains(interest)){
                                        list.add(professors);
                                        flag = 1;
                                    }
                                }
                                if (flag == 1){
                                    onObjectListFetchListener.onListChanged(list,false);
                                }
                                else {
                                    Log.e("hel","");
                                    onObjectListFetchListener.onListChanged(null,true);
                                }
                            }
                        } else {
                            onObjectListFetchListener.onListChanged(null,true);
                        }
                    }
                });
    }
}
