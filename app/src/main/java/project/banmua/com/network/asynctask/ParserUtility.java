package project.banmua.com.network.asynctask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.banmua.com.item.ItemHome;
import project.banmua.com.network.WebServiceConfig;

/**
 * Created by thangbk on 8/2/14.
 */
public class ParserUtility {
    public static final String TAG = "ParserUtility";

    public static ArrayList<ItemHome> parserGetCategoryHomeResponse(
            String json) throws JSONException {
        ArrayList<ItemHome> itemHomes = new ArrayList<ItemHome>();
        try {
            ItemHome itemHome;
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {

               JSONObject obj = arr.getJSONObject(i);
                itemHome = new ItemHome();
                itemHome.setId(obj.getString(WebServiceConfig.KEY_ID));
                itemHome.setTitle(obj.getString(WebServiceConfig.KEY_CATEGORY_NAME));
                itemHome.setUrl(obj.getString(WebServiceConfig.KEY_ICON));
                itemHomes.add(itemHome);

            }

        } catch (Exception e) {
            e.printStackTrace();
            // return null;
        }
        return itemHomes;
    }

}
