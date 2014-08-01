package project.banmua.com.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import project.banmua.com.R;

/**
 * Created by Dat on 8/1/2014.
 */
public class ActivityTabHost extends TabActivity {

    private static final int HOME = 0;
    private static final int BOOKMARK = 2;
    private static final int NOTIFICATION = 2;
    private static final int SETTINGS = 3;

    private TabHost tabHost;
    private TabWidget tabWidget;
    private TabHost.OnTabChangeListener onOpenTabListener = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String s) {
            for (int i = 0, n = tabHost.getTabWidget().getChildCount(); i < n; i++) {
                tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#c4ebdc"));
                TextView textView = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tabsText);
                textView.setTextColor(Color.BLACK);
                icon = (ImageView) tabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tabsImg);
                if (i == HOME) {
                    icon.setImageResource(R.drawable.ic_news_2x);
                } else if (i == BOOKMARK) {
                    icon.setImageResource(R.drawable.ic_favorite_2x);
                } else if (i == NOTIFICATION) {
                    icon.setImageResource(R.drawable.ic_information_2x);
                } else {
                    icon.setImageResource(R.drawable.ic_setting_2x);
                }
            }
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#5f4434"));
            TextView textView = (TextView) tabHost.getCurrentTabView().findViewById(R.id.tabsText);
            textView.setTextColor(Color.WHITE);
            icon = (ImageView) tabHost.getCurrentTabView().findViewById(R.id.tabsImg);
            if (tabHost.getCurrentTab() == HOME) {
                icon.setImageResource(R.drawable.ic_news_selected_2x);
            } else if (tabHost.getCurrentTab() == BOOKMARK) {
                icon.setImageResource(R.drawable.ic_favorite_selected_2x);
            } else if (tabHost.getCurrentTab() == NOTIFICATION) {
                icon.setImageResource(R.drawable.ic_information_selected_2x);
            } else {
                icon.setImageResource(R.drawable.ic_setting_selected_2x);
            }
        }
    };
    private TextView title;
    private ImageView icon;
    private SharedPreferences sharedPreferences;
    private BroadCastBackStack broadCastBackStack;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources(); // Resource object to get Drawables
        tabHost = getTabHost(); // The activity TabHost
        tabWidget = tabHost.getTabWidget();
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        addTab("Home", R.drawable.ic_news_2x, HomeActivity.class);
        addTab("Bookmark", R.drawable.ic_favorite_2x, BookMarkActivity.class);
        addTab("Information", R.drawable.ic_information_2x, InformationActivity.class);
        addTab("Setting", R.drawable.ic_setting_2x, SettingActivity.class);

        tabHost.getTabWidget().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        tabWidget.getChildAt(HOME).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (HomeGroupActivity.HomeGroupStack != null && HomeGroupActivity.HomeGroupStack.mIdList.size() > 1) {
//                    HomeGroupActivity.HomeGroupStack.getLocalActivityManager().removeAllActivities();
//                    HomeGroupActivity.HomeGroupStack.mIdList.clear();
//                    HomeGroupActivity.HomeGroupStack.mIntents.clear();
//                    HomeGroupActivity.HomeGroupStack.mAnimator.removeAllViews();
//                    HomeGroupActivity.HomeGroupStack.startChildActivity("CareGroupActivity", new Intent(HomeGroupActivity.HomeGroupStack, HomeActivity.class));
//
//                }
//
//                tabWidget.setCurrentTab(HOME);
//                tabHost.setCurrentTab(HOME);
//            }
//        });

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#c4ebdc"));
        }
        sharedPreferences = getSharedPreferences(SplashScreenActivity.NAME_FILE, MODE_PRIVATE);
        boolean checkLogin = sharedPreferences.getBoolean("check_login", false);
        if (checkLogin) {
            tabHost.setCurrentTab(SETTINGS);
            tabHost.getTabWidget().getChildAt(SETTINGS).setBackgroundColor(Color.parseColor("#5f4434"));
        } else {
            tabHost.setCurrentTab(HOME);
            tabHost.getTabWidget().getChildAt(HOME).setBackgroundColor(Color.parseColor("#5f4434"));
        }


        title = (TextView) tabHost.getCurrentTabView().findViewById(R.id.tabsText);
        title.setTextColor(Color.WHITE);
        icon = (ImageView) tabHost.getCurrentTabView().findViewById(R.id.tabsImg);
        icon.setImageResource(R.drawable.ic_news_selected_2x);
        tabHost.setOnTabChangedListener(onOpenTabListener);
    }

    private void addTab(String labelId, int drawableId, Class<?> c) {
        Intent intent = new Intent(this, c);
        TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);

        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.custom_tabwidget, getTabWidget(), false);
        title = (TextView) tabIndicator.findViewById(R.id.tabsText);
        title.setText(labelId);

        icon = (ImageView) tabIndicator.findViewById(R.id.tabsImg);
        icon.setImageResource(drawableId);

        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
////
        tabHost.addTab(spec);
    }

    @Override
    public void onBackPressed() {
        if(tabHost.getCurrentTab() == HOME){
            Intent intent = new Intent();
            intent.setAction(BroadCastBackStack.ACTION_BACK_STACK);
            sendBroadcast(intent);
        }else {
            super.onBackPressed();
        }
    }

    //    public void startChildActivity(String Id, Intent intent) {
//        Window window = getLocalActivityManager().startActivity(Id,intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//        if (window != null) {
//            mIdList.add(Id);
////            setContentView(window.getDecorView());
//        }
//    }
}
