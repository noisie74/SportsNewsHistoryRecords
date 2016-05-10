package com.mikhail.sportsnewshistoryrecords.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;

/**
 * Created by Mikhail on 5/10/16.
 */
public class NotificationFragment extends Fragment {

    View v;
    CheckBox topNews, football, basketball, baseball, hockey, soccer;
    NewsDetailsFragment.ControlToolbar controlToolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.notifications_layout, container, false);

        topNews = (CheckBox) v.findViewById(R.id.checkbox_top_news);
        football = (CheckBox) v.findViewById(R.id.checkbox_football);
        basketball = (CheckBox) v.findViewById(R.id.checkbox_basketball);
        baseball = (CheckBox) v.findViewById(R.id.checkbox_baseball);
        hockey = (CheckBox) v.findViewById(R.id.checkbox_hockey);
        soccer = (CheckBox) v.findViewById(R.id.checkbox_soccer);

        controlToolbar.showSpinner(false);

        return v;

    }

    private void onCheckboxClicked(final View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) view).isChecked();
                switch (view.getId()) {
                    case R.id.checkbox_top_news:
                        int NOTIFICATION_ID1 = 1;
                        if (checked) {

                        } else {

                        }
                        break;
                    case R.id.checkbox_football:
                        int NOTIFICATION_ID2 = 2;
                        if (checked) {

                        } else {

                        }
                        break;
                    case R.id.checkbox_basketball:
                        int NOTIFICATION_ID3 = 3;
                        if (checked) {

                        } else {

                        }
                        break;
                    case R.id.checkbox_baseball:
                        int NOTIFICATION_ID4 = 4;
                        if (checked) {

                        } else {

                        }
                        break;
                    case R.id.checkbox_hockey:
                        int NOTIFICATION_ID5 = 5;
                        if (checked) {

                        } else {

                        }
                        break;
                    case R.id.checkbox_soccer:
                        int NOTIFICATION_ID6 = 6;
                        if (checked) {

                        } else {

                        }
                        break;
                    default:
                }

            }
        });
    }
}
