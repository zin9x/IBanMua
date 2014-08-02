package project.banmua.com.network.asynctask;

import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import project.banmua.com.R;
import project.banmua.com.activity.BaseActivity;
import project.banmua.com.utility.DialogUtility;


/**
 * AsyncHttpResponseProcess: process server response
 * 
 * @author Lemon
 */
public class AsyncHttpResponseProcess implements AsyncHttpResponseListener {
	private static final String TAG = "AsyncHttpResponseProcess";

	private BaseActivity context;

	public AsyncHttpResponseProcess(BaseActivity context) {
		this.context = context;
	}

	@Override
	public void before() {
		// Show waiting dialog during connection

		context.showProgressDialog();
	}

	@Override
	public void after(int statusCode, HttpResponse response) {
		// Process server response
		context.closeProgressDialog();
		switch (statusCode) {

		case AsyncHttpBase.NETWORK_STATUS_OFF:
			try {
				try {
//					DialogUtility.alert(context.getParent(),
//							R.string.message_network_is_unavailable);
				} catch (Exception e) {

//					DialogUtility.alert(context,
//							R.string.message_network_is_unavailable);
				}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case AsyncHttpBase.NETWORK_STATUS_ERROR:
			try {
				try {
//					DialogUtility.alert(context.getParent(),
//                            R.string.message_server_error);
				} catch (Exception e) {
//					DialogUtility.alert(context, R.string.message_server_error);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		case AsyncHttpBase.NETWORK_STATUS_OK:
			processHttpResponse(response);
			break;
		default:
			try {
				try {
//					DialogUtility.alert(context.getParent(),
//							R.string.message_server_error);
				} catch (Exception e) {
//					DialogUtility.alert(context, R.string.message_server_error);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Process HttpResponse
	 * 
	 * @param response
	 */
	public void processHttpResponse(HttpResponse response) {
		String json = "";
		try {

			// Get json response
			// long current = System.currentTimeMillis();
			json = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

			// if (json == null) {
			// SmartLog.log(TAG, "Json return null");
			// DialogUtility.alert(context.getParent(),
			// R.string.message_error_cant_extract_server_response);
			// return;
			// }
			// Parser information


			// CommonInfo commonInfo = ParserUtility.parserCommonResponse(json);
			// if (commonInfo.isSuccess()) {
			processIfResponseSuccess(json);
			// } else {
			// processIfResponseFail();
			// context.checkInvalidToken(commonInfo.getMessage());
			// }

		} catch (Exception e) {

			e.printStackTrace();
			try {
//				DialogUtility.alert(context.getParent(),
//						R.string.message_connection_shut_down);
			} catch (Exception e1) {
//				DialogUtility.alert(context,
//						R.string.message_connection_shut_down);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	/**
	 * Interface function
	 * 
	 * @throws org.json.JSONException
	 */

	public void processIfResponseSuccess(String response) throws JSONException {


	}

	/**
	 * Interface function
	 */
	public void processIfResponseFail() {
		// SmartLog.log(TAG, "Process if response is fail ===================");
	}

	@Override
	public void onCancel() {

		// context.finish();
		// TODO Auto-generated method stub

	}
}
