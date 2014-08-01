package project.banmua.com.network;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import project.banmua.com.item.ItemHome;

/**
 * Created by Zin9x on 8/1/2014.
 */
public class TaskGetCategoryHome extends TaskBase<ArrayList<ItemHome>> {
    private String parentID;

    public TaskGetCategoryHome(Context context, String parentID) {
        super(context);
        this.parentID = parentID;
    }

    @Override
    protected ArrayList<ItemHome> genDataFromJSON(String json) throws JSONException {
        ArrayList<ItemHome> itemHomes = new ArrayList<ItemHome>();
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ItemHome itemHome = new ItemHome();
            itemHome.setId(jsonObject.getString(WebServiceConfig.KEY_ID));
            itemHome.setTitle(jsonObject.getString(WebServiceConfig.KEY_CATEGORY_NAME));
            itemHome.setUrl(jsonObject.getString(WebServiceConfig.KEY_ICON));
            itemHomes.add(itemHome);
        }

        return itemHomes;
    }

    @Override
    protected int getMethod() {
        return Request.Method.GET;
    }

    @Override
    protected void genParams(HashMap<String, String> params) {
        super.genParams(params);
        params.put(WebServiceConfig.PARAM_PARENT_ID, parentID);
    }

    @Override
    protected String getBaseURL() {
        return WebServiceConfig.URL_GET_CATEGORY_HOME;
    }
}
