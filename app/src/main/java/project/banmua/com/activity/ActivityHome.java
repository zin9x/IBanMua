package project.banmua.com.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import project.banmua.com.R;
import project.banmua.com.activity.fragment.FragmentRoot;

/**
 * Created by Dat on 8/1/2014.
 */
public class ActivityHome extends BaseActivity{
    @Override
    protected int layoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.root_fragment, new FragmentRoot(), FragmentRoot.class.getName()).commit();
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }
}
