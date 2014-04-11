package com.androiddev101;

import com.androiddev101.structs.ShowType;
import com.androiddev101.toasttip.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import android.widget.Toast;

public class ToastTip {
	
	/**
	 * CONSTANTS
	 */
	static final private int X = 0;
	static final private int Y = 1;
	static final private int MIN_VALID_CORD = 0;
	static final private String TAG = ToastTip.class.getCanonicalName();
	
	//error messages
	static final private String NULL_VIEW_ERROR = "One does not simple walk attach a ToastTip to a null view.  Try initializing your view next time.";
	static final private String NULL_CONTEXT_ERROR = "Context cannot be accessed.  Context matters.";
	
	
	/**
	 * MEMBERS ONLY!
	 */
	//static reference to the last toast shown in case a new toast is shown so we can dismiss previous
	static Toast mToastShown = null;	
	//our nice little toast
	private Toast mToast = null;
	//this is the view for the toast tip, will retain it for later positioning
	private View mAttachedView = null; 
	//the show type for this toasttip
	private ShowType mShowType;
	
	/**
	 * Default constructor for ToastTip
	 * @param view - view for ToastTip
	 * @param tip - tip to display
	 * @throws InvalidParamsException - incase invalid params are given
	 */
	
	@SuppressLint("ShowToast")
	public ToastTip(View view, String tip, ShowType showType) throws InvalidParamsException{
		//in case view is null then NO TIP FOR YOU!!
		if(view == null){
			throw new InvalidParamsException();
		}
		mAttachedView = view;
		mShowType = showType;
		
		Context context = view.getContext();
		//insure we have some valid coordinates for the death star
		if(context == null){
			throw new InvalidParamsException();
		}
		mToast = Toast.makeText(view.getContext(), tip, Toast.LENGTH_LONG);
		
		//time for some fat reduction, on the inflater diet
		LayoutInflater inflatonator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View toastView = inflatonator.inflate(R.layout.toast_tip, null);
		TextView tipText = (TextView) toastView.findViewById(R.id.tv_toast_text);
		tipText.setText(tip);
		mToast.setView(toastView);
	}
	
	public void show() throws InvalidParamsException{
		//if previous toast is showing then dismiss
		if(mToastShown != null && mToastShown != mToast){
			mToastShown.cancel();
		}
		if(mToast != null && mAttachedView != null){
			
			int viewLocation[] = new int[2];
			mAttachedView.getLocationOnScreen(viewLocation);
			if(viewLocation[X] <= MIN_VALID_CORD || 
					viewLocation[Y] <= MIN_VALID_CORD ){
				throw new InvalidParamsException();
			}
			//assuming that view is in viewgroup with marginlayoutparams derivative then can adjust position
			if(mShowType == ShowType.BELOW_VIEW || mShowType == ShowType.ABOVE_VIEW){
				LayoutParams lp = mAttachedView.getLayoutParams();
				int marginAdjust = 0;
				if(lp instanceof MarginLayoutParams){
					MarginLayoutParams mlp = ((MarginLayoutParams)lp);
					marginAdjust = mlp.bottomMargin + mlp.topMargin;
				}
				int toastHeight = mToast.getView().getHeight();
				int yAdjust = ((mShowType == mShowType.ABOVE_VIEW)?(0 - mAttachedView.getHeight() - marginAdjust - toastHeight):(0 + mAttachedView.getHeight() - marginAdjust));
				mToast.setGravity(Gravity.TOP | Gravity.LEFT, viewLocation[X], viewLocation[Y]  + yAdjust);
			}
			else if(mShowType == ShowType.TOP){
				mToast.setGravity(Gravity.TOP | Gravity.CENTER,0, 0);
			}
			mToast.show();
			mToastShown = mToast;
		}
	}
	
	/**
	 * Convenience function to attach a ToastTip to the current view
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 */
	public static void attachToView(final View view, final int tip){
		attachById(view, tip, true);
	}
	
