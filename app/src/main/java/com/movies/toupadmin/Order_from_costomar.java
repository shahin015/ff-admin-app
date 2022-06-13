package com.movies.toupadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Order_from_costomar extends AppCompatActivity {
   public static RecyclerView recyclerView;
    public static   DatabaseReference reference;
    public static  AdminAdapter adapter;
    public static ArrayList<OrderData> list;

    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_from_costomar);
        recyclerView=findViewById(R.id.recylerview);
        reference= FirebaseDatabase.getInstance().getReference().child("order");
        swipeRefreshLayout=findViewById(R.id.swift);
        list=new ArrayList<>();

        loadData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadData();

                swipeRefreshLayout.setRefreshing(false);
            }
        });



    }

    public  void loadData() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter=new AdminAdapter(getApplicationContext(),list);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                if (!snapshot.exists()){

                }else {

                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        OrderData data=dataSnapshot.getValue(OrderData.class);
                        list.add(0,data);

                    }
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                  //  Toast.makeText(Order_from_costomar.this, "Data Found", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }









}