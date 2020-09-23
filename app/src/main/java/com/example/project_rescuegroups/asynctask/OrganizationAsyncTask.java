package com.example.project_rescuegroups.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.project_rescuegroups.database.OrganizationDB;
import com.example.project_rescuegroups.model.Organization;
import com.example.project_rescuegroups.response.JSONResponseImplSingle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OrganizationAsyncTask extends AsyncTask<Void, Void, List<Organization>> {

    Context context;

    public OrganizationAsyncTask(Context myContext) {
        this.context = myContext;
    }

    @Override
    protected List<Organization> doInBackground(Void... voids) {
        List<Organization> organizationList = loadJSONFromAsset();
        return organizationList;
    }

    @Override
    protected void onPostExecute(List<Organization> organizations) {
        super.onPostExecute(organizations);
        OrganizationDB sh = new OrganizationDB((context));
        if (organizations != null) {
            for (Organization org : organizations) {
                sh.addOrganizations(org);
            }
        }

    }

    //json file uitlezen (eerst omzetten naar ander json format)
    public List<Organization> loadJSONFromAsset() {
        List<Organization> organizations = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("organizations.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while (((line = br.readLine()) != null)
            ) {
                JSONResponseImplSingle responseImpl = new JSONResponseImplSingle();
                Organization org = responseImpl.handleOrganizationResponse(line);
                if (org != null) {
                    organizations.add(org);
                }
            }
            return organizations;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
