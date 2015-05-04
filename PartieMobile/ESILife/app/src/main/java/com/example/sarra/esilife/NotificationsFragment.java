package com.example.sarra.esilife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sarra on 06/04/2015.
 */
public class NotificationsFragment extends Fragment {

    private View NotificationsFragmentView;

    public NotificationsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialiser les variables globales
    }

    @Override
    public void onResume() {
        super.onResume();

        // Stuff that needs views ...
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NotificationsFragmentView = inflater.inflate(R.layout.fragment_news_feed, container, false);
        return NotificationsFragmentView ;
    }


}
