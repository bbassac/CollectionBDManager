package lioncorps.collectionbdmanager.utils;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lioncorps.collectionbdmanager.MainActivity;
import lioncorps.collectionbdmanager.R;
import lioncorps.collectionbdmanager.bean.Collection;
import lioncorps.collectionbdmanager.bean.ListItem;

public class ListBDAdapter extends BaseAdapter {
    Collection collection = null; //GET FROM JSON
    List<ListItem> listItems = null;
    MainActivity displayActivity;
    ListItem item;
    private Context context;

    public ListBDAdapter(Context exContext, Collection listeBD, MainActivity displayActivity) {
        super();
        context = exContext;
        collection = listeBD;
        listItems = SerieUtils.convertCollection(listeBD);
        this.displayActivity = displayActivity;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public ListItem getItem(int arg0) {
        return listItems.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return listItems.get(arg0).getBdid();
    }


    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {

       item = listItems.get(index);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_item, null);
            view.setClickable(true);
        }

        ImageView imageview = (ImageView) view.findViewById(R.id.imageView);
        ((TextView) view.findViewById(R.id.serie)).setText(item.getSerieName());
        ((TextView) view.findViewById(R.id.nom)).setText(item.getTitre());
        ((TextView) view.findViewById(R.id.bd_id)).setText(String.valueOf(item.getBdid()));
        ((TextView) view.findViewById(R.id.serie_id)).setText(String.valueOf(item.getSerieId()));
        ((TextView) view.findViewById(R.id.editeur)).setText(item.getEditeur());
        view.findViewById(R.id.button).setOnClickListener(new MyOnClickListener(ListBDAdapter.this,displayActivity,collection,view,listItems,index));

        imageview.setImageBitmap(null);
        if (!TextUtils.isEmpty(item.getUrlImage())) {
            Picasso.with(context).load(item.getUrlImage()).resize(100, 120).into(imageview);
        }

        return view;
    }

}



