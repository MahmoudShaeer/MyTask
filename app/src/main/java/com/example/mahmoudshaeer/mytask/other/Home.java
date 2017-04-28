package com.example.mahmoudshaeer.mytask;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mahmoudshaeer.mytask.Adapters.HomeAdapter;
import com.example.mahmoudshaeer.mytask.Models.ContactsInformation;

import java.util.ArrayList;

/***
 * class which catch the secound view to user who login success
 */
public class Home extends AppCompatActivity {

    String userId="";
    String userMobile="";
    ArrayList<ContactsInformation>AllContactsInformations;
    ViewPager viewPager;
    PagerTabStrip pagerTabStrip;

    /***
     *  in oncreate catch each parameter of attribute in the calss to view in layout of home
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=getIntent();
        String fullName="";
        AllContactsInformations=new ArrayList<ContactsInformation>();
        //just sure to me the intent sended
        //recieve that attributes which i send it when login passed which i need it in anther class
        if(intent!=null)
        {
            fullName+=intent.getExtras().getString("FirstName")+" "+
                    intent.getExtras().getString("LastName");
            userId=intent.getExtras().getString("UserId");
            userMobile=intent.getExtras().getString("Mobile");
        }
        //make this for support android 6 -SDK above 23-
        if(isGoToContactss(this))
        {
            //calling function that get all number in my device to put it in my array list of Contacts
            AllContactsInformations=getNumbers();
        }
        else {
            isGoToContactss(this);
        }

        viewPager= (ViewPager) findViewById(R.id.viewPager);
        pagerTabStrip=(PagerTabStrip)findViewById(R.id.pagerTabStrip);
        HomeAdapter homeAdapter=new HomeAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(homeAdapter);
        viewPager.setCurrentItem(0);
    }

    /***
     * @return id
     */
    public String getUserID()
    {
        return userId;
    }

    /***
     *
     * @return ArrayList<ContactsInformation> these all numbers and phone in my device
     */
    public ArrayList<ContactsInformation> getNumbers()
    {
        ContentResolver cr=this.getContentResolver();
        ArrayList<ContactsInformation>allContactsInformations=new ArrayList<ContactsInformation>();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            //loping in each contact
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactsInformation contactsInformation=new ContactsInformation(phoneNumber,name);
            allContactsInformations.add(contactsInformation);
        }
        phones.close();
       // Toast.makeText(this, "size of Contacts is"+allContactsInformations.size(), Toast.LENGTH_SHORT).show();
        return allContactsInformations;
    }


    /***
     * @return ArrayList<ContactsInformation>
     */
    public ArrayList<ContactsInformation> getAllContactsInformations() {
        return AllContactsInformations;
    }

    //to test my app in android 6

    /***
     *  sure perrmission for device has sdk above 23 like android 6
     * @param context
     * @return boolean the operation sucsseful
     */
    public static boolean isGoToContactss(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(android.Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED) {
                // Log.v(TAG,"Permission is granted");
                return true;
            } else {

                // Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_CONTACTS}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            // Log.v(TAG,"Permission is granted");
            return true;
        }
    }
}
