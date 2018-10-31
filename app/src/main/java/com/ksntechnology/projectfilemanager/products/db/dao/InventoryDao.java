package com.ksntechnology.projectfilemanager.products.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ksntechnology.projectfilemanager.products.db.entity.DetailsEntity;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductWithDetails;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

import java.util.List;

@Dao
public interface InventoryDao {

    @Insert
    void insertProducts(ProductsEntity productsEntity);

    @Insert
    void insertDetails(DetailsEntity detailsEntity);

    @Query("select * from products")
    List<ProductsEntity> getProducts();

    @Query("select * from details")
    List<DetailsEntity> getDetails();

    @Query("SELECT products.product_id AS productId, " +
            "products.product_name AS productName, " +
            "products.product_model AS productMode, " +
            "details.detail_id AS detailId, " +
            "details.detail_price AS price, " +
            "details.detail_image_path AS imagePath " +
            "FROM products,details " +
            "WHERE products.product_id = details.product_id")
    List<ProductWithDetails> getProductWithDetails();

    @Query("UPDATE details " +
            "SET product_id = :productId, " +
            "detail_price = :price, " +
            "detail_image_path = :imagePath " +
            "WHERE detail_id = :detailId")
    void updateDetails(int productId, int detailId,
                       double price, String imagePath);

    @Query("DELETE FROM details WHERE detail_id = :detailId")
    void deleteDetailsByDetailId(int detailId);

    @Query("DELETE FROM details")
    void deleteAllDetails();


}

