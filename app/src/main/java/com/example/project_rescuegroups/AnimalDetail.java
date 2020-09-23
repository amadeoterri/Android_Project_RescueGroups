package com.example.project_rescuegroups;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_rescuegroups.database.OrganizationDB;
import com.example.project_rescuegroups.model.Animal;
import com.squareup.picasso.Picasso;

public class AnimalDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        final TextView tvName = findViewById(R.id.tvNameDetail);
        final ImageView imgDetail = findViewById(R.id.imgDetail);
        final TextView tvBreed = findViewById(R.id.tvBreedDetail);
        final TextView tvSex = findViewById(R.id.tvSexDetail);
        final TextView tvSpecies = findViewById(R.id.tvSpeciesDetail);
        final TextView tvBirthDate = findViewById(R.id.tvBirthDateDetail);
        final TextView tvDescription = findViewById(R.id.tvDescriptionDetail);
        final ImageButton btnEmailOrg = findViewById(R.id.btnEmailOrg);

        Intent i = getIntent();
        final Animal a = i.getParcelableExtra("animal");

        tvName.setText(a.getAnimalName());
        Picasso.get().load(a.getAnimalImageUrl()).into(imgDetail);
        tvBreed.setText("Breed: " + a.getAnimalBreed());
        tvSex.setText("Sex: " + a.getAnimalSex());
        tvSpecies.setText("Species: " + a.getAnimalSpecies());
        tvBirthDate.setText("Birthdate: " + a.getAnimalBirthDate());
        tvDescription.setText(a.getAnimalDescription());

        OrganizationDB sh = new OrganizationDB(this);

        String email = "";
        Cursor cursor = sh.getOrganizationEmail(a);
        if (cursor.moveToFirst()) {
            email = cursor.getString(0);
        }

        final String finalEmail = email;
        final String subject = "Confer " + a.getAnimalName() + " ID: " + a.getAnimalId();

        btnEmailOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{finalEmail});
                i.putExtra(Intent.EXTRA_SUBJECT, subject);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AnimalDetail.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
