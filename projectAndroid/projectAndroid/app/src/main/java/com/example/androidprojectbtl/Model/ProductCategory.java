package com.example.androidprojectbtl.Model;

public class ProductCategory {
    private String image;
    private String tenloai;

    public ProductCategory() {
    }

    public ProductCategory(String image, String tenloai) {
        this.image = image;
        this.tenloai = tenloai;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
