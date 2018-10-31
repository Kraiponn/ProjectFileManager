package com.ksntechnology.projectfilemanager.products.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ksntechnology.projectfilemanager.R;
import com.ksntechnology.projectfilemanager.products.db.callback.SendStatusCallBack;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

public class AddProductsFragment extends Fragment
        implements View.OnClickListener, IMainProducts.IProductView {

    private EditText edtProName, edtProModel;
    private Button btnAdd;
    private ProductPresenter mPresenter;


    public static AddProductsFragment getInstance() {
        AddProductsFragment fm = new AddProductsFragment();
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
                R.layout.fragment_add_product,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void initInstance(View view) {
        edtProName = view.findViewById(R.id.edtProductName);
        edtProModel = view.findViewById(R.id.edtProductModel);
        btnAdd = view.findViewById(R.id.btnAddProduct);

        btnAdd.setOnClickListener(this);
        mPresenter = new ProductPresenter(this);
    }

    private void toastMessage(String text) {
        Toast.makeText(getContext(),
                text,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddProduct:
                insertData();
        }
    }

    private void insertData() {
        String proName = edtProName.getText().toString().trim();
        String proModel = edtProModel.getText().toString().trim();

        if (isProductsInvalid(proName, proModel)) {
            toastMessage("Invalid data");
        } else {
            insertProduct(proName, proModel);
        }

    }

    private void clearView() {
        edtProName.setText("");
        edtProModel.setText("");
    }

    private void insertProduct(String proName, String proModel) {
        ProductsEntity item = new ProductsEntity();
        item.setProductName(proName);
        item.setProductModel(proModel);
        mPresenter.addProduct(getContext(), item);
    }

    private boolean isProductsInvalid(String proName, String proModel) {
        return proName.isEmpty() || proModel.isEmpty();
    }


    @Override
    public void showResultAddProduct(boolean isSuccess) {
        if (isSuccess) {
            toastMessage("Added new product successfully");
            clearView();
        } else {
            toastMessage("Invalid added new product");
        }
    }


}
