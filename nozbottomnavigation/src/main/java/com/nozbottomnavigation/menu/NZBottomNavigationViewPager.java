package com.nozbottomnavigation.menu;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *
 */
public class NZBottomNavigationViewPager extends ViewPager {

	private boolean enabled;

	public NZBottomNavigationViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.enabled = false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.enabled) {
			return super.onTouchEvent(event);
		}

		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (this.enabled) {
			return super.onInterceptTouchEvent(event);
		}

		return false;
	}

	/**
	 * Enable or disable the swipe navigation
	 * @param enabled
	 */
	public void setPagingEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}