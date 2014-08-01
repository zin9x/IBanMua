package project.banmua.com.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.banmua.com.R;

/**
 * Created by Dat on 8/1/2014.
 */
public class FragmentRoot extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_root, container, false);
        return view;
    }
}
