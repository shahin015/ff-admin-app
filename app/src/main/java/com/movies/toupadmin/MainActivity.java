package com.movies.toupadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText pack;
    EditText taka,maindimond,bonas,tototaldimond;
    Button button,orderlist;

    PackCalss packCalss;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pack=findViewById(R.id.textview);
        taka=findViewById(R.id.taka);
        maindimond=findViewById(R.id.maindaimond);
        bonas=findViewById(R.id.bonas);
        tototaldimond=findViewById(R.id.total);
        button=findViewById(R.id.button);
        packCalss=new PackCalss();
        progressDialog=new ProgressDialog(this);

        orderlist=findViewById(R.id.orderlist);
        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Order_from_costomar.class));

            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pack");

        // below line is used to get reference for our database.



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pname=pack.getText().toString();
                String Taka= taka.getText().toString().trim();
                String Maindaimond=taka.getText().toString().trim();
                String Bonas=bonas.getText().toString().trim();
                String TotalDaimond=tototaldimond.getText().toString().trim();

                if (Pname.isEmpty()||Taka.isEmpty()||Maindaimond.isEmpty()||Bonas.isEmpty()||TotalDaimond.isEmpty()){

                    Toast.makeText(MainActivity.this, "Fill All Box", Toast.LENGTH_SHORT).show();
                }
                progressDialog.setMessage("Saving Data ");
                progressDialog.show();
                packCalss.setTaka(Taka);
                packCalss.setMaindaimond(Maindaimond);
                packCalss.setBonas(Bonas);
                packCalss.setTotaldaimond(TotalDaimond);

                databaseReference.child(Pname).setValue(packCalss).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();

                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        finish();





                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();

                    }
                });





            }
        });







    }
}