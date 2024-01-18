package com.example.androidprojectbtl.Presenter;

import com.example.androidprojectbtl.Model.ProductModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ProductPresenter implements IProduct, FirebaseHandler {
    private ProductModel model;

    private FirebaseHandler callback;

    public ProductPresenter(FirebaseHandler callback) {
        model = new ProductModel(this);
        this.callback = callback;
    }

    public void getLoaiSanPham() {
        model.getLoaiSanPham();
    }

    @Override
    public void store(ProductModel entity) {
        model.store(entity);
    }

    @Override
    public void edit(ProductModel entity, String s) {
        model.edit(entity, s);
    }

    @Override
    public void delete(String s) {
        model.delete(s);
    }

    @Override
    public void getAll() {
        model.getAll();
    }

    @Override
    public void getById(String s) {
        model.getById(s);
    }

    @Override
    public void OnSuccess() {
        callback.OnSuccess();
    }

    @Override
    public void OnFail() {
        callback.OnFail();
    }

    @Override
    public void OnComplete() {
        callback.OnComplete();
    }

    @Override
    public void OnGetAllSuccess(Task<QuerySnapshot> task) {
        callback.OnGetAllSuccess(task);
    }

    @Override
    public void OnGetOnceSuccess(Task<DocumentSnapshot> task) {
        callback.OnGetOnceSuccess(task);
    }
}
