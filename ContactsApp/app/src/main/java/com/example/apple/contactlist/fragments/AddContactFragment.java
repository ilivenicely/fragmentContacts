package com.example.apple.contactlist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.apple.contactlist.MainActivity;
import com.example.apple.contactlist.R;
import com.example.apple.contactlist.model.Contact;

public class AddContactFragment extends BaseFragment {


    private EditText etFname,etLName,etPhone;
    private Button btnAdd;

    private OnContactAddedListener mListener;

    public static AddContactFragment newInstance() {
        AddContactFragment fragment = new AddContactFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_contact, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etFname = view.findViewById(R.id.etFname);
        etLName = view.findViewById(R.id.etLname);
        etPhone = view.findViewById(R.id.etPhone);
        btnAdd = view.findViewById(R.id.btnAddContact);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etFname.getText())) {
                    etFname.setError("Please enter First Name");
                    return;
                }
                if (TextUtils.isEmpty(etLName.getText())) {
                    etLName.setError("Please enter Last Name");
                    return;
                }
                if (TextUtils.isEmpty(etPhone.getText())) {
                    etPhone.setError("Please enter Phone");
                    return;
                }

                Contact contact = new Contact();
                contact.setFirstName(etFname.getText().toString());
                contact.setLastName(etLName.getText().toString());
                contact.setPhoneNumber(etPhone.getText().toString());
                mListener.onContactAdded(contact);
                ((MainActivity)getActivity()).hideKeyboard();
                getActivity().onBackPressed();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnContactAddedListener) {
            mListener = (OnContactAddedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnStudentAddedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnContactAddedListener {
        public void onContactAdded(Contact contact);
    }
}
