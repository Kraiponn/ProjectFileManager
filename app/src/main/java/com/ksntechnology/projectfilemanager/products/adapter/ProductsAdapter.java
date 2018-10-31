package com.ksntechnology.projectfilemanager.products.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ksntechnology.projectfilemanager.R;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{
    private Context mContext;
    private List<ProductsEntity> mItem;

    public ProductsAdapter(Context mContext, List<ProductsEntity> mItem) {
        this.mContext = mContext;
        this.mItem = mItem;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(
                R.layout.cutom_show_product_item,
                viewGroup, false);

        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder vh, int i) {
        ProductsEntity item = mItem.get(i);
        vh.txtProId.setText(item.getProductId()+"");
        vh.txtProName.setText(item.getProductName());
        vh.txtProModel.setText(item.getProductModel());

        /*vh.txtProId.setText("99999");
        vh.txtProName.setText("Senior Developer");
        vh.txtProModel.setText("Technology");*/
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }



    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProId;
        public TextView txtProName;
        public TextView txtProModel;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProId = itemView.findViewById(R.id.txtProductId);
            txtProName = itemView.findViewById(R.id.txtProductName);
            txtProModel = itemView.findViewById(R.id.txtProductModel);
        }
    }
}
