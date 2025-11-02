package com.resqher.safety.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resqher.safety.models.EmergencyContact;

import java.util.List;

@Dao
public interface EmergencyContactDao {

    @Insert
    void insert(EmergencyContact contact);

    @Update
    void update(EmergencyContact contact);

    @Delete
    void delete(EmergencyContact contact);

    @Query("SELECT * FROM emergency_contacts ORDER BY isPrimary DESC, name ASC")
    LiveData<List<EmergencyContact>> getAllContacts();

    @Query("SELECT * FROM emergency_contacts WHERE isPrimary = 1")
    List<EmergencyContact> getPrimaryContacts();

    @Query("DELETE FROM emergency_contacts")
    void deleteAll();
}
