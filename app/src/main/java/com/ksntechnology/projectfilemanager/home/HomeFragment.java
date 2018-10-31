package com.ksntechnology.projectfilemanager.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ksntechnology.projectfilemanager.R;
import com.ksntechnology.projectfilemanager.common.MainActivity;
import com.ksntechnology.projectfilemanager.products.ui.AddProductDetailsFragment;
import com.ksntechnology.projectfilemanager.products.ui.AddProductsFragment;
import com.ksntechnology.projectfilemanager.products.ui.ShowProductDetailsFragment;
import com.ksntechnology.projectfilemanager.products.ui.ShowProductsFragment;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button btnAddProduct;
    private Button btnAddProDetails;
    private Button btnShowProDetails;
    private Button btnShowProducts;

    public HomeFragment() {
        //
    }

    public static HomeFragment getInstance() {
        HomeFragment fm = new HomeFragment();
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
                R.layout.fragment_home,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void initInstance(View view) {
        btnAddProduct = view.findViewById(R.id.btnAddProduct);
        btnAddProDetails = view.findViewById(R.id.btnAddProductDetails);
        btnShowProDetails = view.findViewById(R.id.btnShowProductDetails);
        btnShowProducts = view.findViewById(R.id.btnShowProduct);

        btnAddProduct.setOnClickListener(this);
        btnAddProDetails.setOnClickListener(this);
        btnShowProDetails.setOnClickListener(this);
        btnShowProducts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddProduct:
                replaceFragment(AddProductsFragment.getInstance());
                break;
            case R.id.btnAddProductDetails:
                replaceFragment(AddProductDetailsFragment.getInstance());
                break;
            case R.id.btnShowProduct:
                replaceFragment(ShowProductsFragment.getInstance());
                break;
            case R.id.btnShowProductDetails:
                replaceFragment(ShowProductDetailsFragment.getInstance());
                break;
        }
    }

    private void replaceFragment(Fragment fm) {
        MainActivity.fragmentManager.beginTransaction()
                .replace(
                        R.id.containerMain,
                        fm)
                .addToBackStack(null)
                .commit();
    }
}
