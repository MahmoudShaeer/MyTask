package com.example.mahmoudshaeer.mytask;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmoudshaeer.mytask.Database.DatabaseConnection;
import com.example.mahmoudshaeer.mytask.Models.UserInformation;

/****
 * this the first Class will called
 *          attributes : email , password,btnRegistration,btnLogin,databaseConnection
 * in this class checking insert email and password to login in
 *
 */
public class LogIn extends AppCompatActivity {
    EditText email;
    EditText password;
    Button btnRegistration;
    Button btnLogin;
    DatabaseConnection databaseConnection;

    /***
     * checking on the Edite text and if empty or not do not return any thing
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email=(EditText)findViewById(R.id.AccountEmail);
        password=(EditText)findViewById(R.id.Accountpassword);

        Intent intent=getIntent();
        //check bcs the first run the intent will be null
        if(intent!=null)
        {
            email.setText(intent.getStringExtra("email"));
            password.setText(intent.getStringExtra("password"));
        }
        else
        {
            //check validation of fields
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

        }
        btnRegistration= (Button) findViewById(R.id.btnRegistration);
        btnLogin =(Button)findViewById(R.id.btnLogin);
        databaseConnection=new DatabaseConnection(this);
    }

    /***
     *  this function will go to registration page if the user press on button registration
     * @param view
     */

    public void RegistrationPage(View view) {
        // moving to Registration page
        Intent intent=new Intent(this,registration.class);
        startActivity(intent);
    }

    /***
     *  this Function when user press the login button
     *      1-check the field is empty pof not
     *      2- go to database to see all Accouts and check if it is valid or no
     *      3- go to Home page that have posts and Friends
     * @param view
     */
    public  void CheckValid(View view)
    {
        String txtEmail=email.getText().toString();
        String txtPassword=password.getText().toString();
        //to sure the edite text Not empty
      if(txtEmail.isEmpty()||txtPassword.isEmpty())
      {
          Toast.makeText(this, "Please check Email and Password again", Toast.LENGTH_SHORT).show();
      }
      else {
            //get all informtation about this user from data base and send it to anther acitvity
          UserInformation userInformationEnterApp = databaseConnection.validationEmailAndPassword(txtEmail, txtPassword);
          if (userInformationEnterApp != null) {
              GoToHome(view,userInformationEnterApp);
          } else {
              Toast.makeText(this, "Please Registration ...", Toast.LENGTH_SHORT).show();
          }
      }
    }

    /***
     * this fiction that will go to Home Carry Some Information i will used it in there classes
     *
     * @param view
     * @param userInformationEnterApp
     */
    public void GoToHome(View view,UserInformation userInformationEnterApp)
    {
        Intent intent=new Intent(this,Home.class);
        //i want his name and id to view these post depent on userid in JSON
       // Toast.makeText(this, userInformationEnterApp.getFirstName(), Toast.LENGTH_SHORT).show();
        intent.putExtra("FirstName",userInformationEnterApp.getFirstName());
       // Toast.makeText(this, userInformationEnterApp.getLastName(), Toast.LENGTH_SHORT).show();
        intent.putExtra("LastName",userInformationEnterApp.getLastName());
       // intent.putExtra("UserId",userInformationEnterApp.getId());
        intent.putExtra("UserId","1");
       // Toast.makeText(this, userInformationEnterApp.getId(), Toast.LENGTH_SHORT).show();

        intent.putExtra("Mobile",userInformationEnterApp.getMobile());
        //Toast.makeText(this, userInformationEnterApp.getMobile(), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }

}
