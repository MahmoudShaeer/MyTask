package com.example.mahmoudshaeer.mytask;

import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by mahmoud Shaeer on 4/15/2017.
 */

public class FriendsFragment extends Fragment {
    ArrayList<ContactsInformation>allContactsFriends;
    ListView listView;
    //AdapterFriend adapterFriend;
    @Nullable
    @Override
    /***
     * in this method catch view to layout
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_list_friends,container,false);
        listView=(ListView)view.findViewById(R.id.listFriends);
        // what happen if user click in ant row
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialg(view,getActivity().getFragmentManager());
            }
        });
        Home home=(Home)getActivity();
        allContactsFriends= home.getAllContactsInformations();
        listView.setAdapter(new AdapterFriend(getActivity(),allContactsFriends));
      //  Toast.makeText(getActivity(), "oncreateView calling", Toast.LENGTH_SHORT).show();
      return view;
    }

    /***
     *  send the friend name and phone number if the user click on the row
     * @param view as the row
     * @param fragmentManager how conytol to appear the dialog
     */
    public void showDialg(View view,FragmentManager fragmentManager)
    {
        TextView EmptyFriendName= (TextView) view.findViewById(R.id.FriendName);
        TextView EmptyFriendPhone= (TextView) view.findViewById(R.id.contactPhone);
        DialogFriend dialogFriend=new DialogFriend((String)EmptyFriendName.getText(),(String)EmptyFriendPhone.getText());
        dialogFriend.show(fragmentManager,"DialogFragment");
    }

}
