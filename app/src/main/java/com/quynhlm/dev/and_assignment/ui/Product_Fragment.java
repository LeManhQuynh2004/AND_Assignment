package com.quynhlm.dev.and_assignment.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quynhlm.dev.and_assignment.Adapter.Product_Adapter;
import com.quynhlm.dev.and_assignment.Dao.ProductDao;
import com.quynhlm.dev.and_assignment.Model.Product;
import com.quynhlm.dev.and_assignment.R;

import java.util.ArrayList;


public class Product_Fragment extends Fragment {

    View view;

    RecyclerView recyclerView;

    EditText edtname, edtprice, edtquantity;

    ProductDao productDao;

    ArrayList<Product> list = new ArrayList<>();

    Product_Adapter product_adapter;
    TextView txt_price, txt_name, txt_Normal;

    boolean ascending;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView_Product);
        productDao = new ProductDao(getContext());
        list = productDao.selectAll();
        txt_price = view.findViewById(R.id.txt_Order_by_Price);
        ascending = true;
        txt_price.setOnClickListener(view1 -> {
            Order_By_Price();
        });
        txt_name = view.findViewById(R.id.txt_Order_by_title);
        txt_name.setOnClickListener(v -> {
            Order_By_Name();
        });
        txt_Normal = view.findViewById(R.id.txt_Normal);
        txt_Normal.setOnClickListener(v -> {
            Order_By_Normal();
        });
        product_adapter = new Product_Adapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(product_adapter);
        view.findViewById(R.id.fab_add_product).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.item_add_product, null);
            builder.setView(view1);
            AlertDialog alertDialog = builder.create();
            edtname = view1.findViewById(R.id.edt_product_add_name);
            edtprice = view1.findViewById(R.id.edt_product_add_price);
            edtquantity = view1.findViewById(R.id.edt_product_add_quantity);
            view1.findViewById(R.id.btn_add_product).setOnClickListener(view2 -> {
                String name = edtname.getText().toString().trim();
                int price = Integer.parseInt(edtprice.getText().toString().trim());
                int quantity = Integer.parseInt(edtquantity.getText().toString().trim());
                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                if (productDao.insertData(product)) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list.add(product);
                    Log.e("id", "onCreateView: " + product.getProduct_id());
                    product_adapter.notifyDataSetChanged();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            });

            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();
        });
        return view;
    }

    public void Order_By_Price() {
        if (ascending) {
            txt_price.setText("Giá ↑");
            list.clear();
            list.addAll(productDao.select_price_ASC());
            product_adapter.notifyDataSetChanged();
        } else {
            txt_price.setText("Giá ↓");
            list.clear();
            list.addAll(productDao.select_Price_DESC());
            product_adapter.notifyDataSetChanged();
        }
        txt_name.setText("Tên");
        ascending = !ascending;
    }

    public void Order_By_Name() {
        if (ascending) {
            txt_name.setText("Tên ↑");
            list.clear();
            list.addAll(productDao.select_name_ASC());
            product_adapter.notifyDataSetChanged();
        } else {
            txt_name.setText("Tên ↓");
            list.clear();
            list.addAll(productDao.select_name_DESC());
            product_adapter.notifyDataSetChanged();
        }
        txt_price.setText("Giá");
        ascending = !ascending;
    }

    public void Order_By_Normal() {
        list.clear();
        list.addAll(productDao.selectAll());
        product_adapter.notifyDataSetChanged();
        txt_price.setText("Giá");
        txt_name.setText("Tên");
    }
}