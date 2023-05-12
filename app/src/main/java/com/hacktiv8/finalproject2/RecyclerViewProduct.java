package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewProduct extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private List<UploadProduct> mUploads;
    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_product);

        mRecyclerView = findViewById(R.id.recyclerProduct);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();
        mAdapter = new ProductAdapter(mUploads, this);
        mRecyclerView.setAdapter(mAdapter);

        String category = getIntent().getStringExtra("category");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(category);

        // Add a listener to update the RecyclerView with the new data
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadProduct uploadProduct = postSnapshot.getValue(UploadProduct.class);
                    uploadProduct.setmIdProduct(postSnapshot.getKey());
                    mUploads.add(uploadProduct);
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RecyclerViewProduct.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        Button btnUpload = findViewById(R.id.btnUpload);
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), UploadProductActivity.class));
//                finish();
//            }
//        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mDatabaseRef.addValueEventListener(mListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mDatabaseRef.removeEventListener(mListener);
//    }
}
