package com.example.mahmoudshaeer.mytask;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mahmoud Shaeer on 4/16/2017.
 */

/**
 * in this class i show the dialog and catch the attributes to valid view resouces of layout
 */
public class DialogFriend extends DialogFragment {

    TextView FriendName;
    TextView PhoneNumber;
    Button okey;
    String FirnedNameCome;
    String FirnedPhoneCome;

    /***
     * defult constractor
     */
    public DialogFriend() {
        }

    /***
     * constractor set name and hpone to set it in the text view that founded in the layout
     * @param firnedNameCome
     * @param firnedPhoneCome
     */
    public DialogFriend(String firnedNameCome, String firnedPhoneCome) {
        FirnedNameCome = firnedNameCome;
        FirnedPhoneCome = firnedPhoneCome;
    }

    @Nullable
    @Override
    /***
     * in this method catch textview to that valid view from layout
     * and define if user click in the button in dialg
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_friend,container,false);
        FriendName=(TextView)view.findViewById(R.id.friendName);
        PhoneNumber=(TextView)view.findViewById(R.id.phoneNmuber);
        okey=(Button)view.findViewById(R.id.ok);
        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        FriendName.setText(FirnedNameCome);
        PhoneNumber.setText(FirnedPhoneCome);
        return view;
    }
}
