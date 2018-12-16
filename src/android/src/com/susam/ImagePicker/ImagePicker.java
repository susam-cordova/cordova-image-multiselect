/**
 * The MIT License
 * 
 * Copyright (c) 2014 CSullivan102 
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * Code taken from: https://github.com/wymsee/cordova-imagePicker
 * 
 * 
 * An Image Picker Plugin for Cordova/PhoneGap.
 */
package com.susam;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.erikagtierrez.multiple_media_picker.Gallery;

public class ImagePicker extends CordovaPlugin {
	public static String TAG = "ImagePicker";
	
	private static final int OPEN_MEDIA_PICKER = 1;
	private static final int MODE_IMAGES_AND_VIDEO = 1;
	private static final int MODE_IMAGES_ONLY = 2;
	private static final int MODE_VIDEO_ONLY = 3;
	 
	private CallbackContext callbackContext;
	private JSONObject params;
	 
	public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;
		this.params = args.getJSONObject(0);
		if (action.equals("getPictures")) {
			Intent intent = new Intent(cordova.getActivity(), Gallery.class);
			int max = 20;
			String title = getResourceString("multi_title");
			String imagesTitle = getResourceString("images_tab_title");
			String videosTitle = getResourceString("videos_tab_title");
			if (this.params.has("maximumImagesCount")) {
				max = this.params.getInt("maximumImagesCount");
			}
			if (this.params.has("title")) {
				title = this.params.getString("title");
			}
			if (this.params.has("imagesTitle")) {
				imagesTitle = this.params.getString("imagesTitle");
			}
			if (this.params.has("videosTitle")) {
				videosTitle =  this.params.getString("videosTitle");
			}
			intent.putExtra("title", title);
			intent.putExtra("mode", MODE_IMAGES_AND_VIDEO);
			intent.putExtra("maxSelection", max);
			intent.putExtra("imagesTitle", imagesTitle);
			intent.putExtra("videosTitle", videosTitle);
			
			if (this.cordova != null) {
				this.cordova.startActivityForResult((CordovaPlugin) this, intent, OPEN_MEDIA_PICKER);
			}
		}
		return true;
	}
	
	private String getResourceString(String name) {
		if (this.cordova == null) return "";
		int id = cordova.getActivity().getResources().getIdentifier(name, "string", cordova.getActivity().getPackageName());
		String str = cordova.getActivity().getString(id);
		return (str == null) ? "" : str;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK && data != null) {
			ArrayList<String> filePaths = data.getStringArrayListExtra("result");
			addFilePrefix(filePaths);
			JSONArray res = new JSONArray(filePaths);
			this.callbackContext.success(res);
			return;
		} 
		if (resultCode == Activity.RESULT_CANCELED) {
			JSONArray empty = new JSONArray();
			this.callbackContext.success(empty);
			return;
		}		
		this.callbackContext.error("Unknown error!");
	}

	private void addFilePrefix(ArrayList<String> paths) {
		for (int i = 0; i < paths.size(); ++i) {
			String path = paths.get(i);
			String filePath = "file://" + path;
			paths.set(i, filePath);
		}
	}
}
