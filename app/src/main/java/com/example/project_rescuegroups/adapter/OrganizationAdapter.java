package com.example.project_rescuegroups.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_rescuegroups.MapActivity;
import com.example.project_rescuegroups.R;
import com.example.project_rescuegroups.model.Organization;

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

        final Organization organization = organizationList.get(position);
        TextView textViewName = convertView.findViewById(R.id.tvOrganizationName);
        TextView textViewAddress = convertView.findViewById(R.id.tvOrganizationAddress);
        TextView textViewCity = convertView.findViewById(R.id.tvOrganizationCity);
        TextView textViewZip = convertView.findViewById(R.id.tvOrganizationZip);
        TextView textViewState = convertView.findViewById(R.id.tvOrganizationState);
        TextView textViewCountry = convertView.findViewById(R.id.tvOrganizationCountry);
        TextView textViewPhone = convertView.findViewById(R.id.tvOrganizationPhone);
        TextView textViewEmail = convertView.findViewById(R.id.tvOrganizationEmail);
        ImageButton btnGeo = convertView.findViewById(R.id.btnGeo);
        ImageButton btnEmail = convertView.findViewById(R.id.btnEmailOrg);

        textViewName.setText(organization.getOrganizationName());
        textViewAddress.setText(organization.getOrganizationAddress());
        textViewCity.setText(organization.getOrganizationCity());
        textViewZip.setText(organization.getOrganizationZip());
        textViewState.setText(organization.getOrganizationState());
        textViewCountry.setText(organization.getOrganizationCountry());
        textViewPhone.setText(organization.getOrganizationPhone());
        textViewEmail.setText(organization.getOrganizationEmail());

        btnGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("organization", organization);
                context.startActivity(intent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{organization.getOrganizationEmail()});
                try {
                    context.startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }
}
