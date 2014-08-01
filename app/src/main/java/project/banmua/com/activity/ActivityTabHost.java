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
    private static final int SEARCH = 1;
    private static final int POST = 2;
    private static final int NOTIFICATION = 3;
    private static final int SETTINGS = 4;

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
                    //normal
                    icon.setImageResource(R.drawable.ic_launcher);
                } else if (i == POST) {
                    icon.setImageResource(R.drawable.ic_launcher);
                } else if (i == SEARCH) {
                    icon.setImageResource(R.drawable.ic_launcher);
                } else if (i == NOTIFICATION) {
                    icon.setImageResource(R.drawable.ic_launcher);
                } else if (i == SETTINGS){
                    icon.setImageResource(R.drawable.ic_launcher);
                }
            }
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#5f4434"));
            TextView textView = (TextView) tabHost.getCurrentTabView().findViewById(R.id.tabsText);
            textView.setTextColor(Color.WHITE);
            icon = (ImageView) tabHost.getCurrentTabView().findViewById(R.id.tabsImg);
            //select
            if (tabHost.getCurrentTab() == HOME) {
                icon.setImageResource(R.drawable.ic_launcher);
            } else if (tabHost.getCurrentTab() == POST) {
                icon.setImageResource(R.drawable.ic_launcher);
            } else if (tabHost.getCurrentTab() == SEARCH) {
                icon.setImageResource(R.drawable.ic_launcher);
            } else if (tabHost.getCurrentTab() == NOTIFICATION) {
                icon.setImageResource(R.drawable.ic_launcher);
            } else if (tabHost.getCurrentTab() == SETTINGS){
                icon.setImageResource(R.drawable.ic_launcher);
            }
        }
    };
    private TextView title;
    private ImageView icon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_hot);

        Resources res = getResources(); // Resource object to get Drawables
        tabHost = getTabHost(); // The activity TabHost
        tabWidget = tabHost.getTabWidget();
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        addTab("Trang chủ", R.drawable.ic_launcher, ActivityHome.class);
        addTab("Tìm kiếm", R.drawable.ic_launcher, ActivitySearch.class);
        addTab("Đăng tin", R.drawable.ic_launcher, ActivityPost.class);
        addTab("Thông báo", R.drawable.ic_launcher, ActivityInformation.class);
        addTab("Mở rộng", R.drawable.ic_launcher, ActivitySetting.class);

        tabHost.getTabWidget().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#c4ebdc"));
        }

        tabHost.setCurrentTab(HOME);
        tabHost.getTabWidget().getChildAt(HOME).setBackgroundColor(Color.parseColor("#5f4434"));

        title = (TextView) tabHost.getCurrentTabView().findViewById(R.id.tabsText);
        title.setTextColor(Color.WHITE);
        icon = (ImageView) tabHost.getCurrentTabView().findViewById(R.id.tabsImg);
        icon.setImageResource(R.drawable.ic_launcher);
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
        tabHost.addTab(spec);
    }

}
