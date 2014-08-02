/*
 * Name: $RCSfile: AsyncHttpGet.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: May 12, 2011 2:38:36 PM $
 *
 * Copyright (C) 2011 COMPANY NAME, Inc. All rights reserved.
 */

package project.banmua.com.network.asynctask;

import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.widget.Toast;

import project.banmua.com.activity.BaseActivity;
import project.banmua.com.network.WebServiceConfig;

/**
 * AsyncHttpGet makes http get request based on AsyncTask
 * 
 * @author Lemon
 */
public class AsyncHttpGet extends AsyncHttpBase {
	private static final String TAG = "AsyncHttpGet";
	HttpClient httpclient;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param listener
	 * @param parameters
	 */
	public AsyncHttpGet(BaseActivity context,
			AsyncHttpResponseListener listener, List<NameValuePair> parameters,
			boolean isShowWaitingDialog) {
		super(context, listener, parameters, isShowWaitingDialog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fgsecure.meyclub.app.network.AsyncHttpBase#request(java.lang.String)
	 */
	@Override
	protected String request(String url) {
		try {
			HttpParams params = new BasicHttpParams();
			// Lemon commented 19/04/2012
			// if (parameters != null) {
			// Iterator<NameValuePair> it = parameters.iterator();
			// while (it.hasNext()) {
			// NameValuePair nv = it.next();
			// params.setParameter(nv.getName(), nv.getValue());
			// }
			// }

			// Bind param direct to URL

			String combinedParams = "";
			if (!parameters.isEmpty()) {
				combinedParams += "?";
				for (NameValuePair p : parameters) {
					String paramString = p.getName() + "="
							+ URLEncoder.encode(p.getValue(), "UTF-8");
					if (combinedParams.length() > 1) {
						combinedParams += "&" + paramString;
					} else {
						combinedParams += paramString;
					}
				}

			}
			ResponseHandler<String> handler = new BasicResponseHandler();
			HttpConnectionParams.setConnectionTimeout(params,
					WebServiceConfig.NETWORK_TIME_OUT);
			HttpConnectionParams.setSoTimeout(params,
					WebServiceConfig.NETWORK_TIME_OUT);
			// Lemon commented 19/04/2012
			// HttpClient httpclient = createHttpClient(url, params);
			httpclient = new DefaultHttpClient();

			// Handle long loading

//			Thread thread = new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//					try {
//						if (getTimeOut()) {
//							try {
//								Toast.makeText(
//										context.getParent(),
//										context.getString(R.string.message_connection_shut_down),
//										Toast.LENGTH_LONG).show();
//							} catch (Exception e) {
//								Toast.makeText(
//										context,
//										context.getString(R.string.message_connection_shut_down),
//										Toast.LENGTH_LONG).show();
//							}
//							shutdown(httpclient);
//							checkTimeOut = false;
//							isShowDialog = true;
//							return;
//						}
//						if (getIsShowDialog()) {
//							return;
//						}
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			});
//			thread.start();


			HttpGet httpget = new HttpGet(url.toLowerCase() + combinedParams);
			response = httpclient.execute(httpget);
			// Lemon added
			// httpclient.getConnectionManager().shutdown();
			statusCode = NETWORK_STATUS_OK;
		} catch (Exception e) {
			statusCode = NETWORK_STATUS_ERROR;
			e.printStackTrace();
		}
		return null;
	}
}
