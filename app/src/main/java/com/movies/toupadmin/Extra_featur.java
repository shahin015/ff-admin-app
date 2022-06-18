package com.movies.toupadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Extra_featur extends AppCompatActivity {
    EditText edline1,edline2,edline3,edline4;
    Button line1,line2,line3,line4;
    private TextView adstv;

  private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,m_adscontrol;
    private Switch adscontrol;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_featur);
        adscontrol=findViewById(R.id.switch_1);
        edline1=findViewById(R.id.edlineone);
        edline2=findViewById(R.id.edlinetow);
        edline3=findViewById(R.id.edlineTree);
        edline4=findViewById(R.id.edlinefour);
        line1=findViewById(R.id.buttonl1);
        line2=findViewById(R.id.buttonl2);
        line3=findViewById(R.id.buttonl3);
        line4=findViewById(R.id.buttonl4);
        adstv=findViewById(R.id.adswithmemo);

        progressDialog=new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("LineData");

        m_adscontrol=firebaseDatabase.getReference();


        adscontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog.setMessage("Updating");
                progressDialog.setCancelable(false);
                progressDialog.show();
                String tag=adscontrol.getTag().toString();


                if (tag.contains("off")){

                   m_adscontrol.child("adsControl").setValue("on").addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           Toast.makeText(Extra_featur.this, "On", Toast.LENGTH_SHORT).show();
                           adscontrol.setTag("on");
                           adstv.setText("Ads are Now On");

                           progressDialog.dismiss();

                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           progressDialog.dismiss();

                       }
                   });

                }else {



                    m_adscontrol.child("adsControl").setValue("of").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(Extra_featur.this, "of", Toast.LENGTH_SHORT).show();
                            adscontrol.setTag("off");
                            adstv.setText("Ads are Now of");
                            progressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();

                        }
                    });

                }


            }
        });



        line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Edline1=edline1.getText().toString();
                if (Edline1.isEmpty()){
                    Toast.makeText(Extra_featur.this, "Enter Changes", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setTitle("Uploading Data");
                progressDialog.show();

                databaseReference.child("Line_one").setValue(Edline1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(Extra_featur.this, "Data Changed", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Extra_featur.this, "Faild To Data Changes", Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });




        line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Edline1=edline2.getText().toString();
                if (Edline1.isEmpty()){
                    Toast.makeText(Extra_featur.this, "Enter Changes", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setTitle("Uploading Data");
                progressDialog.show();

                databaseReference.child("Line_tow").setValue(Edline1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(Extra_featur.this, "Data Changed", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Extra_featur.this, "Faild To Data Changes", Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });



        line3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Edline1=edline3.getText().toString();
                if (Edline1.isEmpty()){
                    Toast.makeText(Extra_featur.this, "Enter Changes", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setTitle("Uploading Data");
                progressDialog.show();

                databaseReference.child("Line_three").setValue(Edline1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(Extra_featur.this, "Data Changed", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Extra_featur.this, "Faild To Data Changes", Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });



        line4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Edline1=edline4.getText().toString();
                if (Edline1.isEmpty()){
                    Toast.makeText(Extra_featur.this, "Enter Changes", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setTitle("Uploading Data");
                progressDialog.show();

                databaseReference.child("Line_four").setValue(Edline1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(Extra_featur.this, "Data Changed", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Extra_featur.this, "Faild To Data Changes", Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });
    }
}