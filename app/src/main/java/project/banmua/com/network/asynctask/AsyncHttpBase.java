package project.banmua.com.network.asynctask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;


import project.banmua.com.R;
import project.banmua.com.activity.BaseActivity;
import project.banmua.com.utility.NetworkUtility;

/**
 * AsyncHttpBase is base class for AsyncHttpGet and AsyncHttpPost class
 * 
 * @author Lemon
 */
public class AsyncHttpBase extends AsyncTask<String, Integer, String> {
	// Network status

	public static final int NETWORK_STATUS_OK = 0;
	public static final int NETWORK_STATUS_OFF = 1;
	public static final int NETWORK_STATUS_ERROR = 2;

	protected BaseActivity context;
	protected AsyncHttpResponseListener listener;
	protected List<NameValuePair> parameters;
	protected HttpResponse response;
	protected boolean isShowWaitingDialog;
	protected int statusCode;
	private static String TAG = "AsyncHttpBase";

	// Handle long loading
	private AlertDialog.Builder alertDialog;
	private AlertDialog dialog;

	public static boolean checkTimeOut = false;
	public static boolean isShowDialog = false;
	// For raw string
	protected JSONObject json;
	protected boolean isExternalParam = false;
	protected String jsonString = "";

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param listener
	 * @param parameters
	 */
	public AsyncHttpBase(BaseActivity context,
			AsyncHttpResponseListener listener, List<NameValuePair> parameters,
			boolean isShowWatingDilog) {
		this.context = context;
		this.listener = listener;
		this.parameters = parameters;
		this.isShowWaitingDialog = isShowWatingDilog;
	}
	
	/**
	 * Constructor
	 * 
	 * @param context
	 * @param listener
	 * @param parameters
	 */
	public AsyncHttpBase(BaseActivity context, AsyncHttpResponseListener listener,
			List<NameValuePair> parameters, String jsonString, boolean isExternalParam,
			boolean isShowWaitingDialog) {

		this.context = context;
		this.listener = listener;
		this.parameters = parameters;
		this.isExternalParam = isExternalParam;
		this.isShowWaitingDialog = isShowWaitingDialog;
		this.jsonString = jsonString;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (isShowWaitingDialog)
			listener.before();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(String... args) {

		// isShowDialog = false;
		// checkTimeOut = false;
		if (NetworkUtility.getInstance(context).isNetworkAvailable()) {
			// Request to server if network is available

			// Timer timer = new Timer();
			// timer.schedule(new TimerTask() {
			// int i = 0;
			//
			// @Override
			// public void run() {
			// // TODO Auto-generated method stub
			// i++;
			// publishProgress(i);
			// }
			// }, 0, 1000);

			return request(args[0]);
		} else {
			// Return status if network is not available
			statusCode = NETWORK_STATUS_OFF;
			return null;
		}

	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		// super.onProgressUpdate(values);
		if (values[0] == 30) {

			context.closeProgressDialog();

			try {
				alertDialog = new AlertDialog.Builder(context.getParent());
				alertDialog.setTitle(R.string.app_name);
				alertDialog.setPositiveButton(R.string.button_oui,
						onClickYesListener);
				alertDialog.setNegativeButton(R.string.button_no,
						onClickNoListener);
				dialog = alertDialog.create();
				if (!isShowDialog) {
					dialog.show();
					checkTimeOut = true;
					isShowDialog = true;
				} else {
					dialog.dismiss();
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				alertDialog = new AlertDialog.Builder(context);
				alertDialog.setTitle(R.string.app_name);
				alertDialog.setPositiveButton(R.string.button_oui,
						onClickYesListener);
				alertDialog.setNegativeButton(R.string.button_no,
						onClickNoListener);
				dialog = alertDialog.create();
				try {
					if (!isShowDialog) {
						dialog.show();
						checkTimeOut = true;
						isShowDialog = true;
					} else {
						dialog.dismiss();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		// Call method to process http status code and response
		listener.after(statusCode, response);
	}

	/**
	 * Send request to server
	 * 
	 * @param url
	 * @return
	 */
	protected String request(String url) {
		// This function will be implemented in AsyncHttpGet and AsyncHttpPost
		// class
		return null;
	}

	// ==================================HANDLE LONG
	// LOADING=======================

	private DialogInterface.OnClickListener onClickYesListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog2, int which) {
			dialog.dismiss();
			checkTimeOut = false;
			isShowDialog = true;

			// context.showProgressDialog();
		}
	};

	private DialogInterface.OnClickListener onClickNoListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog2, int which) {
			dialog.dismiss();
			checkTimeOut = true;
			isShowDialog = true;
			listener.onCancel();
		}
	};

	public boolean getTimeOut() {
		return checkTimeOut;
	}

	public boolean getIsShowDialog() {
		return isShowDialog;
	}

	public void shutdown(HttpClient client) {
		// dialog.dismiss()
		client.getConnectionManager().shutdown();
	}

	// ============================================================================

	/**
	 * Create HttpClient based on HTTP or HTTPS protocol that is parsed from url
	 * parameter. With HTTPS protocol, we accept all SSL certificates.
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	protected HttpClient createHttpClient(String url, HttpParams params) {
		if ((url.toLowerCase().startsWith("https"))) {
			// HTTPS process
			try {

				KeyStore trustStore = KeyStore.getInstance(KeyStore
						.getDefaultType());
				trustStore.load(null, null);

				SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
				sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
				HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("http", PlainSocketFactory
						.getSocketFactory(), 80));
				registry.register(new Scheme("https", sf, 443));

				ClientConnectionManager ccm = new ThreadSafeClientConnManager(
						params, registry);

				return new DefaultHttpClient(ccm, params);
			} catch (Exception e) {
				return new DefaultHttpClient(params);
			}
		} else {
			// HTTP process
			return new DefaultHttpClient(params);
		}
	}

	// ============================ Https functions ============================

	/**
	 * Trust every server - dont check for any certificate
	 */
	private static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open HTTPS connection. Use this method to setup and accept all SSL
	 * certificates from HTTPS protocol.
	 * 
	 * @param url
	 * @return
	 * @throws java.io.IOException
	 */
	public static HttpsURLConnection openSConnection(String url)
			throws IOException {
		URL theURL = new URL(url);
		trustAllHosts();
		HttpsURLConnection https = (HttpsURLConnection) theURL.openConnection();
		https.setHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
		return https;
	}

	/**
	 * Open HTTP connection
	 * 
	 * @param url
	 * @return
	 * @throws java.io.IOException
	 */
	public static HttpURLConnection openConnection(String url)
			throws IOException {
		URL theURL = new URL(url);
		return (HttpURLConnection) theURL.openConnection();
	}
}
