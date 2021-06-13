package com.example.collegeapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class BlogRequestedAdapter extends RecyclerView.Adapter<BlogRequestedAdapter.BlogRequestedViewAdapter>{

    private List<BlogData> list;
    private Context context;
    public String[] ItemList;

    private DatabaseReference databaseReference , dbRef;

    public BlogRequestedAdapter(List<BlogData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BlogRequestedAdapter.BlogRequestedViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_blog_requested, parent, false);
        return new BlogRequestedViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogRequestedAdapter.BlogRequestedViewAdapter holder, int position) {
        BlogData item = list.get(position);
        holder.blogData.setText(item.getBlogData());
        holder.currentDateA.setText(item.getCurrentDate());
        Picasso.get().load(item.getBlogPic()).into(holder.image);

        holder.DeleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure to reject this Blog ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Blog");
                        reference.child("Index").child(item.getUniqueKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                Toast.makeText(context,"Successfully Deleted",Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                            }
                        });
                        notifyItemRemoved(position);

                    }
                });
                builder.setNegativeButton("No",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog dialog = null;
                try {
                    dialog = builder.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(dialog!=null)
                    dialog.show();
            }
        });

        holder.ApproveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                databaseReference = FirebaseDatabase.getInstance().getReference().child("BlogAccepted");
                dbRef = databaseReference.child("Index");
                final String uniqueKey = dbRef.push().getKey();

                BlogData Data = new BlogData(item.getBlogData(), item.getBlogPic(),currentDate ,uniqueKey);


                dbRef.child(uniqueKey).setValue(Data).addOnSuccessListener(new OnSuccessListener<Void>(

                ) {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context,"Approved Successful",Toast.LENGTH_SHORT).show();
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

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BlogRequestedViewAdapter extends RecyclerView.ViewHolder
    {
        private TextView blogData, currentDateA;
        private ImageView image;
        private Button DeleteB,ApproveB;

        public BlogRequestedViewAdapter(@NonNull View itemView)
        {
            super(itemView);
            blogData = itemView.findViewById(R.id.blogDes);
            currentDateA = itemView.findViewById(R.id.currentDate);
            image = itemView.findViewById(R.id.blogP);
            DeleteB = itemView.findViewById(R.id.reject);
            ApproveB = itemView.findViewById(R.id.approve);
        }

    }
}
