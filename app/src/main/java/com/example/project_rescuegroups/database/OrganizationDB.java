package com.example.project_rescuegroups.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.model.Organization;

public class OrganizationDB extends SchemaHelper {

    public OrganizationDB(Context context) {
        super(context);
    }

    public long addOrganizations(Organization org) {
        if (!(hasObject(org.getOrganizationID()))) {

            ContentValues cv = new ContentValues();
            cv.put(OrganizationTabel.ORGANIZATION_ID, org.getOrganizationID());
            cv.put(OrganizationTabel.ORGANIZATION_NAME, org.getOrganizationName());
            cv.put(OrganizationTabel.ORGANIZATION_ADDRESS, org.getOrganizationAddress());
            cv.put(OrganizationTabel.ORGANIZATION_CITY, org.getOrganizationCity());
            cv.put(OrganizationTabel.ORGANIZATION_STATE, org.getOrganizationState());
            cv.put(OrganizationTabel.ORGANIZATION_ZIP, org.getOrganizationZip());
            cv.put(OrganizationTabel.ORGANIZATION_COUNTRY, org.getOrganizationCountry());
            cv.put(OrganizationTabel.ORGANIZATION_PHONE, org.getOrganizationPhone());
            cv.put(OrganizationTabel.ORGANIZATION_EMAIL, org.getOrganizationEmail());

            SQLiteDatabase sd = getWritableDatabase();

            return sd.insert(OrganizationTabel.TABEL_NAAM, OrganizationTabel.ORGANIZATION_ID, cv);
        } else {
            return 0;
        }
    }

    public Cursor getAllOrganizations() {
        SQLiteDatabase sd = getWritableDatabase();
        return sd.rawQuery("SELECT * FROM " + OrganizationTabel.TABEL_NAAM, null);
    }

    public Cursor getOrganizationEmail(Animal animal) {
        SQLiteDatabase sd = getWritableDatabase();

        return sd.rawQuery("SELECT " + OrganizationTabel.ORGANIZATION_EMAIL + " FROM " + OrganizationTabel.TABEL_NAAM + " , " + AnimalTabel.TABEL_NAAM
                        + " WHERE " + AnimalTabel.TABEL_NAAM + "." + AnimalTabel.ANIMAL_ORGID + " = " + OrganizationTabel.TABEL_NAAM + "." + OrganizationTabel.ORGANIZATION_ID
                        + " AND " + AnimalTabel.TABEL_NAAM + "." + AnimalTabel.ANIMAL_ID + " = ? "
                , new String[]{animal.getAnimalId()});
    }

    public boolean hasObject(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + OrganizationTabel.TABEL_NAAM + " WHERE " + OrganizationTabel.ORGANIZATION_ID + " = ?";

        Cursor cursor = db.rawQuery(selectString, new String[]{id});

        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
            }
            //here, count is records found
            Log.d("recordfound", String.format("%d records found", count));

            //endregion

        }

        cursor.close();
        db.close();
        return hasObject;
    }
}
