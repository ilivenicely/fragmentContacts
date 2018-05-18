package com.example.apple.contactlist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.contactlist.MainActivity;
import com.example.apple.contactlist.R;
import com.example.apple.contactlist.adapter.ContactListAdapter;
import com.example.apple.contactlist.databinding.FragmentHomeBinding;
import com.example.apple.contactlist.model.Contact;

public class HomeFragment extends BaseFragment implements ContactListAdapter.OnItemClickListener {

    FragmentHomeBinding binding;
    ContactListAdapter adapter;
    private OnContactClickListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRecyclerView();
        setAdapter();
        visibleViews();
        setListeners();
    }

    private void setListeners() {
        binding.fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                ((MainActivity)getActivity()).addFragment(AddContactFragment.newInstance());
                break;
        }
    }

    private void setAdapter() {
        adapter = new ContactListAdapter(mActivity, ((MainActivity) mActivity).contacts,this);
        binding.rvContacts.setAdapter(adapter);
    }

    public void updateList() {
        adapter.notifyDataSetChanged();
        visibleViews();
    }

    public void visibleViews() {
        if (((MainActivity) mActivity).contacts.size() > 0) {
            binding.rvContacts.setVisibility(View.VISIBLE);
            binding.frameNoConatct.setVisibility(View.GONE);
        } else {
            binding.rvContacts.setVisibility(View.GONE);
            binding.frameNoConatct.setVisibility(View.VISIBLE);
        }
    }

    private void setRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        binding.rvContacts.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(View view, int position) {
        Contact contact = ((MainActivity)getActivity()).contacts.get(position);
        if (mListener != null) {
            mListener.onContactClick(contact);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnContactClickListener) {
            mListener = (OnContactClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnStudentAddedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }
}
