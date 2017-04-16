package com.example.mahmoudshaeer.mytask;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by mahmoud Shaeer on 4/15/2017.
 */

/***
 * class control in home page and wich fragment will appear and
 */
public class HomeAdapter extends FragmentPagerAdapter {

    Context context;
    //intialize all icone i need it
   // private final String[] titles= new String[]{"Home","Friends"};
    private final int[] titles = {
            R.drawable.home,
            R.drawable.friends,
    };

    private final Fragment [] allPagesFragment= new Fragment[titles.length];

    /***
     * constractor with take parametatrs
     * @param my_context
     * @param fm to show dailog i want fragment maeger
     */
    public HomeAdapter(Context my_context ,FragmentManager fm) {
        super(fm);
        //make first page for posts ^_^
        allPagesFragment[0]=new PostsFragment();
        //make secound page for Friends list
        allPagesFragment[1]=new FriendsFragment();
        context=my_context;
    }

    /***
     *
     * @param position
     * @return fragment of this position
     */

    @Override
    public Fragment getItem(int position) {
        return allPagesFragment[position];
    }

    /***
     *
     * @return size of all pages will created in home page
     */
    @Override
    public int getCount() {
        return allPagesFragment.length;
    }

    /***
     *
     * @param position
     * @return chape of title tab when drag screen -shape title-
     */
    @Override
    public CharSequence getPageTitle(int position) {

        Drawable image =context.getResources().getDrawable(titles[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;

    }
}
