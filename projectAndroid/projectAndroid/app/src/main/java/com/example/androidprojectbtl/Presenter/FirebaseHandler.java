package com.example.androidprojectbtl.Presenter;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public interface FirebaseHandler {
    void OnSuccess();

    void OnFail();

    void OnComplete();
    void OnGetAllSuccess(Task<QuerySnapshot> task);
    void OnGetOnceSuccess(Task<DocumentSnapshot> task);
}
