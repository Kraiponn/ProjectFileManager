package com.ksntechnology.projectfilemanager.products.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ksntechnology.projectfilemanager.R;
import com.ksntechnology.projectfilemanager.products.db.entity.DetailsEntity;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>{
    private Context mContext;
    private List<DetailsEntity> mItem;

    public DetailsAdapter(Context mContext, List<DetailsEntity> mItem) {
        this.mContext = mContext;
        this.mItem = mItem;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(
                R.layout.cutom_show_product_details_item,
                viewGroup, false);

        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder vh, int i) {
        DetailsEntity item = mItem.get(i);
        vh.txtProId.setText("ProductID: " + item.getProductId()+"");
        vh.txtDetailId.setText(item.getDetailId()+"");
        vh.txtPrice.setText("Price: " + item.getPrice());

        Glide.with(mContext)
                .load(item.getImagePath())
                .into(vh.imgShowDetail);
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }



    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProId;
        public TextView txtDetailId;
        public TextView txtPrice;
        public ImageView imgShowDetail;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProId = itemView.findViewById(R.id.txtShowDetail_ProductId);
            txtDetailId = itemView.findViewById(R.id.txtShowDetail_detailId);
            txtPrice = itemView.findViewById(R.id.txtShowDetail_price);
            imgShowDetail = itemView.findViewById(R.id.imgShowDetail);
        }
    }
}
