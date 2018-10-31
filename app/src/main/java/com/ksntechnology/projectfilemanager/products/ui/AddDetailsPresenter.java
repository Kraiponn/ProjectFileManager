package com.ksntechnology.projectfilemanager.products.ui;

import android.content.Context;
import android.os.AsyncTask;

import com.ksntechnology.projectfilemanager.products.db.database.InventoryDB;
import com.ksntechnology.projectfilemanager.products.db.entity.DetailsEntity;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddDetailsPresenter implements IMainProducts.IDetailPresenter {
    private static IMainProducts.IDetailView detailView;

    public AddDetailsPresenter(IMainProducts.IDetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void loadProductsTable(Context context, String requestMode) {
        new InsertDetails(context, requestMode).execute();
    }

    @Override
    public void addDetailOfProduct(Context context, DetailsEntity details, String requestMode) {
        new InsertDetails(context, requestMode, details).execute();
    }


    /****************************
     *  Static Nest Class
     */
    public static class InsertDetails extends AsyncTask<Void, Void, Void> {
        private Context context;
        private String request_mode;
        private DetailsEntity mDetails;
        private List<ProductsEntity> mProducts;
        private static final String MODE_LOAD_PRODUCTS = "LOAD_PRODUCTS";
        private static final String MODE_ADDED_DETAILS = "INSERT_DETAILS";

        public InsertDetails(Context context, String request_mode) {
            this.context = context;
            this.request_mode = request_mode;
        }

        public InsertDetails(Context context,
                             String request_mode, DetailsEntity details) {
            this.context = context;
            this.request_mode = request_mode;
            mDetails = details;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            if (request_mode.equals(MODE_LOAD_PRODUCTS)) {
                mProducts = InventoryDB
                        .newInstance(context)
                        .inventoryDao()
                        .getProducts();
            } else if (request_mode.equals(MODE_ADDED_DETAILS)) {
                InventoryDB
                        .newInstance(context)
                        .inventoryDao()
                        .insertDetails(mDetails);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (request_mode.equals(MODE_LOAD_PRODUCTS)) {
                if (!mProducts.isEmpty()) {
                    detailView.setProductsTableForSpinner(mProducts, true);
                } else {
                    detailView.setProductsTableForSpinner(mProducts, false);
                }
            } else if (request_mode.equals(MODE_ADDED_DETAILS)) {
                detailView.showResultAddDetailOfProduct(true);
            }
        }

    }

}
