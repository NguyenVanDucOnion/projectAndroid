package com.example.androidprojectbtl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidprojectbtl.Model.ProductModel;
import com.example.androidprojectbtl.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductModel> productModels;

    private ProductItemHandler productItemHandler;

    public ProductAdapter(ProductItemHandler productItemHandler) {
        this.productItemHandler = productItemHandler;
        productModels = new ArrayList<>();
    }

    public void setData(List<ProductModel> productModels) {
        this.productModels = productModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.onBind(productModels.get(position), productItemHandler);
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtPrice;
        TextView txtWeight;
        TextView txtQuan;
        ImageView imvThumbnail;

        ImageButton btnEdit;
        ImageButton btnDelete;

        public void onBind(ProductModel productModel, ProductItemHandler productItemHandler) {
            txtName.setText(productModel.getTensp());
            txtPrice.setText(String.valueOf(productModel.getGiatien()));
            txtWeight.setText(String.valueOf(productModel.getTrongluong()));
            txtQuan.setText(String.valueOf(productModel.getSoluong()));
            Picasso.get().load(productModel.getHinhanh()).fit().centerCrop().into(imvThumbnail);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productItemHandler.onDelete(productModel.getId());
                }
            });
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productItemHandler.onEdit(productModel.getId());
                }
            });
        }

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txttensp);
            txtPrice = itemView.findViewById(R.id.txtgiatien);
            txtQuan = itemView.findViewById(R.id.txtsoluong);
            txtWeight = itemView.findViewById(R.id.txttrongluong);
            imvThumbnail = itemView.findViewById(R.id.hinhanh);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public interface ProductItemHandler {
        void onEdit(String id);

        void onDelete(String id);
    }
}
