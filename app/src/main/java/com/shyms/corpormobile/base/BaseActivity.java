package com.shyms.corpormobile.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.shyms.corpormobile.R;
import com.shyms.corpormobile.helper.ActivityHelper;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity {

    protected ActivityHelper mActivityHelper;
    protected final static int DEFAULT_MENU_RES = -1;

    protected int getMenuLayoutResID() {
        return DEFAULT_MENU_RES;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mActivityHelper = new ActivityHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuLayoutResID() == DEFAULT_MENU_RES) {
            return super.onCreateOptionsMenu(menu);
        } else {
            getMenuInflater().inflate(getMenuLayoutResID(), menu);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        int count = fm.getBackStackEntryCount();
        for (int i = count - 1; i >= 0; i--) {
            Fragment fragment = fm.findFragmentByTag(fm.getBackStackEntryAt(i).getName());
            if (fragment != null && fragment.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
