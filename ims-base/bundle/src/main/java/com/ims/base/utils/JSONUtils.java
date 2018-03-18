package com.ims.base.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.ims.base.constants.PropertyConstants;

public final class JSONUtils {
	/**
	 * @param pathArray
	 * @return
	 * @throws JSONException
	 */
	public static List<Map<String, String>> getTitleLinkList(List<String> pathList)
			throws JSONException {
		Map<String, String> titleLinkMap;
		List<Map<String, String>> titleLinkList;
		JSONObject jsonObject;
		titleLinkList = new ArrayList<Map<String,String>>();
		for(String path : pathList){
			titleLinkMap = new HashMap<String, String>();
			jsonObject = new JSONObject(path);
			titleLinkMap.put(PropertyConstants.TITLE.getValue(), jsonObject.get(PropertyConstants.TITLE.getValue()).toString());
			titleLinkMap.put(PropertyConstants.URL.getValue(), jsonObject.get(PropertyConstants.URL.getValue()).toString());
			titleLinkList.add(titleLinkMap);
		}
		return titleLinkList;
	}

}
