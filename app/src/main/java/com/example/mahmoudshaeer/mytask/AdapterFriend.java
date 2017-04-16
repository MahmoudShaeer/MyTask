package com.example.mahmoudshaeer.mytask;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by mahmoud Shaeer on 4/15/2017.
 */

/**
 * this class adapter to control in list contact
 */

public class AdapterFriend extends BaseAdapter {

    ArrayList<ContactsInformation> AllcontactsInformation = new ArrayList<ContactsInformation>();
    Context context;
    //AllContacts allContacts;

    /**
     * contractor with param set some attribute in its params  
     * @param context
     * @param AllcontactsInformation
     */
    public AdapterFriend(@NonNull Context context, ArrayList<ContactsInformation> AllcontactsInformation) {
        this.AllcontactsInformation = AllcontactsInformation;
        this.context = context;
    }


    @Override
    public int getCount() {
        return AllcontactsInformation.size();
    }

    @Override
    public Object getItem(int position) {
        return AllcontactsInformation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_list_friends, parent, false);
        TextView friendName = (TextView) row.findViewById(R.id.FriendName);
        TextView phoneNumber = (TextView) row.findViewById(R.id.contactPhone);

        ImageView calling = (ImageView) row.findViewById(R.id.callFriend);
        final String phoneNumberCall = AllcontactsInformation.get(position).getPhoneNumber();
        calling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to make call phone
                // Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();

                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumberCall));
                context.startActivity(intent);
            }
        });
        phoneNumber.setText(phoneNumberCall);
        friendName.setText(AllcontactsInformation.get(position).getContactsName());
        return row;
    }



}
