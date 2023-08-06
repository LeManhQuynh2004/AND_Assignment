package com.quynhlm.dev.and_assignment.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.quynhlm.dev.and_assignment.Dao.ProductDao;
import com.quynhlm.dev.and_assignment.Model.Product;
import com.quynhlm.dev.and_assignment.R;

import java.util.ArrayList;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.ProductViewHolder> {
    Context context;
    ArrayList<Product> list;

    ProductDao productDao;

    EditText edtname, edtprice, edtquantity;


    public Product_Adapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
        productDao = new ProductDao(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.txt_name.setText(list.get(position).getName());
        holder.txt_quantity.setText(list.get(position).getQuantity() + "");
        holder.txt_price.setText(list.get(position).getPrice() + "");
        holder.txt_delete.setOnClickListener(view -> {
            DeleteItem(position);
        });
        holder.txt_update.setOnClickListener(view -> {
            UpdateItem(position);
        });
    }

    private void UpdateItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view1 = LayoutInflater.from(context).inflate(R.layout.item_add_product, null);
        builder.setView(view1);
        AlertDialog alertDialog = builder.create();
        edtname = view1.findViewById(R.id.edt_product_add_name);
        edtprice = view1.findViewById(R.id.edt_product_add_price);
        edtquantity = view1.findViewById(R.id.edt_product_add_quantity);

        edtname.setText(list.get(position).getName());
        edtprice.setText(list.get(position).getPrice() + "");
        edtquantity.setText(list.get(position).getQuantity() + "");

        view1.findViewById(R.id.btn_add_product).setOnClickListener(view2 -> {
            String name = edtname.getText().toString().trim();
            int price = Integer.parseInt(edtprice.getText().toString().trim());
            int quantity = Integer.parseInt(edtquantity.getText().toString().trim());
            Product product = list.get(position);
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            if (productDao.updateData(product)) {
                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                list.set(position, product);
                notifyDataSetChanged();
                alertDialog.dismiss();
            } else {
                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    public void DeleteItem(int position) {
        Product product = list.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("thong bao");
        builder.setMessage("Ban co chac chan muon xoa san pham " + product.getName()+" khong ?");
        builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (productDao.deleteData(product)) {
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    list.remove(product);
                    notifyItemRemoved(position);
                    notifyItemChanged(position);
                } else {
                    Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("huy", null);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name, txt_price, txt_quantity, txt_update, txt_delete;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name_add_product);
            txt_price = itemView.findViewById(R.id.txt_price_add_product);
            txt_quantity = itemView.findViewById(R.id.txt_quantity_add_product);
            txt_update = itemView.findViewById(R.id.txt_Update);
            txt_delete = itemView.findViewById(R.id.txt_delete);
        }
    }
}
