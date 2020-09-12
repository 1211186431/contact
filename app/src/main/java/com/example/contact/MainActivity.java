package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.time.DayOfWeek;

public class MainActivity extends AppCompatActivity {
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver = this.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String a="";
        while (cursor.moveToNext()) {
            String msg="";

            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            msg = "id:" + id;
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            msg = msg + " name:" + name;
            Cursor phoneNumbers = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
            while (phoneNumbers.moveToNext()) {
                String phoneNumber = phoneNumbers.getString(phoneNumbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                msg = msg + " phone:" + phoneNumber;
            }
            a=a+msg+" \n";
        }
        TextView t=findViewById(R.id.contactsView);
        t.setText(a);

    }

}
