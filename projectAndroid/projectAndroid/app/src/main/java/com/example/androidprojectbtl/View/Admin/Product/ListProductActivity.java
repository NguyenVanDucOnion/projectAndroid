package com.example.androidprojectbtl.View.Admin.Product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprojectbtl.Adapter.ProductAdapter;
import com.example.androidprojectbtl.Model.ProductModel;
import com.example.androidprojectbtl.Presenter.FirebaseHandler;
import com.example.androidprojectbtl.Presenter.ProductPresenter;
import com.example.androidprojectbtl.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity implements FirebaseHandler {

    ProductAdapter productAdapter;
    RecyclerView cyProduct;
    TextView btnAddNewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        initElement();
    }

    public void loadProduct() {
        ProductPresenter productPresenter = new ProductPresenter(this);
        productPresenter.getAll();
    }

    public void initElement() {
        ProductPresenter productPresenter = new ProductPresenter(this);
        ProductAdapter.ProductItemHandler productItemHandler = new ProductAdapter.ProductItemHandler() {
            @Override
            public void onEdit(String id) {
                Intent intent = new Intent(ListProductActivity.this, EditOrAddActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

            @Override
            public void onDelete(String id) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                productPresenter.delete(id);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(ListProductActivity.this);
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này").setPositiveButton("Xóa", dialogClickListener).setNegativeButton("Hủy", dialogClickListener).show();
            }
        };
        btnAddNewProduct = findViewById(R.id.btnAddNewProduct);
        cyProduct = findViewById(R.id.rcyProduct);
        productAdapter = new ProductAdapter(productItemHandler);
        cyProduct.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        cyProduct.setAdapter(productAdapter);

        btnAddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProductActivity.this, EditOrAddActivity.class);
                startActivity(intent);
            }
        });
        loadProduct();
    }

    @Override
    public void OnSuccess() {
        Toast.makeText(this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
        loadProduct();
    }

    @Override
    public void OnFail() {

    }

    @Override
    public void OnComplete() {

    }

    @Override
    public void OnGetAllSuccess(Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            List<ProductModel> productModels = new ArrayList<>();
            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                ProductModel productModel = documentSnapshot.toObject(ProductModel.class);
                productModel.setId(documentSnapshot.getId());
                productModels.add(productModel);
            }
            productAdapter.setData(productModels);
        }
    }

    @Override
    public void OnGetOnceSuccess(Task<DocumentSnapshot> task) {

    }
}