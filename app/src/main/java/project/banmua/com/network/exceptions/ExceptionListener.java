package project.banmua.com.network.exceptions;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 *
 */
public abstract class ExceptionListener implements Response.ErrorListener {

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        String message;
        Throwable cause = volleyError.getCause();
        if (null == volleyError.getMessage()) {
            Log.e("PPP", "No message");
            message = volleyError.toString();
        } else {
            message = volleyError.getCause().getMessage();
            Log.e("PPP", volleyError.getMessage());
        }
        int errorCode;
        if (cause instanceof ResException) {
            errorCode = ((ResException) cause).getErrorCode();
        } else {
            errorCode = ExceptionConstant.UNDEFINED_CODE;
        }
        onError(errorCode, message);
    }

    protected abstract void onError(int errorCode, String errorMessage);
}
