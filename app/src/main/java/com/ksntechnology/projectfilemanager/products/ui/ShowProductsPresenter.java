package com.ksntechnology.projectfilemanager.products.ui;

import android.content.Context;
import android.os.AsyncTask;

import com.ksntechnology.projectfilemanager.products.db.database.InventoryDB;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

import java.util.List;

public class ShowProductsPresenter implements IMainProducts.IShowProductPresenter {
    private static IMainProducts.IShowProductView showProductView;

    public ShowProductsPresenter(IMainProducts.IShowProductView showProductView) {
        this.showProductView = showProductView;
    }

    @Override
    public void loadProductsTable(Context context) {
        new ShowProductsPresenter.QueryProductsTable(context).execute();
    }

    /**********************
     * Static Nest Class
     */
    public static class QueryProductsTable extends AsyncTask<Void, Void, Void> {
        Context mContext;
        List<ProductsEntity> mProducts;

        public QueryProductsTable(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mProducts = InventoryDB.newInstance(mContext)
                                    .inventoryDao().getProducts();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!mProducts.isEmpty()) {
                showProductView.setProductsResultToView(mProducts, true);
            } else {
                showProductView.setProductsResultToView(mProducts, false);
            }
        }
    }

}
