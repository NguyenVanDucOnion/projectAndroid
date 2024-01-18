package com.example.androidprojectbtl.View.Admin.Product;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidprojectbtl.Model.ProductCategory;
import com.example.androidprojectbtl.Model.ProductModel;
import com.example.androidprojectbtl.Presenter.FirebaseHandler;
import com.example.androidprojectbtl.Presenter.ProductPresenter;
import com.example.androidprojectbtl.Presenter.ProductView;
import com.example.androidprojectbtl.R;
import com.example.androidprojectbtl.View.FragMent.DatePickerFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EditOrAddActivity extends AppCompatActivity implements FirebaseHandler, ProductView, DatePickerDialog.OnDateSetListener {
    EditText edtName;
    EditText edtPrice;
    EditText edtWeight;
    EditText edtType;
    EditText edtQuantity;

    EditText edtDesc;

    EditText edtHanSuDung;
    Spinner spnCategory;

    ImageView imgPreview;

    ImageView btnSave;
    ImageView btnFresh;

    ImageView btnBack;
    private String dateConverted;
    private String categorySelected;
    private Uri imageSelected;

    private String id = "";
    private String oldImage;
    private boolean isAdding = false;
    String[] items = new String[]{"KFC", "MicDonald", "JoyBee"};

    ActivityResultLauncher<PickVisualMediaRequest> pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
        Picasso.get().load(uri).fit().centerCrop().into(imgPreview);
        imageSelected = uri;
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_or_add);
            id = getIntent().getExtras().getString("id");
        } catch (Exception e) {
            id = "";
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        ProductPresenter productPresenter = new ProductPresenter(this);
        imgPreview = findViewById(R.id.image_add);
        edtName = findViewById(R.id.edt_product_name);
        edtPrice = findViewById(R.id.edt_price);
        edtQuantity = findViewById(R.id.edt_quantity);
        edtWeight = findViewById(R.id.edt_weight);
        edtDesc = findViewById(R.id.edt_description);
        edtType = findViewById(R.id.edt_type);
        spnCategory = findViewById(R.id.category_spiner);
        edtHanSuDung = findViewById(R.id.edt_han_su_dung);
        btnSave = findViewById(R.id.btn_save);
        btnFresh = findViewById(R.id.btn_refresh);
        btnBack = findViewById(R.id.btn_add_back);
        if (!isAdd()) {
            loadDataIfEdit();
        } else {
            productPresenter.getLoaiSanPham();

        }
        iniEvents();
    }

    public void loadDataIfEdit() {
        ProductPresenter productPresenter = new ProductPresenter(this);
        productPresenter.getById(id);
    }

    public void clear() {
        imgPreview.setImageURI(null);
        edtName.setText("");
        edtPrice.setText("");
        edtQuantity.setText("");
        edtDesc.setText("");
        edtWeight.setText("");
        edtType.setText("");
    }


    public void initSpinner(List<String> productCategories) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, productCategories);
        spnCategory.setAdapter(adapter);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = items[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void initSpinner(ProductModel productModel) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spnCategory.setAdapter(adapter);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = items[i];
                Toast.makeText(EditOrAddActivity.this, categorySelected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void iniEvents() {
        imgPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickMedia.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (isAdding) {
                        return;
                    }
                    isAdding = true;
                    ProductModel productModel = new ProductModel();
                    productModel.setTensp(String.valueOf(edtName.getText()));
                    productModel.setMota(String.valueOf(edtDesc.getText()));
                    productModel.setGiatien(Long.parseLong(String.valueOf(edtPrice.getText())));
                    productModel.setSoluong(Integer.parseInt(String.valueOf(edtQuantity.getText())));
                    productModel.setType(Long.parseLong(String.valueOf(edtType.getText())));
                    productModel.setTrongluong(String.valueOf(edtWeight.getText()));
                    productModel.setHansudung(String.valueOf(edtHanSuDung.getText()));
                    productModel.setLoaisp(categorySelected);
                    if (imageSelected != null) {
                        productModel.uploadImage(imageSelected, new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri res = task.getResult();
                                    productModel.setHinhanh(res.toString());
                                    ProductPresenter productPresenter = new ProductPresenter(EditOrAddActivity.this);
                                    if (Objects.equals(id, "")) {
                                        productPresenter.store(productModel);
                                    } else {
                                        productPresenter.edit(productModel, id);
                                    }
                                } else {
                                    showToast("Không upload được ảnh");
                                }
                            }
                        });
                        return;
                    }
                    ProductPresenter productPresenter = new ProductPresenter(EditOrAddActivity.this);
                    if (Objects.equals(id, "")) {
                        productPresenter.store(productModel);
                    } else {
                        productModel.setHinhanh(oldImage);
                        productPresenter.edit(productModel, id);
                    }
                } catch (Exception e) {
                    isAdding = false;
                    showToast("Có lỗi xảy ra");
                }

            }
        });
        btnFresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

    }

    public boolean isAdd() {
        return Objects.equals(id, "");
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSuccess() {
        showToast(isAdd() ? "Thêm sản phẩm thành công" : "Sửa sản phẩm thành công");
        // back ve list sau khi them hoac sua xong
        backToList();
    }

    public void backToList() {
        Intent intent = new Intent(EditOrAddActivity.this, ListProductActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void OnFail() {
        showToast(isAdd() ? "Thêm sản phẩm thất bại" : "Sửa sản phẩm thất bại");
        isAdding = false;
    }

    @Override
    public void OnComplete() {

    }

    @Override
    public void OnGetAllSuccess(Task<QuerySnapshot> task) {
        List<String> productCategories = new ArrayList<>();
        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
            productCategories.add(documentSnapshot.toObject(ProductCategory.class).getTenloai());
        }
        initSpinner(productCategories);
    }

    @Override
    public void OnGetOnceSuccess(Task<DocumentSnapshot> task) {
        if (task.isSuccessful()) {
            ProductModel productModel = task.getResult().toObject(ProductModel.class);
            initSpinner(productModel);
            if (productModel.getHinhanh() != null) {
                Picasso.get().load(productModel.getHinhanh()).fit().centerCrop().into(imgPreview);
            }
            initProduct(productModel);
        }
    }

    public void initProduct(ProductModel productModel) {
        edtName.setText(productModel.getTensp());
        edtDesc.setText(productModel.getMota());
        edtType.setText(String.valueOf(productModel.getType()));
        edtWeight.setText(String.valueOf(productModel.getTrongluong()));
        edtQuantity.setText(String.valueOf(productModel.getSoluong()));
        edtHanSuDung.setText(String.valueOf(productModel.getHansudung()));
        edtPrice.setText(String.valueOf(productModel.getGiatien()));
        oldImage = productModel.getHinhanh();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, i);
        calendar.set(Calendar.MONTH, i2);
        calendar.set(Calendar.DAY_OF_MONTH, i2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        dateConverted = simpleDateFormat.format(calendar.getTime());
    }
}