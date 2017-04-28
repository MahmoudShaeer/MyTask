package com.example.mahmoudshaeer.mytask;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
     *
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

    class ViewHolder {
        ImageView calling;
        TextView friendName, phoneNumber;

        ViewHolder(View view) {
            calling = (ImageView) view.findViewById(R.id.callFriend);
            friendName = (TextView) view.findViewById(R.id.FriendName);
            phoneNumber = (TextView) view.findViewById(R.id.contactPhone);
        }
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_list_friends, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        final String phoneNumberCall = AllcontactsInformation.get(position).getPhoneNumber();
        holder.calling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to make call phone
                // Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();

                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumberCall));
                context.startActivity(intent);
            }
        });
        holder.phoneNumber.setText(phoneNumberCall);
        holder.friendName.setText(AllcontactsInformation.get(position).getContactsName());
        return row;
    }


}