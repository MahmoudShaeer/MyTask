package com.example.mahmoudshaeer.mytask;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    TextView title;
    TextView body;
    //Context context;



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
    class ViewHolder {
        TextView title, body;

        ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.Owntitle);
            body = (TextView) view.findViewById(R.id.Ownbody);
        }
    }
    @NonNull
    @Override
    /***
     * in this method will set in each row the valid value from all posts i have each row has position
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row= convertView;
        ViewHolder holder=null;
        if(row==null)
        {
            row = layoutInflater.inflate(R.layout.row_of_list_post, parent, false);
            holder=new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
          //  row.setTag(holder);
            holder= (ViewHolder) row.getTag();
        }
        holder.title.setText("title : " + allPosts.get(position).getTitle());
        holder.body.setText("body : " + allPosts.get(position).getBody());

        return row;
    }
}
