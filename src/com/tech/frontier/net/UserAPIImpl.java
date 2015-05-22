package com.tech.frontier.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tech.frontier.listeners.DataListener;
import com.tech.frontier.models.entities.UserInfo;
import com.tech.frontier.net.handlers.UserInfoHander;
import com.tech.frontier.net.mgr.RequestQueueMgr;
import com.tech.frontier.utils.Constants;

import org.json.JSONObject;

public class UserAPIImpl implements UserAPI{
	
	UserInfoHander mHander = new UserInfoHander(); 

	@Override
	public void fetchUserInfo(String uid,String token,final DataListener<UserInfo> listener) {
		JsonObjectRequest request = new JsonObjectRequest(Constants.SINA_UID_TOKEN+uid+ "&access_token=" + token, null,
			       new Response.Listener<JSONObject>() {
			           @Override
			           public void onResponse(JSONObject response) {
			               if (listener != null) {
							 listener.onComplete(mHander.parse(response));
						 }
			           }
			       }, new Response.ErrorListener() {
			           @Override
			           public void onErrorResponse(VolleyError error) {
			               VolleyLog.e("Error: ", error.getMessage());
			           }
			       });

	       RequestQueueMgr.getRequestQueue().add(request);
	}

}
