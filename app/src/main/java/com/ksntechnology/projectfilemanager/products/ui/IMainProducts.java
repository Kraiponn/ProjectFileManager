package com.ksntechnology.projectfilemanager.products.ui;

import android.content.Context;

import com.ksntechnology.projectfilemanager.products.db.entity.DetailsEntity;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

import java.util.List;

public interface IMainProducts {

    /*****************************
     * INSERT NEW ITEM ON DETAILS TABLE
     */
    interface IDetailView{
        void setProductsTableForSpinner(List<ProductsEntity> products, boolean isSuccess);

        void showResultAddDetailOfProduct(boolean isSuccess);
    }

    interface IDetailPresenter {
        void loadProductsTable(Context context, String requestMode);

        void addDetailOfProduct(Context context,
                                DetailsEntity details,
                                String requestMode);

    }

    /*****************************
     * SHOW RESULT ON DETAILS TABLE
     */
    interface IShowDetailView {
        void setDetailsOfProductResultToView(List<DetailsEntity> details, boolean isSuccess);
    }

    interface IShowDetailPresenter {
        void loadDetailsTable(Context context);
    }





    /*****************************
     * INSERT NEW ITEM ON PRODUCTS TABLE
     */
    interface IProductView {
        void showResultAddProduct(boolean isSuccess);
    }

    interface IProductsPresenter {
        void addProduct(Context context,
                        ProductsEntity product);
    }

    /*****************************
     * SHOW RESULT ON PRODUCTS TABLE
     */
    interface IShowProductView {
        void setProductsResultToView(List<ProductsEntity> products, boolean isSuccess);
    }

    interface IShowProductPresenter {
        void loadProductsTable(Context context);
    }

}
