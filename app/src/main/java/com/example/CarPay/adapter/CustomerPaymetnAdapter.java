package com.example.CarPay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CarPay.model.Customer;
import com.example.CarPay.R;

import java.util.ArrayList;

public class CustomerPaymetnAdapter extends RecyclerView.Adapter<CustomerPaymetnAdapter.customerPaymetnViewHolder> {
    ArrayList<Customer> arrayList=new ArrayList<>();
    Context context;
    OnUserClicked onUserClicked;
    public CustomerPaymetnAdapter(ArrayList<Customer> arrayList, Context context,OnUserClicked onUserClicked) {
        this.arrayList = arrayList;
        this.context = context;
        this.onUserClicked=onUserClicked;
    }
    public interface OnUserClicked
    {
        public void onDelete(Customer c);
    }

    @NonNull
    @Override
    public customerPaymetnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_customer_payment_item,parent,false);
        return new customerPaymetnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customerPaymetnViewHolder holder, int position) {
        Customer customerPayment=arrayList.get(position);
        holder.customerName.setText(customerPayment.getCustomerName());
        holder.totalPrice.setText(customerPayment.getCash());
        holder.carName.setText(customerPayment.getCarName());
        holder.carModel.setText(customerPayment.getCarModel());
        holder.carPrice.setText(customerPayment.getCarPrice());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUserClicked.onDelete(customerPayment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class customerPaymetnViewHolder extends RecyclerView.ViewHolder
    {
        TextView customerName,totalPrice,carName,carModel,carPrice;
        ImageView delete;
        public customerPaymetnViewHolder(@NonNull View itemView) {
            super(itemView);
            customerName=itemView.findViewById(R.id.customerName);
            totalPrice=itemView.findViewById(R.id.totalPrice);
            carName=itemView.findViewById(R.id.carName);
            carModel=itemView.findViewById(R.id.carModel);
            carPrice=itemView.findViewById(R.id.carPrice);
            delete=itemView.findViewById(R.id.deleteCustomer);

        }
    }
}
