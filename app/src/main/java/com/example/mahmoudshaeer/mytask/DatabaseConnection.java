package com.example.mahmoudshaeer.mytask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by mahmoud Shaeer on 4/15/2017.
 */

/***
 *  this class related database and all thing of database from this
 */
public class DatabaseConnection {
    UsersDataBase usersDataBase;
    Context context;

    /**
     * constractor try to make the object related to some activity
     * @param context
     */
    public DatabaseConnection(Context context) {
        usersDataBase=new UsersDataBase(context);
        this.context=context;
    }

    /***
     *  function take userInformation and insert in the data base
     * @param userInformation
     * @return return the id of iserted
     */

    public long insert(UserInformation userInformation)
    {
        SQLiteDatabase sqLiteDatabase=usersDataBase.getWritableDatabase();
        Toast.makeText(context,userInformation.getEmail(),Toast.LENGTH_LONG);
        ContentValues contentValues=new ContentValues();
        contentValues.put(UsersDataBase.firstName,userInformation.getFirstName());
        contentValues.put(UsersDataBase.lastName,userInformation.getLastName());
        contentValues.put(UsersDataBase.email,userInformation.getEmail());
        contentValues.put(UsersDataBase.gender,userInformation.getGender());
        contentValues.put(UsersDataBase.password,userInformation.getPassword());
        contentValues.put(UsersDataBase.mobile,userInformation.getMobile());
        long id=sqLiteDatabase.insert(UsersDataBase.tableName,null,contentValues);
        return id;
    }


    //search by email & password in the database

    /***
     *  when user log in i try to take his object with me if i want any thing from that
     * @param email
     * @param password
     * @return UserInformation
     */
    public UserInformation validationEmailAndPassword(String email, String password)
    {
        //intailze the varabile there not founded
        boolean correcrEmailAndPassword=false;
        SQLiteDatabase sqLiteDatabase=usersDataBase.getWritableDatabase();
        //get usesId to show the name of user
        String []coulmsIntersted={usersDataBase.userId,usersDataBase.firstName,usersDataBase.lastName,usersDataBase.email,
                usersDataBase.password,usersDataBase.gender,usersDataBase.mobile};
        Cursor cursor=sqLiteDatabase.query(usersDataBase.tableName,coulmsIntersted,usersDataBase.email+" = '" +email+ "'",null,null,null,null);
        StringBuffer stringBuffer=new StringBuffer();
        UserInformation userInformation=null;
        while(cursor.moveToNext())
        {
            int indexOfEmail=cursor.getColumnIndex(usersDataBase.email);
            int indexOfPassword=cursor.getColumnIndex(usersDataBase.password);

            String catchHisEmail=cursor.getString(indexOfEmail);
            String catchHisPasswrod=cursor.getString(indexOfPassword);

            if(catchHisEmail.equals(email)&&catchHisPasswrod.equals(password))
            {
                //found true vlaue in database
                int indexOfId=cursor.getColumnIndex(usersDataBase.userId);
                int indexOfFirstName=cursor.getColumnIndex(usersDataBase.firstName);
                int indexOfLastName=cursor.getColumnIndex(usersDataBase.lastName);
                int indexOfMobile=cursor.getColumnIndex(usersDataBase.mobile);
                int indexOfGender=cursor.getColumnIndex(usersDataBase.gender);
                //set all value in the string to set in the object return
                String userId=cursor.getString(indexOfId);
                String userEmail=cursor.getString(indexOfEmail);
                String userPassword=cursor.getString(indexOfPassword);
                String userFirstName=cursor.getString(indexOfFirstName);
                String userLastName=cursor.getString(indexOfLastName);
                String userMobile=cursor.getString(indexOfMobile);
                String userGender=cursor.getString(indexOfGender);
                userInformation=new UserInformation(userFirstName,userLastName,userEmail,userPassword,
                        userGender,userMobile);
                userInformation.setId(userId);
               break;
            }
        }
        return  userInformation;
    }

    /***
     * i use inner class this way for good security named encapculation outer calss & inner class
     */
    //make static class user can not use insert function in outerclass
    static class UsersDataBase extends SQLiteOpenHelper {

        private Context context;

        // i make all attributes is private to sure the outerclass only can used it more security ^_^
        private static final String databaseName = "Users";
        private static final int databaseVersion = 1;
        private static final String tableName = "userInformation";

        //all  information about user AS ACOULMN IN THE TABLE
        private static final String userId = "id";
        private static final String firstName = "firstName";
        private static final String lastName = "lastName";
        private static final String email = "email";
        private static final String password = "password";
        private static final String gender = "gender";
        private static final String mobile = "mobile";

        //create this string to use it in upgrade method
        private static final String dropTableQuary = "DROP TABLE IF EXISTS " + tableName;

        // this string is the query will create table and these coulm
        private static final String createTableQuary = "CREATE TABLE " + tableName +
                " ( " + userId + " INTEGER PRIMARY KEY AUTOINCREMENT , " + firstName + " VARCHAR(100) , " +
                lastName + " VARCHAR(100), " + email + " VARCHAR(100) , " + password + " VARCHAR(100) , " +
                gender + " VARCHAR(100), " + mobile + " VARCHAR(100) ) ; ";

        /**
         *   Constractor
         */
        public UsersDataBase(Context context) {
            super(context, databaseName, null, databaseVersion);
            this.context = context;
            //Toast.makeText(context, "calling constructor", Toast.LENGTH_LONG).show();
        }

        /**
         * create the data base do in the createTableQuary
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //make creation table
                db.execSQL(createTableQuary);
              //  Toast.makeText(context, "Created succss in (oncreate)", Toast.LENGTH_LONG).show();
            } catch (SQLiteException e) {
               // Toast.makeText(context, "Error while creating the table", Toast.LENGTH_LONG).show();
            }
        }

        /**
         * in this method checking if there are table has the same name drop it and create anther
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                //insure this table was not created before if found it drop it
                db.execSQL(dropTableQuary);
                //after drop create
                onCreate(db);
               // Toast.makeText(context, "Created succss in (onUpgrade)", Toast.LENGTH_LONG).show();
            } catch (SQLiteException e) {
                Toast.makeText(context, "Error while Droping and cretion the table", Toast.LENGTH_LONG).show();
            }
        }

    }
}