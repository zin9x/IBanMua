package project.banmua.com.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import project.banmua.com.R;
import project.banmua.com.adapter.AdapterListViewHome;
import project.banmua.com.item.ItemHome;

/**
 * Created by Dat on 8/1/2014.
 */
public class FragmentRoot extends Fragment {
    private ListView listViewHome;
    private AdapterListViewHome adapterListViewHome;
    private ArrayList<ItemHome> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_root, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listViewHome = (ListView) view.findViewById(R.id.list_view_home);
        data = new ArrayList<ItemHome>();
        updateListView(data);
    }

    private void updateListView(ArrayList<ItemHome> data) {
        adapterListViewHome = new AdapterListViewHome(getActivity().getApplicationContext(),data);
        listViewHome.setAdapter(adapterListViewHome);
    }
}
