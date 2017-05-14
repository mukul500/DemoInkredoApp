package net.xblacky.demoinkredoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by xBlacky on 5/14/2017.
 */

public class ListAdapter extends ArrayAdapter {

    public ListAdapter(Context context, ArrayList<InterestData> ar) {
        super(context, 0, ar);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_view, parent, false);
        }
        InterestData id= (InterestData) getItem(position);
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);

        TextView month=(TextView)listView.findViewById(R.id.months);
        month.setText(id.getMonth()+" months");

        TextView emi=(TextView) listView.findViewById(R.id.emi);
        emi.setText(""+df.format(id.getEmi()));

        TextView totalPayment= (TextView) listView.findViewById(R.id.totalPayment);
        totalPayment.setText(""+df.format(id.getTottalPayment()));

        return listView;
    }
}
