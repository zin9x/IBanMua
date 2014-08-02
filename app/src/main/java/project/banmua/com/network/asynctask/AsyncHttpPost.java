package project.banmua.com.network.asynctask;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import project.banmua.com.activity.BaseActivity;
import project.banmua.com.network.WebServiceConfig;


/**
 * AsyncHttpGet makes http post request based on AsyncTask
 * 
 * @author Zin
 */
public class AsyncHttpPost extends AsyncHttpBase {

	// HttpClient httpclient;
	//
	// /**
	// * Constructor
	// *
	// * @param context
	// * @param listener
	// * @param parameters
	// */
	// public AsyncHttpPost(FruityBaseActivity context,
	// AsyncHttpResponseListener listener, List<NameValuePair> parameters,
	// boolean isShowDilog) {
	// super(context, listener, parameters, isShowDilog);
	// }
	//
	// /**
	// * Constructor
	// *
	// * @param context
	// * @param process
	// * @param parameters
	// */
	// public AsyncHttpPost(FruityBaseActivity context,
	// AsyncHttpResponseProcess process, List<NameValuePair> parameters,
	// boolean isShowDilag) {
	// super(context, process, parameters, isShowDilag);
	//
	// }
	//
	// /**
	// * Constructor
	// *
	// * @param context
	// * @param listener
	// * @param parameters
	// */
	// public AsyncHttpPost(FruityBaseActivity context,
	// AsyncHttpResponseListener listener, List<NameValuePair> parameters,
	// String jsonString, boolean isExternalParam,
	// boolean isShowWaitingDialog) {
	// super(context, listener, parameters, jsonString, isExternalParam,
	// isShowWaitingDialog);
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// *
	// com.fgsecure.meyclub.app.network.AsyncHttpBase#request(java.lang.String)
	// */
	// @Override
	// protected String request(String url) {
	// try {
	// HttpParams params = new BasicHttpParams();
	// HttpConnectionParams.setConnectionTimeout(params,
	// WebServiceConfig.NETWORK_TIME_OUT);
	// HttpConnectionParams.setSoTimeout(params,
	// WebServiceConfig.NETWORK_TIME_OUT);
	// httpclient = createHttpClient(url, params);
	// HttpPost httpost = new HttpPost(url);
	// // For json FORMAT
	// if (isExternalParam) {
	// //
	// {"nameValuePairs":{"adr_liv_adr2":"","adr_liv_adr1":"EVRON","adr_liv_prenom":"TEST","cb_numcarte":"","cb_dateexp":"xxxxxxxxxx","cb_id_transac":"","adr_liv_cp":"53600","liste_cheque":[{"id":"1838","amount":0.0}],"nonce":"33aa2dc7-ab37-4ad6-b333-be72030caff9","token":"8csm1e4bxi70f3cxgmn4rjyjb652g9nj7ndcflen4rter","adr_liv_email":"test_bdd@prowebce.com","adr_liv_tel":"0605040302","mt_regle":"000","cde_id":"1489855","adr_liv_ville":"EVRON","adr_liv_nom":"TEST","mode_rgt":"CC","user_id":"53","signature":"1c0299788fb7319fb8264869cd511baa69a04739"}}
	// if (jsonString.contains("nameValuePairs")) {
	// SmartLog.log("AsyncHttpPost",
	// "json string contains nameValuePair");
	// // jsonString.replace("{\"nameValuePairs\":", "");
	// jsonString = jsonString.substring(18,
	// jsonString.length() - 1);
	// }
	//
	// SmartLog.logWS("WEBSERVICE", "WEBSERVICE INPUT PARAMS : "
	// + jsonString);
	// StringEntity se = new StringEntity(jsonString, HTTP.UTF_8);
	// httpost.setEntity(se);
	// httpost.setHeader("Accept", "application/json");
	// httpost.setHeader("Content-type",
	// "application/json;charset=UTF-8");
	//
	// // StringEntity se = new StringEntity(jsonString, HTTP.UTF_8);
	// // se.setContentType("application/json");
	// // httpost.setEntity(se);
	// // httpost.setHeader("Accept", "application/json");
	// // httpost.setHeader("Content-type",
	// // "application/json;charset=UTF-8");
	// }
	// // End for JSON format
	//
	// // Runable to handle long loading
	// // Thread thread = new Thread(new Runnable() {
	// //
	// // @Override
	// // public void run() {
	// //
	// // try {
	// // if (getTimeOut()) {
	// // shutdown(httpclient);
	// // checkTimeOut = false;
	// // isShowDialog = true;
	// // return;
	// // }
	// // if (getIsShowDialog()) {
	// // return;
	// // }
	// // Thread.sleep(100);
	// // } catch (InterruptedException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// // }
	// // });
	// // thread.run();
	// else {
	// // httpclient = createHttpClient(url, params);
	// // HttpPost httppost = new HttpPost(url);
	// SmartLog.logWS("AsyncHttpPost", "WEBSERVICE POST PARAMS : "
	// + parameters.toString());
	// httpost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
	// // httppost.setHeader("Content-Type", "multipart/form-data");
	// }
	// response = httpclient.execute(httpost);
	// statusCode = NETWORK_STATUS_OK;
	//
	// } catch (Exception e) {
	// statusCode = NETWORK_STATUS_ERROR;
	// e.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param listener
	 * @param parameters
	 */
	public AsyncHttpPost(BaseActivity context,
			AsyncHttpResponseListener listener, List<NameValuePair> parameters,
			boolean isShowDilog) {
		super(context, listener, parameters, isShowDilog);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param process
	 * @param parameters
	 */
	public AsyncHttpPost(BaseActivity context,
			AsyncHttpResponseProcess process, List<NameValuePair> parameters,
			boolean isShowDilag) {
		super(context, process, parameters, isShowDilag);

	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param listener
	 * @param parameters
	 */
	public AsyncHttpPost(BaseActivity context,
			AsyncHttpResponseListener listener, List<NameValuePair> parameters,
			String jsonString, boolean isExternalParam,
			boolean isShowWaitingDialog) {
		super(context, listener, parameters, jsonString, isExternalParam,
				isShowWaitingDialog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fgsecure.ubiqus.app.network.AsyncHttpBase#request(java.lang.String)
	 */
	@Override
	protected String request(String url) {

		try {

			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params,
					WebServiceConfig.NETWORK_TIME_OUT);
			HttpConnectionParams.setSoTimeout(params,
					WebServiceConfig.NETWORK_TIME_OUT);
			HttpClient httpclient = createHttpClient(url, params);

			// For json FORMAT
			HttpPost httpost = new HttpPost(url);

			StringEntity se;
			if (!isExternalParam) {
				httpost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
				response = httpclient.execute(httpost);
			} else {
				// Raw Json
				se = new StringEntity(jsonString, HTTP.UTF_8);

				httpost.setEntity(se);
				httpost.setHeader("Accept", "application/json");
				httpost.setHeader("Content-type",
						"application/json;charset=UTF-8");

				response = httpclient.execute(httpost);
				// String postParams = jsonString.substring(1,
				// jsonString.length() - 1);
				// SmartLog.logWS("WEBSERVICE", "POST STRING L: " + postParams);
				// BasicHttpEntity entity = new BasicHttpEntity();
				// entity.setContent(new ByteArrayInputStream(postParams
				// .getBytes()));
				// httpost.setEntity(entity);

			}

			// End for JSON format

			statusCode = NETWORK_STATUS_OK;

		} catch (Exception e) {
			statusCode = NETWORK_STATUS_ERROR;
			e.printStackTrace();
		}

		return null;
	}
}
