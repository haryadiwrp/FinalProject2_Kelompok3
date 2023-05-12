package com.hacktiv8.finalproject2;

public class UploadProduct {

    private String mIdProduct, mNameProduct, mPriceProduct, mBrandProduct, mDescriptionProduct, mQuantity;
    private String mImageUrl;


    public UploadProduct() {
        //constructor
    }

    public UploadProduct (String idProduct, String nameProduct, String priceProduct, String brandProduct, String descriptionProduct, String quantity, String imageUrl) {
        this.mIdProduct = idProduct;
        this.mNameProduct = nameProduct;
        this.mPriceProduct = priceProduct;
        this.mBrandProduct = brandProduct;
        this.mDescriptionProduct = descriptionProduct;
        this.mQuantity = quantity;
        this.mImageUrl = imageUrl;
    }

    public String getmIdProduct() {
        return mIdProduct;
    }

    public void setmIdProduct(String mIdProduct) {
        this.mIdProduct = mIdProduct;
    }

    public String getmNameProduct() {
        return mNameProduct;
    }

    public void setmNameProduct(String mNameProduct) {
        this.mNameProduct = mNameProduct;
    }

    public String getmPriceProduct() {
        return mPriceProduct;
    }

    public void setmPriceProduct(String mPriceProduct) {
        this.mPriceProduct = mPriceProduct;
    }

    public String getmBrandProduct() {
        return mBrandProduct;
    }

    public void setmBrandProduct(String mBrandProduct) {
        this.mBrandProduct = mBrandProduct;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mProductType) {
        this.mQuantity = mProductType;
    }

    public String getmDescriptionProduct() {
        return mDescriptionProduct;
    }

    public void setmDescriptionProduct(String mDescriptionProduct) {
        this.mDescriptionProduct = mDescriptionProduct;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

}
