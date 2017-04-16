package com.example.mahmoudshaeer.mytask;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


/***
 * this class has Attributes all information about any user like
 *      FirstName , LastName, Password , email ...
 *      insert all this in the database each user will inserted will have i unique id
 */
public class registration extends AppCompatActivity {

    // Attribues in my class to catch Views in my layout
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    EditText confirmPassword;
    EditText mobile;
    RadioButton male;
    RadioButton feMale;

    UserInformation userInformation;
    DatabaseConnection databaseConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // to appear button back in the action bar if the user want go to login page
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // each attributes refer to view in the layout
        firstName=(EditText)findViewById(R.id.Etxt_FirstName);
        //make sure the user write in this filed
        firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(firstName.getText().length()<3)
                {
                    //the message will appear if the user write first name less than 2 char
                    firstName.setError("name more than 3 char");
                }
                else
                    {
                        // if the First name is correct show icone correct
                        Drawable CorrectFirstName= getResources().getDrawable(R.drawable.checked);
                        CorrectFirstName.setBounds(0, 0,
                                CorrectFirstName.getIntrinsicWidth(), CorrectFirstName.getIntrinsicHeight());
                        firstName.setError("Valid",CorrectFirstName);
                    }
            }
        });
        //make last thing for each Edit text and all fields the user will interact with that
        //last name
        lastName=(EditText)findViewById(R.id.Etxt_LastName);
        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(lastName.getText().length()<3)
                {
                    lastName.setError("name more than 3 char");
                }
                else
                {
                    Drawable CorrectFirstName= getResources().getDrawable(R.drawable.checked);
                    CorrectFirstName.setBounds(0, 0,
                            CorrectFirstName.getIntrinsicWidth(), CorrectFirstName.getIntrinsicHeight());
                    lastName.setError("Valid",CorrectFirstName);
                }
            }
        });


        //Email
        email=(EditText)findViewById(R.id.Etxt_Email) ;
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!String.valueOf(email.getText()).contains("@"))
                {
                    email.setError("write correct Email");
                }
                else
                {
                    Drawable CorrectFirstName= getResources().getDrawable(R.drawable.checked);
                    CorrectFirstName.setBounds(0, 0,
                            CorrectFirstName.getIntrinsicWidth(), CorrectFirstName.getIntrinsicHeight());
                    email.setError("Valid",CorrectFirstName);
                }
            }
        });

        //password
        password=(EditText)findViewById(R.id.Etxt_Password);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(password.getText().length()<5)
                {
                    password.setError("password more than 5 char");
                }
                else
                {
                    Drawable CorrectFirstName= getResources().getDrawable(R.drawable.checked);
                    CorrectFirstName.setBounds(0, 0,
                            CorrectFirstName.getIntrinsicWidth(), CorrectFirstName.getIntrinsicHeight());

                    password.setError("Valid",CorrectFirstName);
                }
            }
        });

        //confirm password
        confirmPassword=(EditText)findViewById(R.id.Etxt_ConfirmPassword);
        confirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(confirmPassword.getText().length()<5)
                {
                    confirmPassword.setError("password more than 5 char");
                }
                else if ( !String.valueOf(confirmPassword.getText()).equals(String.valueOf(password.getText())))
                {
                    confirmPassword.setError("Confirm password should match password");
                }
                else
                {
                    Drawable CorrectFirstName= getResources().getDrawable(R.drawable.checked);
                    CorrectFirstName.setBounds(0, 0,
                            CorrectFirstName.getIntrinsicWidth(), CorrectFirstName.getIntrinsicHeight());
                    confirmPassword.setError("Valid",CorrectFirstName);
                }
            }
        });

        //mobile
        mobile=(EditText)findViewById(R.id.Etxt_Mobile);
        mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(mobile.getText().length()<11)
                {
                    mobile.setError("Mobile more than 11 number");
                }
                else
                {
                    Drawable CorrectFirstName= getResources().getDrawable(R.drawable.checked);
                    CorrectFirstName.setBounds(0, 0,
                            CorrectFirstName.getIntrinsicWidth(), CorrectFirstName.getIntrinsicHeight());
                    mobile.setError("Valid",CorrectFirstName);
                }
            }
        });
        male=(RadioButton)findViewById(R.id.Rbtn_male) ;
        feMale=(RadioButton)findViewById(R.id.Rbtn_female);

        //crete the data base that found in usersDataBase
        databaseConnection=new DatabaseConnection(this);

        //get result of Gender from radio button and put the value in the constractor of UserInformation
        String resultOfGender="";
            resultOfGender=CheckGender(male);
            if(!resultOfGender.equals("")) {
                //set all data in the object user
                userInformation = new UserInformation(firstName.getText().toString(),
                        lastName.getText().toString(),email.getText().toString(),
                        password.getText().toString(),"male", mobile.getText().toString());
            }
            else
            {
              resultOfGender=CheckGender(feMale);
                userInformation = new UserInformation(firstName.getText().toString(),
                        lastName.getText().toString(),email.getText().toString(),
                       password.getText().toString(),resultOfGender, mobile.getText().toString());

            }

    }

    /***
     * this Function related to appear the button back in the action bar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //related to button back in action bar
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    /***
     * this function to know the user choice any option male or female
     * @param view
     * @return String male or female
     */

    // this function return Gender of user
    public String CheckGender(View view)
    {
        String genderChecked="";
        boolean checked=((RadioButton)view).isChecked();
        switch (view.getId()) {
            case R.id.Rbtn_male:
                //should to see if this radio button already checked to set the value
                if(checked)
                genderChecked="male";
                break;
            case R.id.Rbtn_female:
                if(checked)
                    genderChecked="female";
        }
        Toast.makeText(this,genderChecked , Toast.LENGTH_LONG).show();
        return genderChecked;
    }

    /***
     * this Funtion is called checkall fields in not empty and go to
     *              data base to insert it after Passes Go to Login Page found his
     *              email & password is writen in Edit text
     * @param view
     */
    public void registration(View view)
    {
        //check all fields are empty or have value from user
        if(password.getText().toString().isEmpty() ||
                email.getText().toString().isEmpty()||
                firstName.getText().toString().isEmpty()||
                lastName.getText().toString().isEmpty()||
                mobile.getText().toString().isEmpty()||
                confirmPassword.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Can not insert please fill all fields", Toast.LENGTH_LONG).show();
        }
        else {
            //function put all information in user
            putInformationForUser();
            //ready to insert the data
            long id = databaseConnection.insert(userInformation);
            if (id < 0) {
                Toast.makeText(this, "Un success , ! Not inserted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "successfully inserted", Toast.LENGTH_LONG).show();
                //calling for method that go Login and put it is parameters
                GotoLoginAfterRegistration(view,userInformation.getEmail(),userInformation.getPassword());
            }
        }
    }

    /***
     *  tis function go to Login Page carry Email & password to put it in Edit text
     * @param view
     * @param email
     * @param password
     */
    public  void GotoLoginAfterRegistration(View view,String email,String password)
    {
        Intent intent=new Intent(this,LogIn.class);
        //put email in the intent to put it in login automatic
        intent.putExtra("email",email);
     //   Toast.makeText(this,"this email that send "+ email, Toast.LENGTH_LONG).show();
        intent.putExtra("password",password);
        startActivity(intent);
    }

    /***
     * this function get Gender as String from CheckGender passes any view and get that return
     */
    public void putInformationForUser()
    {
        //get result of Gender from radio button and put the value in the constractor of UserInformation
        String resultOfGender="";
        resultOfGender=CheckGender(male);
        if(!resultOfGender.equals("")) {
            //set all data in the object user
            userInformation = new UserInformation(firstName.getText().toString(),
                    lastName.getText().toString(),email.getText().toString(),
                    password.getText().toString(),"male", mobile.getText().toString());
        }
        else
        {
            resultOfGender=CheckGender(feMale);
            userInformation = new UserInformation(firstName.getText().toString(),
                    lastName.getText().toString(),email.getText().toString(),
                    password.getText().toString(),resultOfGender, mobile.getText().toString());
        }
    }
}
