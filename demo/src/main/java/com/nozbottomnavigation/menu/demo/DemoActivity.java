package com.nozbottomnavigation.menu.demo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.nozbottomnavigation.menu.NZBottomNavigation;
import com.nozbottomnavigation.menu.NZBottomNavigationAdapter;
import com.nozbottomnavigation.menu.NZBottomNavigationItem;
import com.nozbottomnavigation.menu.NZBottomNavigationViewPager;

import java.util.ArrayList;

public class DemoActivity extends AppCompatActivity {

	private DemoFragment currentFragment;
	private DemoViewPagerAdapter adapter;
	private NZBottomNavigationAdapter navigationAdapter;
	private ArrayList<NZBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
	private boolean useMenuResource = true;
	private int[] tabColors;
	private Handler handler = new Handler();

	// UI
	private NZBottomNavigationViewPager viewPager;
	private NZBottomNavigation bottomNavigation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		boolean enabledTranslucentNavigation = getSharedPreferences("shared", Context.MODE_PRIVATE)
//				.getBoolean("translucentNavigation", false);
//		setTheme(enabledTranslucentNavigation ? R.style.AppTheme_TranslucentNavigation : R.style.AppTheme);
		setContentView(R.layout.activity_home);

		initUI();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacksAndMessages(null);
	}

	private void initUI() {
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
		}


		bottomNavigation = findViewById(R.id.bottom_navigation);
		viewPager = findViewById(R.id.view_pager);

		NZBottomNavigationItem item1 = new NZBottomNavigationItem(R.string.tab_1, R.drawable.ic_apps_black_24dp, R.color.color_tab_1);
		NZBottomNavigationItem item2 = new NZBottomNavigationItem(R.string.tab_2, R.drawable.ic_maps_local_bar, R.color.color_tab_2);
		NZBottomNavigationItem item3 = new NZBottomNavigationItem(R.string.tab_3, R.drawable.ic_maps_local_restaurant, R.color.color_tab_3);
		NZBottomNavigationItem item4 = new NZBottomNavigationItem(R.string.tab_4, R.drawable.ic_maps_local_bar, R.color.color_tab_4);
		NZBottomNavigationItem item5 = new NZBottomNavigationItem(R.string.tab_5, R.drawable.ic_maps_place, R.color.color_tab_5);

		bottomNavigationItems.add(item1);
		bottomNavigationItems.add(item2);
		bottomNavigationItems.add(item3);
		bottomNavigationItems.add(item4);
		bottomNavigationItems.add(item5);

		bottomNavigation.addItems(bottomNavigationItems);
		bottomNavigation.setColored(true);

//		bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
		bottomNavigation.setTranslucentNavigationEnabled(true);
		bottomNavigation.isColored();

		bottomNavigation.setOnTabSelectedListener(new NZBottomNavigation.OnTabSelectedListener() {
			@Override
			public boolean onTabSelected(int position, boolean wasSelected) {

				if (currentFragment == null) {
					currentFragment = adapter.getCurrentFragment();
				}

				if (wasSelected) {
					currentFragment.refresh();
					return true;
				}

				if (currentFragment != null) {
					currentFragment.willBeHidden();
				}

				viewPager.setCurrentItem(position, false);
				
				if (currentFragment == null) {
					return true;
				}
				
				currentFragment = adapter.getCurrentFragment();
				currentFragment.willBeDisplayed();


				return true;
			}
		});
		

		viewPager.setOffscreenPageLimit(4);
		adapter = new DemoViewPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);

		currentFragment = adapter.getCurrentFragment();

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {


			}
		}, 3000);
	}



	/**
	 * Return the number of items in the bottom navigation
	 */
	public int getBottomNavigationNbItems() {
		return bottomNavigation.getItemsCount();
	}

}
