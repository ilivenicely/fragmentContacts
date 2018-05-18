package com.example.apple.contactlist;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.apple.contactlist.databinding.ActivityMainBinding;
import com.example.apple.contactlist.fragments.AddContactFragment;
import com.example.apple.contactlist.fragments.DetailFragment;
import com.example.apple.contactlist.fragments.HomeFragment;
import com.example.apple.contactlist.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnContactClickListener,AddContactFragment.OnContactAddedListener {
    ActivityMainBinding binding;
    Activity mActivity;
   public ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        binding = DataBindingUtil.setContentView(mActivity, R.layout.activity_main);
        goToFragment(new HomeFragment());
    }

    public void goToFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.main_container, frag)
                .commit();
    }

    public void addFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right)
                .add(R.id.main_container, frag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onContactClick(Contact contact) {
        addFragment(DetailFragment.newInstance(contact));
    }

    @Override
    public void onContactAdded(Contact contact) {
        contacts.add(contact);
        goToFragment(new HomeFragment());
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }
}
