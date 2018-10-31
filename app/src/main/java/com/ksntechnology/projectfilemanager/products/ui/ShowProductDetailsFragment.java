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
import android.widget.Toast;

import com.ksntechnology.projectfilemanager.R;
import com.ksntechnology.projectfilemanager.products.adapter.DetailsAdapter;
import com.ksntechnology.projectfilemanager.products.db.entity.DetailsEntity;

import java.util.List;

public class ShowProductDetailsFragment extends Fragment implements IMainProducts.IShowDetailView {
    private RecyclerView rcv;
    private ShowDetailsPresenter mDetailPresenter;
    private DetailsAdapter mAdapter;

    public static ShowProductDetailsFragment getInstance() {
        ShowProductDetailsFragment fm = new ShowProductDetailsFragment();
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
                R.layout.fragment_show_product_details,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void toastMessage(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    private void initInstance(View view) {
        rcv = view.findViewById(R.id.recyclerView_showProductDetails);
        mDetailPresenter = new ShowDetailsPresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mDetailPresenter.loadDetailsTable(getContext());
    }

    @Override
    public void setDetailsOfProductResultToView(List<DetailsEntity> details,
                                        boolean isSuccess) {
        if (!isSuccess) {
            toastMessage("No date on the details table ");
            return;
        }

        mAdapter = new DetailsAdapter(getContext(), details);
        rcv.setAdapter(mAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
