package com.example.project_rescuegroups.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_rescuegroups.R;
import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.model.Organization;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrganizationAdapter extends ArrayAdapter<Organization> {

    private Context context;
    private List<Organization> organizationList;

    public OrganizationAdapter(Context context, List<Organization> organizationList) {
        super(context, R.layout.organization_list_item,organizationList);
        this.context = context;
        this.organizationList = organizationList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //als convertView null is een nieuwe View maken, anders hergebruiken
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.organization_list_item,parent,false);
        }

        Organization organization = organizationList.get(position);
        TextView textViewName = convertView.findViewById(R.id.tvOrganizationName);
        TextView textViewAddress = convertView.findViewById(R.id.tvOrganizationAddress);
        TextView textViewCity = convertView.findViewById(R.id.tvOrganizationCity);
        TextView textViewZip = convertView.findViewById(R.id.tvOrganizationZip);
        TextView textViewState = convertView.findViewById(R.id.tvOrganizationState);
        TextView textViewCountry = convertView.findViewById(R.id.tvOrganizationCountry);
        TextView textViewPhone = convertView.findViewById(R.id.tvOrganizationPhone);
        TextView textViewEmail = convertView.findViewById(R.id.tvOrganizationEmail);

        textViewName.setText(organization.getOrganizationName());
        textViewAddress.setText(organization.getOrganizationAddress());
        textViewCity.setText(organization.getOrganizationCity());
        textViewZip.setText(organization.getOrganizationZip());
        textViewState.setText(organization.getOrganizationState());
        textViewCountry.setText(organization.getOrganizationCountry());
        textViewPhone.setText(organization.getOrganizationPhone());
        textViewEmail.setText(organization.getOrganizationEmail());
        return convertView;
    }
}
