package project.banmua.com.network.asynctask;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import project.banmua.com.network.WebServiceConfig;


public final class ParameterFactory {

	private static String TAG = "ParameterFactory";

	public static List<NameValuePair> createGetCategoryParams(int parentId) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair(WebServiceConfig.PARAM_PARENT_ID,
                String.valueOf(parentId)));
		return parameters;
	}
}
