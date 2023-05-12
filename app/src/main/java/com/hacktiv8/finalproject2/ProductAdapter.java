package com.hacktiv8.finalproject2;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<UploadProduct> uploadProducts;
    private Context context;

    public ProductAdapter(List<UploadProduct> uploadProducts, Context context) {
        this.uploadProducts = uploadProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        UploadProduct uploadProduct = uploadProducts.get(position);

        holder.nameProduct.setText(uploadProduct.getmNameProduct());
        holder.description.setText(uploadProduct.getmDescriptionProduct());
        holder.price.setText(uploadProduct.getmPriceProduct());
        holder.brand.setText(uploadProduct.getmBrandProduct());
        holder.quantity.setText(uploadProduct.getmQuantity());
        Log.d("Nama", uploadProduct.getmNameProduct());
        Log.d("Deskripsi", uploadProduct.getmDescriptionProduct());
        Log.d("Harga", uploadProduct.getmPriceProduct());
        Log.d("Brand", uploadProduct.getmBrandProduct());
        if (uploadProduct.getmQuantity() != null) {
            Log.d("Quantity", uploadProduct.getmQuantity());
        }

        Glide.with(context).load(uploadProduct.getmImageUrl()).into(holder.imageProduct);
        Log.d("Image", uploadProduct.getmImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProduct.class);
                intent.putExtra("name_product", uploadProduct.getmNameProduct());
                intent.putExtra("description", uploadProduct.getmDescriptionProduct());
                intent.putExtra("price", uploadProduct.getmPriceProduct());
                intent.putExtra("brand", uploadProduct.getmBrandProduct());
                intent.putExtra("quantity", uploadProduct.getmQuantity());
                intent.putExtra("image_url", uploadProduct.getmImageUrl());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uploadProducts.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView nameProduct, description, price, brand, quantity;
        private ImageView imageProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            nameProduct = itemView.findViewById(R.id.txtNameProduct);
            description = itemView.findViewById(R.id.textDetailDescription);
            price = itemView.findViewById(R.id.txtPrice);
            brand = itemView.findViewById(R.id.txtBrand);
            quantity = itemView.findViewById(R.id.txtStock);
            imageProduct = itemView.findViewById(R.id.imageItem);
        }
    }
}


