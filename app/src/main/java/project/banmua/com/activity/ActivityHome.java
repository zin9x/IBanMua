package project.banmua.com.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;

import java.util.ArrayList;


import project.banmua.com.R;
import project.banmua.com.adapter.AdapterListViewHome;
import project.banmua.com.item.ItemHome;
import project.banmua.com.network.TaskGetCategoryHome;
import project.banmua.com.network.exceptions.ExceptionListener;

/**
 * Created by Dat on 8/1/2014.
 */
public class ActivityHome extends BaseActivity {
    private ListView lsvHome;

    private ArrayList<ItemHome> itemHomeInfos;
    private AdapterListViewHome adapterListViewHome;

    @Override
    protected int layoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        itemHomeInfos = new ArrayList<ItemHome>();
        lsvHome = (ListView) findViewById(R.id.lsvHome);

    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        bindDataforHomePage();
    }

    private void bindDataforHomePage() {
        TaskGetCategoryHome taskGetCategoryHome = new TaskGetCategoryHome(getApplicationContext(), 0);
        taskGetCategoryHome.request(new Response.Listener<ArrayList<ItemHome>>() {
            @Override
            public void onResponse(ArrayList<ItemHome> itemHomes) {
                if (itemHomeInfos.size() > 0) {
                    itemHomeInfos = itemHomes;
                    adapterListViewHome = new AdapterListViewHome(ActivityHome.this, itemHomeInfos);
                    lsvHome.setAdapter(adapterListViewHome);
                }
            }
        }, new ExceptionListener() {
            @Override
            protected void onError(int errorCode, String errorMessage) {

            }
        });
    }
}
