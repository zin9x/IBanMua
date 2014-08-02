package project.banmua.com.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;

import org.apache.http.NameValuePair;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


import project.banmua.com.R;
import project.banmua.com.adapter.AdapterListViewHome;
import project.banmua.com.item.ItemHome;
import project.banmua.com.network.TaskGetCategoryHome;
import project.banmua.com.network.WebServiceConfig;
import project.banmua.com.network.asynctask.AsyncHttpGet;
import project.banmua.com.network.asynctask.AsyncHttpResponseProcess;
import project.banmua.com.network.asynctask.ParameterFactory;
import project.banmua.com.network.asynctask.ParserUtility;
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
                    itemHomeInfos = itemHomes;
                    adapterListViewHome = new AdapterListViewHome(ActivityHome.this, itemHomeInfos);
                    lsvHome.setAdapter(adapterListViewHome);

            }
        }, new ExceptionListener() {
            @Override
            protected void onError(int errorCode, String errorMessage) {

            }
        });


//
//        List<NameValuePair> parameters = ParameterFactory.createGetCategoryParams(0);
//        AsyncHttpGet post = new AsyncHttpGet(self,
//                new AsyncHttpResponseProcess(self) {
//
//                    @Override
//                    public void processIfResponseSuccess(String response) {
//                        processParserListCategory(response);
//                    }
//
//                }, parameters, true);
//        post.execute(WebServiceConfig.URL_GET_CATEGORY_HOME);
    }

    private void processParserListCategory(String response) {
        try {
            itemHomeInfos = ParserUtility.parserGetCategoryHomeResponse(response);
            adapterListViewHome = new AdapterListViewHome(ActivityHome.this, itemHomeInfos);
            lsvHome.setAdapter(adapterListViewHome);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
