package com.example.project_rescuegroups;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_rescuegroups.adapter.OrganizationAdapter;
import com.example.project_rescuegroups.database.OrganizationDB;
import com.example.project_rescuegroups.model.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationActivity extends AppCompatActivity {

    private List<Organization> organizationList;
    Context context;
    OrganizationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);

        context = this;

        final ListView listView = findViewById(R.id.listViewOrganizations);
        OrganizationDB sh = new OrganizationDB(this);
        Cursor cursor = sh.getAllOrganizations();
        organizationList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                String id = cursor.getString(1);
                String name = cursor.getString(2);
                String address = cursor.getString(3);
                String city = cursor.getString(4);
                String state = cursor.getString(5);
                String zip = cursor.getString(6);
                String country = cursor.getString(7);
                String phone = cursor.getString(8);
                String email = cursor.getString(9);
                Organization org = new Organization(id, name, address, city, state, zip, country, phone, email);
                organizationList.add(org);
                cursor.moveToNext();
            }
        }
        mAdapter = new OrganizationAdapter(context, organizationList);
        listView.setAdapter(mAdapter);
    }
}
