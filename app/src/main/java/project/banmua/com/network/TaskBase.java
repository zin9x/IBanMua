package project.banmua.com.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import project.banmua.com.network.exceptions.ExceptionConstant;
import project.banmua.com.network.exceptions.ExceptionListener;
import project.banmua.com.network.exceptions.ResException;

/**
 * Created by Thangbk on 1/8/14.
 */
public abstract class TaskBase<T extends Object> {
    private static final int SOCKET_TIMEOUT_MS = 50000;
    private HashMap<String, String> mParams;
    private boolean debugging = false;
    protected JSONObject mGson;
    private Context mContext;
    private Request<T> mRequest;

    public TaskBase(Context context) {
        this.mContext = context;
    }

    public final void request(final Response.Listener<T> listener, final ExceptionListener errorListener) {
        String url = genURLRequest();
//            String encodedURL = URLEncoder.encode(url, "UTF-8");

        Log.e("REQUEST", url);
        mRequest = new Request<T>(getMethod(), url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
            }

        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
                String data = new String(networkResponse.data);
                T result = null;
                ResException resException;
                try {
                    resException = validData(data);
                    if (null == resException) {
                        result = genDataFromJSON(data);
                        if (debugging) {
                            VolleyError errorTest = testAPI();
                            if (null != errorTest) {
                                return Response.error(errorTest);
                            } else {
                                return Response.success(result, getCacheEntry());
                            }
                        } else {
                            return Response.success(result, getCacheEntry());
                        }
                    } else {
                        VolleyError error = new VolleyError(resException);
                        return Response.error(error);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    resException = new ResException(e.getMessage());
                    VolleyError error = new VolleyError(resException);
                    return Response.error(error);
                }
            }

            @Override
            protected void deliverResponse(T t) {
                listener.onResponse(t);
            }

            @Override
            public void cancel() {
                super.cancel();
            }
        };

        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        mRequest.setRetryPolicy(new DefaultRetryPolicy(
                SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(mRequest);


    }

    protected void genDataFromErrorMessage(String data) {

    }

    protected VolleyError testAPI() {
        return null;
    }

    public void cancelRequest() {
        if (null != mRequest) {
            mRequest.cancel();
        }
    }

    private ResException validData(String json) {
        ResException resException = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int code = jsonObject.getInt("code");
            if (code != 0) {
                String message = getMessage(json);
                resException = new ResException(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            resException = new ResException(e.getMessage());
        }
        return resException;
    }

    protected String getMessage(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject.getString("message");
    }


    protected abstract T genDataFromJSON(String json) throws JSONException;

    protected abstract int getMethod();

    private String genURLRequest() {
        mParams = new HashMap<String, String>();
        genParams(mParams);
        String url = getBaseURL();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        if (mParams.size() > 0) {
            Set<String> keys = mParams.keySet();
            String pre = "?";
            for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                String key = i.next();
                String value = mParams.get(key);
                stringBuilder.append(pre);
                stringBuilder.append(key);
                stringBuilder.append("=");
                stringBuilder.append(value);
                pre = "&";
            }
        }
        return stringBuilder.toString();
    }

    protected abstract String getBaseURL();

    protected void genParams(HashMap<String, String> params) {
//        params.put("os", "" + 2);
//        try {
//            byte[] data = PPPApplication.CONFIG.USER_AGENT.getBytes("UTF-8");
//            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
//            Log.e("TAG", "BASE 64 =" + base64);
//            params.put("agent", URLEncoder.encode(base64, "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            params.put("agent", "Android");
//        }
//        params.put("udid", PPPApplication.CONFIG.DEVICE_UUID);
    }

}
