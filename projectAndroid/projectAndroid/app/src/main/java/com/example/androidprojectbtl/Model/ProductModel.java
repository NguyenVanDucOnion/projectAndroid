package com.example.androidprojectbtl.Model;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidprojectbtl.Presenter.FirebaseHandler;
import com.example.androidprojectbtl.Presenter.IProduct;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ProductModel implements Serializable, IProduct {
    private final String PRODUCT_COLLECTION = "SanPham";
    private String id;
    private String idsp;

    private String loaisp;
    private String tensp;
    private String hinhanh;
    private long giatien;


    private int soluong;
    private String mota;
    private String trongluong;
    private String hansudung;
    private Long type;
    private FirebaseHandler callBack;
    FirebaseFirestore db;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    public ProductModel(FirebaseHandler callBack) {
        this.callBack = callBack;
        db = FirebaseFirestore.getInstance();
    }

    public ProductModel(String id, String idsp, String loaisp, String tensp, String hinhanh, long giatien, int soluong, String mota, String trongluong, String hansudung, Long type) {
        this.id = id;
        this.idsp = idsp;
        this.loaisp = loaisp;
        this.tensp = tensp;
        this.hinhanh = hinhanh;
        this.giatien = giatien;
        this.soluong = soluong;
        this.mota = mota;
        this.trongluong = trongluong;
        this.hansudung = hansudung;
        this.type = type;
    }

    public ProductModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public float getGiatien() {
        return giatien;
    }

    public void setGiatien(long giatien) {
        this.giatien = giatien;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTrongluong() {
        return trongluong;
    }

    public void setTrongluong(String trongluong) {
        this.trongluong = trongluong;
    }

    public String getHansudung() {
        return hansudung;
    }

    public void setHansudung(String hansudung) {
        this.hansudung = hansudung;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public void store(ProductModel entity) {
        db.collection(PRODUCT_COLLECTION).add(entity).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                callBack.OnSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.OnFail();
            }
        });
    }

    @Override
    public void edit(ProductModel entity, String s) {
        db.collection(PRODUCT_COLLECTION).document(s).set(entity).addOnSuccessListener((doc) -> {
            callBack.OnSuccess();
        }).addOnFailureListener((e) -> callBack.OnFail());
    }

    @Override
    public void delete(String s) {
        db.collection(PRODUCT_COLLECTION).document(s).delete().addOnSuccessListener((aVoid) -> {
            callBack.OnSuccess();
        });
    }

    @Override
    public void getAll() {
        db.collection(PRODUCT_COLLECTION).get().addOnCompleteListener((task) -> {
            callBack.OnGetAllSuccess(task);
        });
    }

    @Override
    public void getById(String s) {
        db.collection(PRODUCT_COLLECTION).document(s).get().addOnCompleteListener((task) -> {
            callBack.OnGetOnceSuccess(task);
        });
    }

    public void uploadImage(Uri uri, OnCompleteListener<Uri> onCompleteListener) {
        final StorageReference storageReference = storage.getReference().child(UUID.randomUUID().toString() + ".png");
        UploadTask uploadTask = storageReference.putFile(uri);
        uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()) {
                Log.d("Error ::: ", task.getException().getMessage());
            }
            return storageReference.getDownloadUrl();
        }).addOnCompleteListener(onCompleteListener).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Error:: ", e.getMessage());
            }
        });
    }

    public void getLoaiSanPham() {
        db.collection("LoaiSP").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                callBack.OnGetAllSuccess(task);
            }
        });
    }


}
