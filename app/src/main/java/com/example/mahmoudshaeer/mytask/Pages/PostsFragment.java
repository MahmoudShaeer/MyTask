package com.example.mahmoudshaeer.mytask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mahmoudshaeer.mytask.Adapters.PostAdapter;
import com.example.mahmoudshaeer.mytask.Database.DatabaseConnection;
import com.example.mahmoudshaeer.mytask.Models.PostInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mahmoud Shaeer on 4/15/2017.
 */

/***
 * this class related to fragment posts
 */
public class PostsFragment extends Fragment {
    ArrayList<PostInformation>allPosts;
    PostAdapter postAdapter;
    ListView allPostsAppearance;
    DatabaseConnection databaseConnection;
     String UserID="";
    @Nullable
    @Override
    /***
     * oncreated attach the layout and reading from JSON url
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_posts,container,false);
        allPosts=new ArrayList<PostInformation>();
        String url="https://jsonplaceholder.typicode.com/posts";
        databaseConnection=new DatabaseConnection(getActivity());
        allPostsAppearance=(ListView)view.findViewById(R.id.listPosts);
        //will fill from url
        new JSONAsynTask().execute(url);
        //get user id that sending from action log in
        Home home=(Home)getActivity();
        UserID=home.getUserID();

        return view;
    }
    //inner class which task i want to run in back ground
    class JSONAsynTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        @Override
        /***
         * this function check internet before do the task in background
         */
        protected void onPreExecute() {
            //any code do before task
            //josnUrl="https://jsonplaceholder.typicode.com/posts";
            super.onPreExecute();
            //if connect to server and any problem in the internet
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait Loading ...");
            progressDialog.setTitle("Connecting Server");
            progressDialog.show();
            // the user can not cancel it
            progressDialog.setCancelable(false);
        }

        /***
         *  in this function check if task in background is success or not
         * @param result return fro inbackground
         */
        @Override
        protected void onPostExecute(String result) {
            progressDialog.cancel();
            if(result=="Un Sucess")
            {
                Toast.makeText(getActivity(), "Unable to recive Data from server", Toast.LENGTH_LONG).show();
            }
        }


        /***
         *
         * @param params that send it to class from create view
         * @return all data in the URL as String
         */
        @Override
        protected String doInBackground(String... params) {
            String Data="";
            try {
                URL url=new URL(params[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=new BufferedInputStream( httpURLConnection.getInputStream());
                Data=StreamToString(inputStream);
                inputStream.close();
                //publish Data to onprogress found it
                publishProgress(Data);
                return "done";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Un Sucess";
        }

        /**
         * get the objects of array in JSON file and set it to adapter of list view
         * @param values
         */
        @Override
        protected void onProgressUpdate(String... values) {
            //can use interface
            super.onProgressUpdate(values);
            try {
                cropLargData(values[0]);
                postAdapter=new PostAdapter(getActivity(),R.layout.row_of_list_post,allPosts);
                allPostsAppearance.setAdapter(postAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /**
         * in this function convert stream to string
         * @param inputStream
         * @return Data From url
         */
        public String StreamToString(InputStream inputStream)
        {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String text="";
            try{
                while((line=bufferedReader.readLine())!=null){
                    text+=line;
                }
            }
            catch (Exception ex)
            {

            }
            return text;
        }

        /***
         * crop from Larg String to get Object of Post title , id , body
         * @param Data
         * @throws JSONException
         */
        public void cropLargData(String Data) throws JSONException {
            JSONArray arr = new JSONArray(Data);
            //loop through each object
            for (int eachPost=0; eachPost<arr.length(); eachPost++){

                JSONObject jsonProductObject = arr.getJSONObject(eachPost);

                String id = jsonProductObject.getString("id");
                String title = jsonProductObject.getString("title");
                //select some of posts by userid
                //String name=databaseConnection.retriveingName(Integer.valueOf(id));
                String body = jsonProductObject.getString("body");
                String Uid = jsonProductObject.getString("userId");
                //one this user Id Can see all thing
                if(Uid.equals(UserID))
                {
                    PostInformation postInformation=new PostInformation(id,title,body);
                    allPosts.add(postInformation);
                }
            }
        }
    }

}
