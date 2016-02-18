package lioncorps.collectionbdmanager.utils;

import android.view.View;
import android.widget.TextView;

import lioncorps.collectionbdmanager.MainActivity;
import lioncorps.collectionbdmanager.R;
import lioncorps.collectionbdmanager.bean.Collection;

/**
 * Created by b.bassac on 03/12/2014.
 */
public class MyOnClickListener implements View.OnClickListener {

    private MainActivity activity;
    private Collection collection;
    private View itemView;

    public MyOnClickListener(MainActivity activity, Collection collection, View view) {
        this.activity = activity;
        this.collection = collection;
        this.itemView = view;
    }

    @Override
    public void onClick(View view) {
        Long serie_ID = Long.valueOf(((TextView) itemView.findViewById(R.id.serie_id)).getText().toString());
        Long bd_id = Long.valueOf(((TextView) itemView.findViewById(R.id.bd_id)).getText().toString());
        ToastUtils.display(activity,serie_ID+" "+bd_id+" "+((TextView) itemView.findViewById(R.id.nom)).getText());
        collection.setBDFromSerieAsPossede(serie_ID,bd_id);
    }


}

