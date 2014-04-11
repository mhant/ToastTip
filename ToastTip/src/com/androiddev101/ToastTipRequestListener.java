package com.androiddev101;

import android.util.Log;
import android.view.View;

import com.androiddev101.structs.ShowType;

public class ToastTipRequestListener 
			implements View.OnLongClickListener,
						View.OnClickListener{
	
	/**
	 * CONSTANTS
	 */
	public final static String TAG = ToastTipRequestListener.class.getCanonicalName();
	
	/**
	 * The ToastTip for this listener
	 */
	ToastTip mToastTip = null;

	public ToastTipRequestListener(View view, String tip, ShowType showType) 
			throws InvalidParamsException{
		if(view == null || tip == null){
			throw new InvalidParamsException();
		}
		mToastTip = new ToastTip(view, tip, showType);
	}
	
	@Override
	public boolean onLongClick(View view) {
		show();
		return false;
	}

	@Override
	public void onClick(View v) {
		show();
	}
	
	private void show(){
		if(mToastTip != null){
			try {
				mToastTip.show();
			} catch (InvalidParamsException e) {
				Log.e(TAG, "Uh oh something went horribly wrong, time to print a stack trace");
				e.printStackTrace();
			}
		}
	}
}