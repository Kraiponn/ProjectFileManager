package com.ksntechnology.projectfilemanager.products.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ksntechnology.projectfilemanager.R;
import com.ksntechnology.projectfilemanager.products.adapter.ProductsAdapter;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

import java.util.List;

public class ShowProductsFragment extends Fragment implements IMainProducts.IShowProductView{
    private RecyclerView rcv;
    private TextView txtTitle;
    private List<ProductsEntity> mProducts;
    private ProductsAdapter mAdapter;
    private ShowProductsPresenter mShowProductPresenter;

    public static ShowProductsFragment getInstance() {
        ShowProductsFragment fm = new ShowProductsFragment();
        Bundle args = new Bundle();
        fm.setArguments(args);
        return fm;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_show_products,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void initInstance(View view) {
        txtTitle = view.findViewById(R.id.txtShowProductsTitle);
        rcv = view.findViewById(R.id.recyclerView_showProducts);
        mShowProductPresenter = new ShowProductsPresenter(this);
        mShowProductPresenter.loadProductsTable(getContext());
    }

    private void toastMessage(String text) {
        Toast.makeText(getContext(),
                text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProductsResultToView(List<ProductsEntity> products,
                                        boolean isSuccess) {
        if (isSuccess) {
            showDataOnRecyclerView(products);
        } else {
            toastMessage("Data invalid");
        }
    }

    private void showDataOnRecyclerView(List<ProductsEntity> products) {
        mAdapter = new ProductsAdapter(getContext(), products);
        rcv.setAdapter(mAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(
                getContext(),
                RecyclerView.VERTICAL, false
        ));


        String text = "";
        for (int i = 0; i < products.size(); i++) {
            text += "ID: " + products.get(i).getProductId() + "\n";
            text += "Name: " + products.get(i).getProductName() + "\n";
            text += "Model: " + products.get(i).getProductModel() + "\n\n";
        }

        //txtTitle.setText(text);
        //toastMessage("load data successfully \n" + text);
    }
}
