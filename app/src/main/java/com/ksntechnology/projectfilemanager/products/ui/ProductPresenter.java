package com.ksntechnology.projectfilemanager.products.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.ksntechnology.projectfilemanager.products.db.database.InventoryDB;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

public class ProductPresenter implements IMainProducts.IProductsPresenter {
    private static IMainProducts.IProductView iProductsView;

    public ProductPresenter(IMainProducts.IProductView iProductsView) {
        this.iProductsView = iProductsView;
    }

    @Override
    public void addProduct(Context context,
                           ProductsEntity product) {
        new ProductPresenter.InertProduct(context, product).execute();
    }

    /*************************
     * Static Nest Class
     */
    public static class InertProduct extends AsyncTask<Void, Void, Void> {
        Context context;
        ProductsEntity productsEntity;

        public InertProduct(Context context,
                              ProductsEntity productsEntity) {
            this.context = context;
            this.productsEntity = productsEntity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            InventoryDB.newInstance(context)
                    .inventoryDao()
                    .insertProducts(productsEntity);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            iProductsView.showResultAddProduct(true);
        }
    }

}
