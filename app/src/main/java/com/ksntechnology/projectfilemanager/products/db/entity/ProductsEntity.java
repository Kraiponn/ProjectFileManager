package com.ksntechnology.projectfilemanager.products.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductsEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    private int productId;

    @ColumnInfo(name = "product_name")
    String productName;

    @ColumnInfo(name = "product_model")
    String productModel;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }
}
