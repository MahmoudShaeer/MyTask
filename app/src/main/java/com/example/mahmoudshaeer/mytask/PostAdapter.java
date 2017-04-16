package com.example.mahmoudshaeer.mytask;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmoud Shaeer on 4/15/2017.
 */

/***
 * this adapter to control the list view of posts
 */
public class PostAdapter extends ArrayAdapter<PostInformation>{
    ArrayList <PostInformation>allPosts;
    LayoutInflater layoutInflater;
    int Resource;
    //Context context;

  //  ViewHolder holder;

    /***
     * constractor set attributes that i will used it
     * @param context
     * @param resource
     * @param objects
     */
    public PostAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<PostInformation> objects) {
        super(context, resource, objects);
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        allPosts=objects;
    }

    @NonNull
    @Override
    /***
     * in this method will set in each row the valid value from all posts i have each row has position
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row= layoutInflater.inflate(R.layout.row_of_list_post,parent,false);

        TextView title= (TextView) row.findViewById(R.id.Owntitle);
        TextView body=(TextView)row.findViewById(R.id.Ownbody);
        title.setText("title : " + allPosts.get(position).getTitle());
        body.setText("body : " + allPosts.get(position).getBody());

        return row;
    }
}