	/**
	 * Convenience function to attach a ToastTip to the current view
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 * @param showType - show type for this ToastTip
	 */
	public static void attachToView(final View view, final int tip, final ShowType showType){
		attachById(view, tip, true, showType);
	}
	
	/**
	 * Attaches a ToastTip to the current view
	 * @param view - to which view to attach the ToastTip
	 * @param tip - tip string to display
	 */
	public static void attachToView(final View view, final String tip){
		attach(view, tip, true);
	}

	/**
	 * Attaches a ToastTip to the current view
	 * @param view - to which view to attach the ToastTip
	 * @param tip - tip string to display
	 * @param showType - show type for this ToastTip
	 */
	public static void attachToView(final View view, final String tip, final ShowType showType){
		attach(view, tip, true, showType);
	}

	/**
	 * Convenience function to attach a ToastTip to the current view for on click action
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 */
	public static void attachToViewShort(final View view, final int tip){
		attachById(view, tip, false);
	}
	
	/**
	 * Convenience function to attach a ToastTip to the current view for on click action
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 * @param showType - show type for this ToastTip
	 */
	public static void attachToViewShort(final View view, final int tip, final ShowType showType){
		attachById(view, tip, false, showType);
	}
	
	/**
	 * Attaches a ToastTip to the current view for on click action
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 */
	public static void attachToViewShort(final View view, final String tip){
		attach(view, tip, false);
	}
	
	/**
	 * Attaches a ToastTip to the current view for on click action
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 * @param showType - show type for this ToastTip
	 */
	public static void attachToViewShort(final View view, final String tip, final ShowType showType){
		attach(view, tip, false, showType);
	}
	
	/**
	 * Attaches a view to a tip for OnLongClickListener or OnClickListener, default showtype is top
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 * @param longClick - true if attaching a OnLongClickListener listener, otherwise OnClickListener
	 */
	
	private static void attach(final View view, final String tip, final boolean longClick) {
		attach(view, tip, longClick, ShowType.TOP);
	}
	

	/**
	 * Attaches a view to a tip for OnLongClickListener or OnClickListener
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 * @param longClick - true if attaching a OnLongClickListener listener, otherwise OnClickListener
	 * @param showType - ShowType for this ToastTip
	 */
	
	private static void attach(final View view, final String tip, final boolean longClick, final ShowType showType) {
		if(view == null){
			Log.e(TAG, NULL_VIEW_ERROR);
			return;
		}
		//set a global layout listener to insure we get the valid location
		view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				try {
					if(longClick){
						view.setOnLongClickListener(new ToastTipRequestListener(view, tip, showType));
					}
					else{
						view.setOnClickListener(new ToastTipRequestListener(view, tip, showType));
					}
				} catch (InvalidParamsException e) {
					//issue in attaching print stacktrace
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Attaches a view to a tip by res id for OnLongClickListener or OnClickListener, default ShowType is TOP
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 * @param longClick - true if attaching a OnLongClickListener listener, otherwise OnClickListener
	 */
	
	private static void attachById(final View view, final int tip, final boolean longClick) {
		attachById(view, tip, longClick, ShowType.TOP);
	}
	
	/**
	 * Attaches a view to a tip by res id for OnLongClickListener or OnClickListener
	 * @param view - to which view to attach the ToastTip
	 * @param tip - resource id of string to display
	 * @param longClick - true if attaching a OnLongClickListener listener, otherwise OnClickListener
	 * @param showType - ShowType for this ToastTip
	 */
	
	private static void attachById(final View view, final int tip, final boolean longClick, ShowType showType) {
		if(view == null){
			Log.e(TAG, NULL_VIEW_ERROR);
			return;
		}
		Context context = view.getContext();
		if(context == null){
			Log.e(TAG, NULL_CONTEXT_ERROR);
			return;
		}
		attach(view, context.getString(tip), longClick, showType);
	}

}
