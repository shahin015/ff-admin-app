package com.movies.toupadmin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.Viewholder> {
    Context context;
    ArrayList<OrderData>list;


    private Task<Void> reference;

    public AdminAdapter(Context context, ArrayList<OrderData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);
        return new Viewholder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        OrderData data=list.get(position);


        holder.ordername.setText(data.getFullname());
        holder.gameId.setText(data.getPlayerid());
        holder.packname.setText(data.getOrder_no());
        holder.paymnetaccount.setText(data.getAccount_no());
        holder.tracnsctionId.setText(data.getTransctionid());
        holder.orderdate.setText(data.getDate());
        holder.payment.setText(data.getBank());

        if (data.getColorcode().contains("f")){


        }else {
            holder.layout.setBackgroundColor(R.color.done);
        }

        holder.delevery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order").child(data.getOrder_no()).child("colorcode");
                ref.setValue("t");


            }



        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order").child(data.getOrder_no());
                ref.removeValue();











            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView ordername,gameId,packname,paymnetaccount,tracnsctionId,orderdate,payment;
        LinearLayout layout;
        Button button,delevery;
        public Viewholder(@NonNull View itemView) {
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




        }
    }
}
