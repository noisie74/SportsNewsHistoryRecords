package com.mikhail.sportsnewshistoryrecords.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.interfaces.ControlToolbar;


/**
 * Created by Mikhail on 5/11/16.
 */
public class AboutFragment extends Fragment {

    protected ImageView appImage;
    protected TextView appName, appVersion;
    Button feedback;
    protected View v;
    ControlToolbar controlToolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.about_layout, container, false);
        appImage = (ImageView) v.findViewById(R.id.imageAbout);
        appName = (TextView) v.findViewById(R.id.app_name);
        appVersion = (TextView) v.findViewById(R.id.app_version);
        feedback = (Button) v.findViewById(R.id.button_feedback);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        controlToolbar.showSpinner(false);
        controlToolbar.setTitle("About");

        hasOptionsMenu();
        setClickListener();

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            controlToolbar = (ControlToolbar) getActivity();
        } catch (ClassCastException ex) {
            throw new ClassCastException();
        }
    }

    private void setClickListener() {
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });
    }

    private void sendFeedback() {
        final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{getString(R.string.mail_feedback_email)});
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
        intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
        startActivity(Intent.createChooser(intent, getString(R.string.title_send_feedback)));
    }

}
