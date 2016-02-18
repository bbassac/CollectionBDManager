package lioncorps.collectionbdmanager.utils;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import lioncorps.collectionbdmanager.MainActivity;
import lioncorps.collectionbdmanager.R;
import lioncorps.collectionbdmanager.bean.Collection;
import lioncorps.collectionbdmanager.bean.ListItem;

/**
 * Created by b.bassac on 03/12/2014.
 */
public class MyOnClickListener implements View.OnClickListener {

    private final List<ListItem> listItems;
    private final int index;
    private MainActivity activity;
    private Collection collection;
    private View itemView;
    private ListBDAdapter adapter;

    public MyOnClickListener(ListBDAdapter adapter, MainActivity activity, Collection collection, View view, List<ListItem> listItems, int index) {
        this.activity = activity;
        this.collection = collection;
        this.itemView = view;
        this.adapter=adapter;
        this.listItems= listItems;
        this.index = index;
    }

    @Override
    public void onClick(View view) {
        Long serie_ID = Long.valueOf(((TextView) itemView.findViewById(R.id.serie_id)).getText().toString());
        Long bd_id = Long.valueOf(((TextView) itemView.findViewById(R.id.bd_id)).getText().toString());
        ToastUtils.display(activity,serie_ID+" "+bd_id+" "+((TextView) itemView.findViewById(R.id.nom)).getText());
        collection.setBDFromSerieAsPossede(serie_ID,bd_id);
        listItems.remove(index);
        adapter.notifyDataSetChanged();
    }


}

