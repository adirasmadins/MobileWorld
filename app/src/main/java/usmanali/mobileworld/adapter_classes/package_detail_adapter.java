package usmanali.mobileworld.adapter_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usmanali.mobileworld.R;

/**
 * Created by SAJIDCOMPUTERS on 7/22/2017.
 */

public class package_detail_adapter extends BaseExpandableListAdapter {
    List<String> packages_catorgerylist;
    HashMap<String,List<String>> packagedetailslist;
    Context context;

    public package_detail_adapter(List<String> packages_catorgerylist, HashMap<String, List<String>> packagedetailslist, Context context) {
        this.packages_catorgerylist = packages_catorgerylist;
        this.packagedetailslist = packagedetailslist;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return packages_catorgerylist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return packagedetailslist.get(packages_catorgerylist.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return packages_catorgerylist.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return packagedetailslist.get(packages_catorgerylist.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String packagescategories= (String) this.getGroup(i);
        if(view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.packages_catorgery_layout,null);
        }
        TextView packagecategorytxt =(TextView) view.findViewById(R.id.packages_catorgery);
        packagecategorytxt.setText(packagescategories);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String packagename= (String) this.getChild(i,i1);
        if(view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.packages_detail_layout,null);

        }
        TextView package_nametxt=(TextView) view.findViewById(R.id.package_name);
        package_nametxt.setText(packagename);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
