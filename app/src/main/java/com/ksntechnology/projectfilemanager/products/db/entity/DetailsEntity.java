package com.ksntechnology.projectfilemanager.products.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "details")
public class DetailsEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "detail_id")
    private int detailId;

    @ColumnInfo(name = "product_id")
    private int productId;

    @ColumnInfo(name = "detail_price")
    double price;

    @ColumnInfo(name = "detail_image_path")
    String imagePath;


    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
