package com.movies.toupadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Offerl_list extends AppCompatActivity {

    EditText pack;
    EditText taka,maindimond,bonas,tototaldimond;
    Button button;


    PackCalss packCalss;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerl_list);

        pack=findViewById(R.id.textview);
        taka=findViewById(R.id.taka);
        maindimond=findViewById(R.id.maindaimond);
        bonas=findViewById(R.id.bonas);
        tototaldimond=findViewById(R.id.total);
        button=findViewById(R.id.button);
        packCalss=new PackCalss();
        progressDialog=new ProgressDialog(this);










        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("pack");




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pname=pack.getText().toString();
                String Taka= taka.getText().toString().trim();
                String Maindaimond=taka.getText().toString().trim();
                String Bonas=bonas.getText().toString().trim();
                String TotalDaimond=tototaldimond.getText().toString().trim();

                if (Pname.isEmpty()||Taka.isEmpty()||Maindaimond.isEmpty()||Bonas.isEmpty()||TotalDaimond.isEmpty()){

                    Toast.makeText(Offerl_list.this, "Fill All Box", Toast.LENGTH_SHORT).show();
                    return;
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

                        startActivity(new Intent(Offerl_list.this,MainActivity.class));
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