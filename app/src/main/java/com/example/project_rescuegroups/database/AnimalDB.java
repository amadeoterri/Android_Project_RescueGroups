package com.example.project_rescuegroups.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.util.ChoiceParamData;

public class AnimalDB extends SchemaHelper {

    public AnimalDB(Context context) {
        super(context);
    }

    public long addAnimals(Animal animal) {
        if (!(hasObject(animal.getAnimalId()))) {

            ContentValues cv = new ContentValues();
            cv.put(AnimalTabel.ANIMAL_ID, animal.getAnimalId());
            cv.put(AnimalTabel.ANIMAL_NAME, animal.getAnimalName());
            cv.put(AnimalTabel.ANIMAL_SPECIES, animal.getAnimalSpecies());
            cv.put(AnimalTabel.ANIMAL_BREED, animal.getAnimalBreed());
            cv.put(AnimalTabel.ANIMAL_SEX, animal.getAnimalSex());
            cv.put(AnimalTabel.ANIMAL_BIRTHDATE, animal.getAnimalBirthDate());
            cv.put(AnimalTabel.ANIMAL_IMAGE, animal.getAnimalImageUrl());
            cv.put(AnimalTabel.ANIMAL_DESC, animal.getAnimalDescription());
            cv.put(AnimalTabel.ANIMAL_ORGID, animal.getAnimalOrgID());

            SQLiteDatabase sd = getWritableDatabase();

            return sd.insert(AnimalTabel.TABEL_NAAM, AnimalTabel.ANIMAL_NAME, cv);
        } else {
            return 0;
        }
    }

    public Cursor getAllAnimals() {
        SQLiteDatabase sd = getWritableDatabase();
        return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM, null);
    }

    public Cursor getAnimalsWithParams(ChoiceParamData choice) {
        SQLiteDatabase sd = getWritableDatabase();
        String species = choice.getsChoiceSpecies();
        String breed = choice.getsChoiceBreed();
        String sex = choice.getsChoiceSex();
        String state = choice.getsChoiceState();

        if (!state.equals("All")) {
            state = state.substring(state.length() - 2);
        }

        //ALL/ALL/ALL
        if (breed.equals("All") && sex.equals("All") && state.equals("All")) {
            return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM
                            + " WHERE " + AnimalTabel.ANIMAL_SPECIES + " =  ? "
                            + "GROUP BY " + AnimalTabel.ANIMAL_NAME
                    , new String[]{species});
        }
        //ALL/X/ALL
        else if (breed.equals("All") && !sex.equals("All") && state.equals("All")) {
            return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM
                            + " WHERE " + AnimalTabel.ANIMAL_SPECIES + " = ? "
                            + " AND " + AnimalTabel.ANIMAL_SEX + " = ? "
                            + "GROUP BY " + AnimalTabel.ANIMAL_NAME
                    , new String[]{species, sex});
        }
        //ALL/ALL/X
        else if (breed.equals("All") && sex.equals("All") && !state.equals("All")) {
            return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM + ", " + OrganizationTabel.TABEL_NAAM
                    + " WHERE " + AnimalTabel.ANIMAL_SPECIES + " =  ? "
                    + " AND " + AnimalTabel.ANIMAL_ORGID + " = " + OrganizationTabel.ORGANIZATION_ID
                    + " AND " + OrganizationTabel.ORGANIZATION_STATE + " = ? " + " GROUP BY " + AnimalTabel.ANIMAL_NAME, new String[]{species, state});
        }
        //X/ALL/ALL
        else if (!breed.equals("All") && sex.equals("All") && state.equals("All")) {
            return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM
                            + " WHERE " + AnimalTabel.ANIMAL_SPECIES + " =  ? "
                            + " AND " + AnimalTabel.ANIMAL_BREED + " LIKE ? "
                            + "GROUP BY " + AnimalTabel.ANIMAL_NAME
                    , new String[]{species, "%" + breed + "%"});
        }
        //X/X/ALL
        else if (!breed.equals("All") && !sex.equals("All") && state.equals("All")) {
            return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM
                            + " WHERE " + AnimalTabel.ANIMAL_SPECIES + " =  ? "
                            + " AND " + AnimalTabel.ANIMAL_BREED + " LIKE ? "
                            + " AND " + AnimalTabel.ANIMAL_SEX + " = ? "
                            + "GROUP BY " + AnimalTabel.ANIMAL_NAME
                    , new String[]{species, "%" + breed + "%", sex});
        }
        //X/ALL/X
        else if (!breed.equals("All") && sex.equals("All") && !state.equals("All")) {
            return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM + ", " + OrganizationTabel.TABEL_NAAM
                    + " WHERE " + AnimalTabel.ANIMAL_SPECIES + " =  ? "
                    + " AND " + AnimalTabel.ANIMAL_BREED + " LIKE ? "
                    + " AND " + AnimalTabel.ANIMAL_ORGID + " = " + OrganizationTabel.ORGANIZATION_ID
                    + " AND " + OrganizationTabel.ORGANIZATION_STATE + " = ? " + " GROUP BY " + AnimalTabel.ANIMAL_NAME, new String[]{species, "%" + breed + "%", state});
        }
        //ALL/X/X
        else if (breed.equals("All") && !sex.equals("All") && !state.equals("All")) {
            return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM + ", " + OrganizationTabel.TABEL_NAAM
                    + " WHERE " + AnimalTabel.ANIMAL_SPECIES + " =  ? "
                    + " AND " + AnimalTabel.ANIMAL_SEX + " = ? "
                    + " AND " + AnimalTabel.ANIMAL_ORGID + " = " + OrganizationTabel.ORGANIZATION_ID
                    + " AND " + OrganizationTabel.ORGANIZATION_STATE + " = ? " + " GROUP BY " + AnimalTabel.ANIMAL_NAME, new String[]{species, sex, state});
        }
        //X/X/X
        else if (!breed.equals("All") && !sex.equals("All") && !state.equals("All")) {
            return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM + ", " + OrganizationTabel.TABEL_NAAM
                    + " WHERE " + AnimalTabel.ANIMAL_SPECIES + " =  ? "
                    + " AND " + AnimalTabel.ANIMAL_SEX + " = ? "
                    + " AND " + AnimalTabel.ANIMAL_BREED + " LIKE ? "
                    + " AND " + AnimalTabel.ANIMAL_ORGID + " = " + OrganizationTabel.ORGANIZATION_ID
                    + " AND " + OrganizationTabel.ORGANIZATION_STATE + " = ? " + " GROUP BY " + AnimalTabel.ANIMAL_NAME, new String[]{species, sex, "%" + breed + "%", state});
        } else {
            return getAllAnimals();
        }
    }

    public Cursor getAnimalsUniqueBreed(String species) {
        SQLiteDatabase sd = getReadableDatabase();
        String whereClause = AnimalTabel.ANIMAL_SPECIES + " = ?";
        String[] whereArgs = new String[]{species};

        return sd.query(true, AnimalTabel.TABEL_NAAM, new String[]{AnimalTabel.ANIMAL_BREED}, whereClause, whereArgs, AnimalTabel.ANIMAL_BREED, null, null, null);
    }

    public boolean hasObject(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + AnimalTabel.TABEL_NAAM + " WHERE " + AnimalTabel.ANIMAL_ID + " =?";

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
