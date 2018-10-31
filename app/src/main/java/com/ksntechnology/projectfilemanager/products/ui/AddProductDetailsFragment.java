package com.ksntechnology.projectfilemanager.products.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ksntechnology.projectfilemanager.R;
import com.ksntechnology.projectfilemanager.manager.Contextor;
import com.ksntechnology.projectfilemanager.products.db.database.InventoryDB;
import com.ksntechnology.projectfilemanager.products.db.entity.DetailsEntity;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddProductDetailsFragment extends Fragment implements IMainProducts.IDetailView {
    private Spinner spnProducts;
    private TextView txtSelectPicture;
    private EditText edtPrice;
    private ImageView imgProduct;
    private Button btnAddDetail;

    private  final String MODE_LOAD_PRODUCTS = "LOAD_PRODUCTS";
    private  final String MODE_ADDED_DETAILS = "INSERT_DETAILS";
    private final int REQUEST_PICTIRE = 99;

    private ArrayList mProductId;
    private AddDetailsPresenter presenter;
    private Bitmap imgBitmap;
    private String mImgFilePath;
    private String mImgFileName;

    public static AddProductDetailsFragment getInstance() {
        AddProductDetailsFragment fm = new AddProductDetailsFragment();
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
                R.layout.fragment_add_product_details,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void toastMessage(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    private void initInstance(View view) {
        imgProduct = view.findViewById(R.id.imgDetailOfProduct);
        edtPrice = view.findViewById(R.id.edtDetail_price);
        imgProduct = view.findViewById(R.id.imgDetailOfProduct);
        spnProducts = view.findViewById(R.id.spinnerDetail_productId);
        btnAddDetail = view.findViewById(R.id.btnDetail_add);
        txtSelectPicture = view.findViewById(R.id.txtDetail_selectPicture);

        btnAddDetail.setOnClickListener(btnAddDetailClicked);
        txtSelectPicture.setOnClickListener(txtSelectPictureClicked);

    }


    @Override
    public void onStart() {
        super.onStart();
        loadProductIdToSpinner();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        InventoryDB.newInstance(getContext()).onDestroyInstance();
    }

    private void loadProductIdToSpinner() {
        presenter = new AddDetailsPresenter(this);
        presenter.loadProductsTable(getContext(), MODE_LOAD_PRODUCTS);
    }

    @Override
    public void setProductsTableForSpinner(List<ProductsEntity> products, boolean isSuccess) {
        mProductId = new ArrayList();
        ArrayList<String> productName = new ArrayList<>();
        mProductId.clear();

        if (isSuccess) {
            for (int i = 0; i < products.size(); i++) {
                mProductId.add(products.get(i).getProductId());
                productName.add(products.get(i).getProductName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    Contextor.getInstance().getContext(),
                    R.layout.custom_spinner_item, productName
            );
            spnProducts.setAdapter(adapter);
            spnProducts.setSelection(0);
        } else {
            toastMessage("Load product fail");
        }
    }

    private void AddProductDetail() {
        //savedPictureToInternalStorage();
        String pId = String.valueOf(mProductId.get(spnProducts.getSelectedItemPosition()));
        String pPrice = edtPrice.getText().toString().trim();
        mImgFileName = getImageFileName();
        mImgFilePath = getImageFilePath(mImgFileName);

        if (isAllViewInvalid(pId, pPrice, imgProduct)) {
            toastMessage("Have some view invalid");
            return;
        }

        DetailsEntity details = new DetailsEntity();
        details.setProductId(Integer.valueOf(pId));
        details.setPrice(Double.valueOf(pPrice));
        details.setImagePath(mImgFilePath);
        presenter.addDetailOfProduct(getContext(), details, MODE_ADDED_DETAILS);
    }

    private String getImageFileName() {
        Date date = new Date();
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = dateFormat.format(date) + ".png";

        return fileName;
    }

    private String getImageFilePath(String fileName) {
        File imgInt = getContext().getFilesDir();
        String imgPath = imgInt.getAbsolutePath() + "/" + fileName;

        return imgPath;
    }

    private boolean isAllViewInvalid(String pId, String pPrice, ImageView imgProduct) {
        return pId.isEmpty() || pPrice.isEmpty() || imgProduct.getDrawable() == null;
    }

    private void clearAllView() {
        edtPrice.setText("");
        imgProduct.setImageResource(0);
        mImgFileName = "";
        mImgFilePath = "";
        imgBitmap = null;
    }

    private void savedPictureToInternalStorage() {
        try {
            FileOutputStream stream = getContext().openFileOutput(
                    mImgFileName, Context.MODE_PRIVATE
            );
            imgBitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
            stream.flush();
            stream.close();

            clearAllView();
            toastMessage("Add image successfully");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showResultAddDetailOfProduct(boolean isSuccess) {
        if (isSuccess) {
            savedPictureToInternalStorage();
        } else {
            //
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICTIRE && resultCode == getActivity().RESULT_OK) {
            Uri imgUri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity()
                    .getContentResolver()
                    .query(
                            imgUri,
                            projection,
                            null, null, null
                    );

            if (cursor != null) {
                cursor.moveToNext();
                int imgColumn = cursor.getColumnIndex(projection[0]);
                String imgPath = cursor.getString(imgColumn);
                imgBitmap = BitmapFactory.decodeFile(imgPath);
                imgProduct.setImageBitmap(imgBitmap);
            }
            cursor.close();
        }
    }

    /***********************************
     *  Listener Zone
     */
    View.OnClickListener btnAddDetailClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddProductDetail();
        }
    };

    View.OnClickListener txtSelectPictureClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent imgItn = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imgItn, REQUEST_PICTIRE);

            /*Intent imgItn = new Intent();
            imgItn.setType("image/*");
            imgItn.setAction(Intent.ACTION_PICK);
            startActivityForResult(
                    Intent.createChooser(imgItn, "Select picture"),
                    REQUEST_PICTIRE
            );*/
        }
    };


}
