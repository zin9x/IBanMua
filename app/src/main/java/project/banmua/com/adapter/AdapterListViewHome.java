package project.banmua.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

import project.banmua.com.R;
import project.banmua.com.item.ItemHome;

/**
 * Created by Dat on 8/1/2014.
 */
public class AdapterListViewHome extends ArrayAdapter<ItemHome> {
    private LayoutInflater layoutInflater;

    public AdapterListViewHome(Context context, List<ItemHome> objects) {
        super(context, -1, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHome itemHome = getItem(position);
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_list_view_home, parent, false);
            viewHolder.imgIcon = (SmartImageView) convertView.findViewById(R.id.img_icon);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.txtTitle.setText("");
        }

        viewHolder.txtTitle.setText(itemHome.getTitle());
        viewHolder.imgIcon.setImageUrl(itemHome.getUrl());
        return convertView;
    }

    private class ViewHolder {
        private TextView txtTitle;
        private SmartImageView imgIcon;
    }
}
