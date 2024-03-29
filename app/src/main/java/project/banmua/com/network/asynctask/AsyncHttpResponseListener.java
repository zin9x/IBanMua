package project.banmua.com.network.asynctask;

import org.apache.http.HttpResponse;

/**
 * Predefine some http listener methods
 * 
 * @author Lemon
 */
public interface AsyncHttpResponseListener {
	/**
	 * Before get http response
	 */
	public void before();

	/**
	 * After get http response
	 * 
	 * @param statusCode
	 * @param response
	 */
	public void after(int statusCode, HttpResponse response);

	/**
	 * OnCancel request after 30s
	 */
	public void onCancel();
}
