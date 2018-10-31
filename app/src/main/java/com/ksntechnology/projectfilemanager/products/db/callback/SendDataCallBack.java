package com.ksntechnology.projectfilemanager.products.db.callback;

import com.ksntechnology.projectfilemanager.products.db.entity.DetailsEntity;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductWithDetails;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

import java.util.List;

public interface SendDataCallBack {
    void loadProductsCallBack(List<ProductsEntity> productsEntities, boolean isSuccess);

    void loadDetailsCallback(List<DetailsEntity> proDetails, boolean isSuccess);

    void loadProductWithDetailsCallback(List<ProductWithDetails> proDetails, boolean isSuccess);
}
