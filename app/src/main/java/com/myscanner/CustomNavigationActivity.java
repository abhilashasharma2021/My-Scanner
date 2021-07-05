package com.myscanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.myscanner.fragment.FeedFragment;
import com.myscanner.fragment.MessagesFragment;
import com.myscanner.fragment.MusicFragment;
import com.myscanner.fragment.NewsFragment;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class CustomNavigationActivity extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_navigation);

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<com.shrikanthravi.customnavigationdrawer2.data.MenuItem> menuItems = new ArrayList<>();


        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("News", R.drawable.news_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Feed", R.drawable.feed_bg));


        //then add them to navigation drawer

        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("News", R.drawable.news_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Feed", R.drawable.feed_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Messages", R.drawable.message_bg));
        menuItems.add(new MenuItem("Music", R.drawable.music_bg));



        //then add them to navigation drawer

        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass = NewsFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }


        //Listener to handle the menu item click. It returns the position of the menu item clicked. Based on that you can switch between the fragments.

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position " + position);

                switch (position) {
                    case 0: {
                        fragmentClass = NewsFragment.class;
                        break;
                    }
                    case 1: {
                        fragmentClass = FeedFragment.class;
                        break;
                    }
                    case 2: {
                        fragmentClass = MessagesFragment.class;
                        break;
                    }
                    case 3: {
                        fragmentClass = MusicFragment.class;
                        break;
                    }

                }

                //Listener for drawer events such as opening and closing.
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening() {

                    }

                    @Override
                    public void onDrawerClosing() {
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State " + newState);
                    }
                });
            }
        });
    }
}