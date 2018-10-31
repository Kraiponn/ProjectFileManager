package com.ksntechnology.projectfilemanager.products.ui;

import android.content.Context;
import android.os.AsyncTask;

import com.ksntechnology.projectfilemanager.products.db.database.InventoryDB;
import com.ksntechnology.projectfilemanager.products.db.entity.DetailsEntity;

import java.util.List;

public class ShowDetailsPresenter implements IMainProducts.IShowDetailPresenter {
    private static IMainProducts.IShowDetailView detailView;

    public ShowDetailsPresenter(IMainProducts.IShowDetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void loadDetailsTable(Context context) {
        new QueryDetailOfProduct(context).execute();
    }


    public static class QueryDetailOfProduct extends AsyncTask<Void, Void, Void> {
        Context context;
        List<DetailsEntity> mItems;

        public QueryDetailOfProduct(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mItems = InventoryDB
                                .newInstance(context)
                                .inventoryDao()
                                .getDetails();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!mItems.isEmpty()) {
                detailView.setDetailsOfProductResultToView(mItems, true);
            } else {
                detailView.setDetailsOfProductResultToView(mItems, false);
            }
        }
    }

}
