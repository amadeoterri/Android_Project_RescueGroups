package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project_rescuegroups.asynctask.AnimalsAsyncTask;
import com.example.project_rescuegroups.asynctask.OrganizationAsyncTask;
import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.model.Organization;

import java.util.List;

public class OrganizationActivity extends AppCompatActivity {

    private List<Organization> organizationList;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);

        context = this;

        final ListView listView = findViewById(R.id.listViewOrganizations);

        //asynctask om elementen uit json file te halen
        //listview meegeven
        new OrganizationAsyncTask(this).execute(listView);

    }
}
