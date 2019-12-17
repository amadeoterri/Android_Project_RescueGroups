package com.example.project_rescuegroups.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.project_rescuegroups.model.Organization;
import com.example.project_rescuegroups.response.JSONResponseImpl;
import com.example.project_rescuegroups.adapter.OrganizationAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class OrganizationAsyncTask extends AsyncTask<ListView, Void, List<Organization>> {

    private ListView listView;
    Context context;

    public OrganizationAsyncTask(Context myContext) {
        this.context = myContext;
    }

    @Override
    protected List<Organization> doInBackground(ListView... listViews) {
        listView = listViews[0];
        String data;
        try {
            data = loadJSONFromAsset();
            JSONResponseImpl responseImpl = new JSONResponseImpl();
            return responseImpl.handleOrganizationResponse(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Organization> organizations) {
        super.onPostExecute(organizations);
        OrganizationAdapter organizationAdapter = new OrganizationAdapter(listView.getContext(), organizations);
        listView.setAdapter(organizationAdapter);
    }

    //json file uitlezen (eerst omzetten naar ander json format)
    public String loadJSONFromAsset() {
        StringBuilder text = new StringBuilder();
        try {
            InputStream is = context.getAssets().open("organizations.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            int counter = 1;
            text.append("{\"organizations\":");
            text.append("[");
            while (((line = br.readLine()) != null)
                    /*&& (counter < 300)*/
            ) {
                text.append(line);
                /*if(counter <299) {
                    text.append(",");
                }*/
                text.append(",");
                counter++;
            }
            //laatste komma deleten
            text.substring(0, text.length() - 1);
            text.append("]}");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return text.toString();
    }
}
