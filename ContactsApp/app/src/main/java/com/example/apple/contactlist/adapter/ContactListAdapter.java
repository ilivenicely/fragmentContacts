package com.example.apple.contactlist.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.contactlist.R;
import com.example.apple.contactlist.databinding.ListContactBinding;
import com.example.apple.contactlist.model.Contact;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {
    Context c;
    ArrayList<Contact> list;
    OnItemClickListener onItemClickListener;

    public ContactListAdapter(Context c, ArrayList<Contact> list,OnItemClickListener onItemClickListener) {
        this.c = c;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contact, parent, false);
        return new MyViewHolder(v.getRootView());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.txtName.setText(list.get(position).getFirstName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ListContactBinding binding;

        public MyViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!= null) {
                        onItemClickListener.onItemClick(v,getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view,int position);
    }
}
