package com.hacktiv8.finalproject2;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadProductActivity extends AppCompatActivity {
    private TextView nameProduct, description, price, brand, quantity, IDproduct;
    private ImageView uploadImage, productImage;
    private Button submit;
    private ProgressBar progressBar;

    private Uri mImageUri;
    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;
    private StorageTask mUploadTask;

    private static final int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        IDproduct = findViewById(R.id.inputIDproduct);
        nameProduct = findViewById(R.id.inputProductHeadline);
        price = findViewById(R.id.inputPrice);
        brand = findViewById(R.id.inputBrand);
        quantity = findViewById(R.id.inputQuantity);
        description = findViewById(R.id.inputDescription);

        uploadImage = findViewById(R.id.uploadImage);
        productImage = findViewById(R.id.productImage);

        progressBar = findViewById(R.id.progressBar);
        submit = findViewById(R.id.btnSubmit);

        mStorageReference = FirebaseStorage.getInstance().getReference("uploads");
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            // check if user is signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                StorageReference fileReference = mStorageReference.child("uploads/" + System.currentTimeMillis() + "." + getFileExtension(mImageUri));
                mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        urlTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadUrl) {
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(0);
                                    }
                                }, 5000);

                                Toast.makeText(UploadProductActivity.this, "Upload Succes", Toast.LENGTH_SHORT).show();

                                String productCode = IDproduct.getText().toString().trim();
                                if (productCode.startsWith("TS-")) {
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("T-shirt");
                                    UploadProduct uploadProduct = new UploadProduct(
                                            IDproduct.getText().toString().trim(),
                                            nameProduct.getText().toString().trim(),
                                            price.getText().toString().trim(),
                                            brand.getText().toString().trim(),
                                            description.getText().toString().trim(),
                                            quantity.getText().toString().trim(),
                                            downloadUrl.toString());
                                    String uploadId = mDatabaseReference.push().getKey();
                                    mDatabaseReference.child(uploadId).setValue(uploadProduct);
                                    startActivity(new Intent(getApplicationContext(), ActivityAdmin.class));
                                    finish();
                                } else if (productCode.startsWith("FM-")) {
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("Formal");
                                    UploadProduct uploadProduct = new UploadProduct(
                                            IDproduct.getText().toString().trim(),
                                            nameProduct.getText().toString().trim(),
                                            price.getText().toString().trim(),
                                            brand.getText().toString().trim(),
                                            description.getText().toString().trim(),
                                            quantity.getText().toString().trim(),
                                            downloadUrl.toString());
                                    String uploadId = mDatabaseReference.push().getKey();
                                    mDatabaseReference.child(uploadId).setValue(uploadProduct);
                                    startActivity(new Intent(getApplicationContext(), ActivityAdmin.class));
                                    finish();
                                } else if (productCode.startsWith("BM-")) {
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("Bottom Wear");
                                    UploadProduct uploadProduct = new UploadProduct(
                                            IDproduct.getText().toString().trim(),
                                            nameProduct.getText().toString().trim(),
                                            price.getText().toString().trim(),
                                            brand.getText().toString().trim(),
                                            description.getText().toString().trim(),
                                            quantity.getText().toString().trim(),
                                            downloadUrl.toString());
                                    String uploadId = mDatabaseReference.push().getKey();
                                    mDatabaseReference.child(uploadId).setValue(uploadProduct);
                                    startActivity(new Intent(getApplicationContext(), ActivityAdmin.class));
                                    finish();
                                } else if (productCode.startsWith("SH-")) {
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("Shoes");
                                    UploadProduct uploadProduct = new UploadProduct(
                                            IDproduct.getText().toString().trim(),
                                            nameProduct.getText().toString().trim(),
                                            price.getText().toString().trim(),
                                            brand.getText().toString().trim(),
                                            description.getText().toString().trim(),
                                            quantity.getText().toString().trim(),
                                            downloadUrl.toString());
                                    String uploadId = mDatabaseReference.push().getKey();
                                    mDatabaseReference.child(uploadId).setValue(uploadProduct);
                                    startActivity(new Intent(getApplicationContext(), ActivityAdmin.class));
                                    finish();
                                } else if (productCode.startsWith("CO-")) {
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("Computer");
                                    UploadProduct uploadProduct = new UploadProduct(
                                            IDproduct.getText().toString().trim(),
                                            nameProduct.getText().toString().trim(),
                                            price.getText().toString().trim(),
                                            brand.getText().toString().trim(),
                                            description.getText().toString().trim(),
                                            quantity.getText().toString().trim(),
                                            downloadUrl.toString());
                                    String uploadId = mDatabaseReference.push().getKey();
                                    mDatabaseReference.child(uploadId).setValue(uploadProduct);
                                    startActivity(new Intent(getApplicationContext(), ActivityAdmin.class));
                                    finish();
                                } else if (productCode.startsWith("SP-")) {
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("Smartphone");
                                    UploadProduct uploadProduct = new UploadProduct(
                                            IDproduct.getText().toString().trim(),
                                            nameProduct.getText().toString().trim(),
                                            price.getText().toString().trim(),
                                            brand.getText().toString().trim(),
                                            description.getText().toString().trim(),
                                            quantity.getText().toString().trim(),
                                            downloadUrl.toString());
                                    String uploadId = mDatabaseReference.push().getKey();
                                    mDatabaseReference.child(uploadId).setValue(uploadProduct);
                                    startActivity(new Intent(getApplicationContext(), ActivityAdmin.class));
                                    finish();
                                } else if (productCode.startsWith("BOOK-")) {
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("Books");
                                    UploadProduct uploadProduct = new UploadProduct(
                                            IDproduct.getText().toString().trim(),
                                            nameProduct.getText().toString().trim(),
                                            price.getText().toString().trim(),
                                            brand.getText().toString().trim(),
                                            description.getText().toString().trim(),
                                            quantity.getText().toString().trim(),
                                            downloadUrl.toString());
                                    String uploadId = mDatabaseReference.push().getKey();
                                    mDatabaseReference.child(uploadId).setValue(uploadProduct);
                                    startActivity(new Intent(getApplicationContext(), ActivityAdmin.class));
                                    finish();
                                } else if (productCode.startsWith("OT-")) {
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("Others");
                                    UploadProduct uploadProduct = new UploadProduct(
                                            IDproduct.getText().toString().trim(),
                                            nameProduct.getText().toString().trim(),
                                            price.getText().toString().trim(),
                                            brand.getText().toString().trim(),
                                            description.getText().toString().trim(),
                                            quantity.getText().toString().trim(),
                                            downloadUrl.toString());
                                    String uploadId = mDatabaseReference.push().getKey();
                                    mDatabaseReference.child(uploadId).setValue(uploadProduct);
                                    startActivity(new Intent(getApplicationContext(), ActivityAdmin.class));
                                    finish();
                                }




                            }
                        });
                    }
                }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadProductActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), DetailProduct.class));
                        finish();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        progressBar.setProgress((int) progress);

                    }
                });
            } else {
                // user not signed in, handle error
                Toast.makeText(this, "Please sign in to upload", Toast.LENGTH_SHORT).show();
                // redirect user to sign in screen
                Intent intent = new Intent(this, DetailProduct.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Image No Selected", Toast.LENGTH_SHORT).show();
        }
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(uploadImage);
        }
    }
}

