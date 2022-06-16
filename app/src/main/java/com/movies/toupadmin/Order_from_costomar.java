package com.movies.toupadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.movies.toupadmin.pushnotication.FcmNotificationsSender;


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
       FirebaseMessaging.getInstance().subscribeToTopic("notification");
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
        adapter=new AdminAdapter(this,list);

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




public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder>  {

    Context context;
    ArrayList<OrderData>list;


    public AdminAdapter(Context context, ArrayList<OrderData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_final,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        OrderData data=list.get(position);


        holder.ordername.setText(data.getFullname());
        holder.gameId.setText(data.getPlayerid());
        holder.packname.setText(data.getOrder_no());
        holder.paymnetaccount.setText(data.getAccount_no());
        holder.tracnsctionId.setText(data.getTransctionid());
        holder.orderdate.setText(data.getDate());
        holder.payment.setText(data.getBank());
        FirebaseMessaging.getInstance().subscribeToTopic("all");

        if (data.getColorcode()!=null){


        if (data.getColorcode().contains("f")){


        }else {
            holder.layout.setBackgroundColor(R.color.done);
            holder.status.setText("Deleveryed");
            holder.delevery.setVisibility(View.INVISIBLE);
        }}



        holder.delevery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              ///  String massage="New massage";
                String massage=data.getFullname().toString();
                String titile="Delevery Done";

                FcmNotificationsSender notificationsSender=new FcmNotificationsSender("/topics/all",
                        titile,massage,getApplicationContext(),Order_from_costomar.this);

                notificationsSender.SendNotifications();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order").child(data.getOrder_no()).child("colorcode");

                ref.setValue("t");



            }



        });

        holder.copys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboardManager= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("label",data.getPlayerid());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(context, "Copy:"+data.getPlayerid(), Toast.LENGTH_SHORT).show();


            }
        });




        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order").child(data.getOrder_no());

                if (ref.equals(null)){
                    Toast.makeText(context, "Data Is delete", Toast.LENGTH_SHORT).show();
                }else ref.removeValue();
                loadData();











            }
        });





    }


    @Override
    public int getItemCount() {



        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ordername,gameId,packname,paymnetaccount,tracnsctionId,orderdate,payment,status;
        TextView copys;
        RelativeLayout layout;
        Button button,delevery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            orderdate=itemView.findViewById(R.id.orderDate);
            ordername=itemView.findViewById(R.id.ordername);
            gameId=itemView.findViewById(R.id.gameuid);
            packname=itemView.findViewById(R.id.packname);
            paymnetaccount=itemView.findViewById(R.id.payment_account);
            tracnsctionId=itemView.findViewById(R.id.transctionId);
            payment=itemView.findViewById(R.id.payment_methord);
            layout=itemView.findViewById(R.id.layout);
            button=itemView.findViewById(R.id.delete);
            delevery=itemView.findViewById(R.id.delevery);
            status=itemView.findViewById(R.id.stasus);
            copys=itemView.findViewById(R.id.copys);





        }
    }
}




}