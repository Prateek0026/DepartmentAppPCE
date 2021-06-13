package com.example.collegeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CreateBlog extends AppCompatActivity {

    private Button PicBlogButton, requestBlog;
    private EditText descriptionBlog;
    private ImageView imageBlog;
    private String blogData;

    private  final int REQ = 1;
    private Bitmap bitmap;

    private StorageReference storageReference;
    private DatabaseReference databaseReference , dbRef;

    private ProgressBar progressBar;
    String downloadUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_blog);

        PicBlogButton = (Button)findViewById(R.id.addImage);
        requestBlog = (Button)findViewById(R.id.requestBlog);
        descriptionBlog = (EditText)findViewById(R.id.descriptionForBlog);
        imageBlog = (ImageView)findViewById(R.id.blogImage);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Blog");
        storageReference = FirebaseStorage.getInstance().getReference();

        RelativeLayout layout = findViewById(R.id.displayb);

        PicBlogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

        requestBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = new ProgressBar(CreateBlog.this, null, android.R.attr.progressBarStyleLarge);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                layout.addView(progressBar, params);
                progressBar.setVisibility(View.VISIBLE);
                if(Check())
                {
                    UploadImage();
                }

            }
        });
    }

    private void OpenGallery()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ && resultCode == RESULT_OK)
        {
            Uri url = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageBlog.setImageBitmap(bitmap);
        }
    }

    private void UploadImage()
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] FinalImg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("BlogPic").child(FinalImg + "jpg");
        final UploadTask uploadTask = filePath.putBytes(FinalImg);
        uploadTask.addOnCompleteListener(CreateBlog.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(Task<UploadTask.TaskSnapshot> task)
            {
                if (task.isSuccessful())
                {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        public void onSuccess(UploadTask.TaskSnapshot task)
                        {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                public void onSuccess(Uri url)
                                {
                                    downloadUrl = String.valueOf(url);
                                    UploadData();

                                }
                            });
                        }
                    });
                }
                else {
                    Toast.makeText(CreateBlog.this,"Something went Wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void UploadData()
    {
        dbRef = databaseReference.child("Index");
        final String uniqueKey = dbRef.push().getKey();

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        BlogData Data = new BlogData(descriptionBlog.getText().toString(), downloadUrl,currentDate, uniqueKey);


        dbRef.child(uniqueKey).setValue(Data).addOnSuccessListener(new OnSuccessListener<Void>(

        ) {
            @Override
            public void onSuccess(Void aVoid) {
               /* DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Organizer");
                DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("Organizer");


                reference2.child("Index").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        OrganizerData userData = snapshot.getValue(OrganizerData.class);
                        String mp = userData.getMc();

                        int I = Integer.parseInt(mp) + L;
                        L=0;

                        String G = String.valueOf(I) ;
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("mc",G);

                        reference3.child("Index").updateChildren(hashMap2).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {

                            }
                        }).addOnFailureListener(new OnFailureListener(){
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

                progressBar.setVisibility(View.GONE);
                Toast.makeText(CreateBlog.this,"Your Blog is Successfully Send to Request",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateBlog.this, NavigationPage.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CreateBlog.this,"Something Went Wrong, Try Again Later", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean Check()
    {
        boolean flag = false;
        blogData = descriptionBlog.getText().toString();

        if (blogData.isEmpty())
        {
            progressBar.setVisibility(View.GONE);
            descriptionBlog.setError("Empty");
            descriptionBlog.requestFocus();
        }
        else if(bitmap == null) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "No Image is Selected", Toast.LENGTH_SHORT).show();
        }
        else {
            flag = true;
        }

        return flag;
    }

}